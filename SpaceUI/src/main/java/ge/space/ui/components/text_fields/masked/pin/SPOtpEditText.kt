package ge.space.ui.components.text_fields.masked.pin

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.AttributeSet
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import ge.space.spaceui.R
import ge.space.ui.components.text_fields.masked.base.OnPinEnteredListener
import ge.space.ui.components.text_fields.masked.base.SPPinBaseEditText.Companion.DEFAULT_LENGTH
import ge.space.ui.util.extension.getColorFromAttribute
import java.util.*

class SPOtpEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs) {

    var onPinEnteredListener: OnPinEnteredListener? = null

    private var charSize = DEFAULT_FLOAT
    private var numChars = DEFAULT_FLOAT
    private var maxLength = DEFAULT_LENGTH
    private var lineCords: Array<RectF?>? = null
    private var pinBackground: Drawable? = null

    private val pinWidth: Float by lazy { resources.getDimension(R.dimen.sp_pin_edit_text_width) }
    private val pinHeight: Float by lazy { resources.getDimension(R.dimen.sp_pin_edit_text_height) }
    private val lineStroke: Float by lazy { resources.getDimension(R.dimen.sp_pin_edit_text_line_stroke) }


    private var charPaint: Paint = Paint(paint)
    private var lastCharPaint: Paint = Paint(paint)
    private var linesPaint: Paint = Paint(paint)
    private lateinit var charBottom: FloatArray
    private val fullText: CharSequence
        get() = text ?: ""

    init {
        linesPaint.strokeWidth = lineStroke
        numChars = maxLength.toFloat()
    }

    fun setStyle(@StyleRes defStyleRes: Int) {

        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPPinEntryEditText)
        styleAttrs.run {
            isCursorVisible = false
            isClickable = false
            setTextIsSelectable(false)
            includeFontPadding = false

            setTextColor(
                getColor(
                    R.styleable.SPPinEntryEditText_android_textColor,
                    context.getColorFromAttribute(R.attr.brand_primary)
                )
            )
            pinBackground =
                ContextCompat.getDrawable(context, R.drawable.bg_pin_number)
            recycle()
        }
    }
    var isError: Boolean = false
    fun setError(
        isError: Boolean,
        @AttrRes errorColor: Int = context.getColorFromAttribute(R.attr.status_primary_distractive)
    ) {
        setTextColor(if (isError) errorColor else context.getColorFromAttribute(R.attr.brand_primary))
    }

    fun setMaxLength(maxLength: Int) {
        val params = this.layoutParams
        params.width = (pinWidth * maxLength).toInt()
        params.height = pinHeight.toInt()
        this.layoutParams = params
        this.maxLength = maxLength
        numChars = maxLength.toFloat()
        filters = arrayOf<InputFilter>(LengthFilter(maxLength))
        text = null
        requestLayout()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        charSize = pinWidth
        lineCords = arrayOfNulls(numChars.toInt())
        charBottom = FloatArray(numChars.toInt())


        var startX: Int = ViewCompat.getPaddingStart(this)
        var i = 0
        while (i < numChars) {
            lineCords?.let { cords ->
                cords[i] = RectF(
                    startX.toFloat(),
                    top.toFloat(),
                    startX + charSize,
                    bottom.toFloat()
                )

                startX +=
                    (charSize).toInt()

                charBottom[i] = ((cords[i]?.bottom ?: 0f) + (cords[i]?.top ?: 0f)) / 2
            }
            i++
        }
    }



    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        // When tapped, move cursor to end of text.
        super.setOnClickListener {
            setSelection(fullText.length)
        }

        lastCharPaint.color = textColors.defaultColor
        charPaint.color = textColors.defaultColor

        val textLength = fullText.length
        val textWidths = FloatArray(textLength)
        paint.getTextWidths(text, 0, textLength, textWidths)

        var i = 0
        while (i < numChars) {
            lineCords?.let { cords ->
                if (pinBackground != null && (textLength <= i)) {
                    val padding =
                        pinWidth.minus(resources.getDimension(R.dimen.sp_pin_edit_line_size)) / 2
                    pinBackground?.setBounds(
                        ((cords[i]?.left?.toInt() ?: 0) + padding).toInt(),
                        (cords[i]?.top?.toInt() ?: 0),
                        ((cords[i]?.right?.toInt() ?: 0) - padding).toInt(),
                        (charBottom[i].toInt())
                    )
                    pinBackground?.draw(canvas)
                }

                val middle = (cords[i]?.left ?: 0f) + charSize / 2
                if (textLength > i) {
                    if (i != textLength - 1) {
                        canvas.drawText(
                            fullText,
                            i,
                            i + 1,
                            middle - textWidths[i] / 2,
                            charBottom[i] - 4,
                            charPaint
                        )
                    } else {
                        canvas.drawText(
                            fullText,
                            i,
                            i + 1,
                            middle - textWidths[i] / 2,
                            charBottom[i] - 4,
                            lastCharPaint
                        )

                    }
                }
                i++
            }
        }
    }

    /**
     * Request focus on this PinEntryEditText
     */
    fun focus() {
        requestFocus()

        // Show keyboard
        val inputMethodManager = context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, 0)
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {

        if (lineCords == null) {
            if (text.length == maxLength) {
                onPinEnteredListener?.onPinEntered(text)
            }
            return
        }
        if (lengthAfter > lengthBefore) {
            animatePopIn()
        }
    }

    private fun animatePopIn() {
        val va = ValueAnimator.ofFloat(1f, paint.textSize)
        va.duration = DEFAULT_ANIMATION_DURATION
        va.interpolator = OvershootInterpolator()
        va.addUpdateListener { animation ->
            lastCharPaint.textSize = (animation.animatedValue as Float)
            this@SPOtpEditText.invalidate()
        }
        if (fullText.length == maxLength) {
            va.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    onPinEnteredListener?.onPinEntered(fullText)
                }
            })
        }
        va.start()
    }

    companion object {
        private const val DEFAULT_ANIMATION_DURATION = 200L
        private const val DEFAULT_FLOAT = 0f
    }
}