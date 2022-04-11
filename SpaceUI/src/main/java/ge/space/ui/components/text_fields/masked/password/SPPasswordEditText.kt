package ge.space.ui.components.text_fields.masked.password

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import ge.space.spaceui.R
import ge.space.ui.components.text_fields.masked.base.OnPinEnteredListener
import ge.space.ui.components.text_fields.masked.base.SPPinBaseEditText.Companion.DEFAULT_LENGTH
import ge.space.ui.util.extension.EMPTY_TEXT

internal class SPPasswordEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs) {

    /**
     * Sets a error.
     */
    var isError: Boolean = false
        set(value) {
            field = value
            if (value) updateDrawableState(hasText = false, isNext = false)
        }

    var onPinEnteredListener: OnPinEnteredListener? = null
    private var maxLength = DEFAULT_LENGTH
    private var lineCords: Array<RectF?>? = null
    private var pinBackground: Drawable? = null
    private val space: Float by lazy { resources.getDimension(R.dimen.sp_password_text_space) }
    private val pinWidth: Float by lazy { resources.getDimension(R.dimen.sp_password_text_width) }
    private val fullText: CharSequence
        get() = text ?: EMPTY_TEXT
    private lateinit var charBottom: FloatArray

    init {
        movementMethod = null
        setTextColor(ContextCompat.getColor(context, android.R.color.transparent))
        pinBackground =
            ContextCompat.getDrawable(context, R.drawable.bg_pin_circle)

        //Height of the characters, used if there is a background drawable
        paint.getTextBounds("|", 0, 1, Rect())
    }

    fun setStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPPinEntryEditText)
        styleAttrs.run {
            isCursorVisible = false
            isClickable = false
            setTextIsSelectable(false)
            includeFontPadding = false
            setTextColor(ContextCompat.getColor(context, android.R.color.transparent))
            pinBackground = ContextCompat.getDrawable(context, R.drawable.bg_pin_circle)
            recycle()
        }
    }

    fun setMaxLength(maxLength: Int) {
        val params = this.layoutParams
        params.width = ((pinWidth * maxLength) +
                (space * (maxLength - 1))).toInt()
        params.height = (pinWidth).toInt()

        this.layoutParams = params
        this.maxLength = maxLength
        filters = arrayOf<InputFilter>(LengthFilter(maxLength))
        text = null
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val charSize = (width - space * maxLength) / (maxLength - 1)

        lineCords = arrayOfNulls(maxLength)
        charBottom = FloatArray(maxLength)
        var startX: Int
        val bottom = height
            startX = ViewCompat.getPaddingStart(this)
        var i = 0
        while (i < maxLength) {
            lineCords?.let { cords ->
                cords[i] = RectF(
                    startX.toFloat(),
                    top.toFloat(),
                    startX + charSize,
                    bottom.toFloat()
                )
                pinBackground?.let {
                    cords[i]?.top = paddingTop.toFloat()
                    cords[i]?.right = startX + (cords[i]?.height() ?: 0f)
                }
                startX += if (space < 0) {
                    (charSize * 2).toInt()
                } else {
                    ((charSize + space)).toInt()
                }
                charBottom[i] = (cords[i]?.bottom ?: 0f)
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
        while (i < maxLength) {
            lineCords?.let { cords ->
                if (pinBackground != null) {
                    updateDrawableState(i < textLength, i == textLength)
                    pinBackground?.setBounds(
                        cords[i]?.left?.toInt() ?: 0,
                        (cords[i]?.top?.toInt() ?: 0),
                        cords[i]?.right?.toInt() ?: 0,
                        (cords[i]?.bottom?.toInt() ?: 0)
                    )
                    pinBackground?.draw(canvas)
                }
                i++
            }
        }
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
        if (lengthAfter > lengthBefore && fullText.length == maxLength) {
            onPinEnteredListener?.onPinEntered(fullText)
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

    private fun updateDrawableState(hasText: Boolean, isNext: Boolean) {
        if (isError) {
            pinBackground?.state = intArrayOf(android.R.attr.state_active)
        } else if (isFocused) {
            pinBackground?.state = intArrayOf(android.R.attr.state_focused)
            if (isNext) {
                pinBackground?.state =
                    intArrayOf(android.R.attr.state_focused, android.R.attr.state_selected)
            } else if (hasText) {
                pinBackground?.state =
                    intArrayOf(android.R.attr.state_focused, android.R.attr.state_checked)
            }
        } else {
            if (isNext) {
                pinBackground?.state =
                    intArrayOf(android.R.attr.state_focused, android.R.attr.state_selected)
            } else if (hasText) {
                pinBackground?.state =
                    intArrayOf(android.R.attr.state_focused, android.R.attr.state_checked)
            }
        }
    }
}