package ge.space.ui.components.text_fields.pin

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.AttributeSet
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import ge.space.spaceui.R
import ge.space.ui.util.extension.getColorFromAttribute
import java.util.*

class SPOtpEditText : AppCompatEditText {

    var onPinEnteredListener: OnPinEnteredListener? = null

    private var charSize = DEFAULT_FLOAT
    private var numChars = DEFAULT_FLOAT
    private var maxLength = DEFAULT_LENGTH
    private var lineCords: Array<RectF?>? = null
    private var pinBackground: Drawable? = null

    private val pinWidth: Float by lazy { resources.getDimension(R.dimen.sp_pin_edit_text_width) }
    private val pinHeight: Float by lazy { resources.getDimension(R.dimen.sp_pin_edit_text_height) }
    private val lineStroke: Float by lazy { resources.getDimension(R.dimen.sp_pin_edit_text_line_stroke) }

    private lateinit var originalTextColors: ColorStateList

    private lateinit var charPaint: Paint
    private lateinit var lastCharPaint: Paint
    private lateinit var linesPaint: Paint
    private lateinit var charBottom: FloatArray

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    fun setError(
        isError: Boolean,
        @AttrRes errorColor: Int = context.getColorFromAttribute(R.attr.accent_magenta)
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

    private fun init(context: Context, attrs: AttributeSet) {
        val ta =
            context.obtainStyledAttributes(attrs, R.styleable.SPPinEntryEditText, 0, 0)

        ta.run {
            movementMethod = null

            disableCopyPaste()
            pinBackground =
                ContextCompat.getDrawable(context, R.drawable.bg_pin_number)

            recycle()
        }

        charPaint = Paint(paint)
        lastCharPaint = Paint(paint)
        linesPaint = Paint(paint)
        linesPaint.strokeWidth = lineStroke
        maxLength = attrs.getAttributeIntValue(XML_NAMESPACE_ANDROID, "maxLength", DEFAULT_LENGTH)
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

            disableCopyPaste()
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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalTextColors = textColors
        lastCharPaint.color = originalTextColors.defaultColor
        charPaint.color = originalTextColors.defaultColor

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


    private val fullText: CharSequence
        get() = text ?: ""


    private fun disableCopyPaste() {
        super.setCustomSelectionActionModeCallback(object : ActionMode.Callback {
            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode) {}
            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                return false
            }
        })
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
                override fun onAnimationEnd(animation: Animator) {
                    onPinEnteredListener?.onPinEntered(fullText)
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
        }
        va.start()
    }

    companion object {
        private const val XML_NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android"
        private const val DEFAULT_ANIMATION_DURATION = 200L
        private const val DEFAULT_LENGTH = 4
        private const val DEFAULT_FLOAT = 0f
    }
}