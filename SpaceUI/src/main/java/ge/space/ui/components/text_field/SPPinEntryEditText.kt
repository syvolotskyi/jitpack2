package ge.space.ui.components.text_field

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
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import ge.space.spaceui.R
import ge.space.ui.components.text_field.SPPinEntryEditText.PinType.OTP
import ge.space.ui.components.text_field.SPPinEntryEditText.PinType.Password
import java.util.*

class SPPinEntryEditText : AppCompatEditText {

    /**
     * Sets disable copy-paste.
     */
    private var isDisableCopyPaste = false
        set(value) {
            field = value

            if (isDisableCopyPaste) {
                disableCopyPaste()
            }
        }

    /**
     * Sets a error.
     */
    var isError: Boolean
        get() = hasError
        set(hasError) {
            this.hasError = hasError
            if (hasError) updateDrawableState(hasText = false, isNext = false)
        }

    var onPinEnteredListener: OnPinEnteredListener? = null

    /**
     * Sets a pin type - password or OTP.
     */
    private var pinType = Password
        set(value) {
            field = value

            if (value == Password) {
                handlePassportInputState()
            } else {
                handleOTPInputState()
            }
        }

    private var charSize = DEFAULT_FLOAT
    private var numChars = DEFAULT_FLOAT
    private var maxLength = DEFAULT_LENGTH
    private var lineCords: Array<RectF?>? = null
    private var pinBackground: Drawable? = null
    private var textHeight = Rect()

    private val space: Float by lazy { resources.getDimension(R.dimen.dimen_p_16) }
    private val pinWidth: Float by lazy { resources.getDimension(R.dimen.dimen_p_16) }
    private val lineStroke: Float by lazy { resources.getDimension(R.dimen.dimen_p_1) }

    private var hasError = false
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

    fun setMaxLength(maxLength: Int) {
        val params = this.layoutParams
        params.width = (pinWidth * maxLength).toInt() +
                (space * maxLength).withPadding()
        this.layoutParams = params
        requestLayout()
        applyMaxLength(maxLength)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val ta =
            context.obtainStyledAttributes(attrs, R.styleable.SPPinEntryEditText, 0, 0)

        ta.run {
            val pinTypeId = getInt(
                R.styleable.SPPinEntryEditText_sp_pinType, DEFAULT_PIN_TYPE
            )

            movementMethod = null
            pinType = PinType.values()[pinTypeId]
            isDisableCopyPaste =
                getBoolean(R.styleable.SPPinEntryEditText_sp_disableCopyPaste, false)
            pinBackground = if (pinType == Password) {
                ContextCompat.getDrawable(context, R.drawable.circle_pin_background)
            } else {
                ContextCompat.getDrawable(context, R.drawable.number_pin_background)
            }

            recycle()
        }

        charPaint = Paint(paint)
        lastCharPaint = Paint(paint)
        linesPaint = Paint(paint)
        linesPaint.strokeWidth = lineStroke
        maxLength = attrs.getAttributeIntValue(XML_NAMESPACE_ANDROID, "maxLength", DEFAULT_LENGTH)
        numChars = maxLength.toFloat()

        //Height of the characters, used if there is a background drawable
        paint.getTextBounds("|", 0, 1, textHeight)
    }

    fun setStyle(@StyleRes defStyleRes: Int) {

        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPPinEntryEditText)
        styleAttrs.run {
            val pinTypeId = getInt(
                R.styleable.SPPinEntryEditText_sp_pinType, DEFAULT_PIN_TYPE
            )
            isCursorVisible = false
            isClickable = false
            setTextIsSelectable(false)
            includeFontPadding = false

            pinType = PinType.values()[pinTypeId]
            isDisableCopyPaste =
                getBoolean(R.styleable.SPPinEntryEditText_sp_disableCopyPaste, false)
            setTextColor(
                getColor(
                    R.styleable.SPPinEntryEditText_android_textColor,
                    ContextCompat.getColor(context, R.color.appPrimaryColor)
                )
            )
            pinBackground = if (pinType == Password) {
                ContextCompat.getDrawable(context, R.drawable.circle_pin_background)
            } else {
                ContextCompat.getDrawable(context, R.drawable.number_pin_background)
            }
            recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalTextColors = textColors
        lastCharPaint.color = originalTextColors.defaultColor
        charPaint.color = originalTextColors.defaultColor

        val availableWidth =
            width - ViewCompat.getPaddingEnd(this) - ViewCompat.getPaddingStart(this)
        charSize = if (space < 0) {
            availableWidth / (numChars * 2 - 1)
        } else {
            (availableWidth - space * numChars) / (numChars - 1)
        }
        lineCords = arrayOfNulls(numChars.toInt())
        charBottom = FloatArray(numChars.toInt())
        var startX: Int
        val bottom = height - paddingBottom
        val rtlFlag: Int
        val isLayoutRtl =
            TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_RTL
        if (isLayoutRtl) {
            rtlFlag = -1
            startX = (width - ViewCompat.getPaddingStart(this) - charSize).toInt()
        } else {
            rtlFlag = 1
            startX = ViewCompat.getPaddingStart(this)
        }
        var i = 0
        while (i < numChars) {
            lineCords?.let { cords ->
                cords[i] = RectF(
                    startX.toFloat(),
                    top.toFloat(),
                    startX + charSize,
                    bottom.toFloat()
                )
                if (pinBackground != null) {
                    if (pinType == Password) {
                        cords[i]?.top = paddingTop.toFloat()
                        cords[i]?.right = startX + (cords[i]?.height() ?: 0f)
                    } else {
                        cords[i]?.top = cords[i]?.top?.minus(textHeight.height())
                    }
                }
                startX += if (space < 0) {
                    (rtlFlag * charSize * 2).toInt()
                } else {
                    (rtlFlag * (charSize + space)).toInt()
                }
                charBottom[i] = (cords[i]?.bottom ?: 0f) - DEFAULT_TEXT_BOTTOM_PADDING
            }
            i++
        }
    }

    private fun handlePassportInputState() {
        setTextColor(ContextCompat.getColor(context, android.R.color.transparent))
        setPadding(
            resources.getDimensionPixelSize(R.dimen.dimen_p_16),
            resources.getDimensionPixelSize(R.dimen.dimen_p_10),
            resources.getDimensionPixelSize(R.dimen.dimen_p_16),
            resources.getDimensionPixelSize(R.dimen.dimen_p_10)
        )
    }

    private fun handleOTPInputState() {
        setBackgroundResource(R.drawable.sp_otp_code_shape_background)
        setPadding(
            resources.getDimensionPixelSize(R.dimen.dimen_p_16),
            0,
            resources.getDimensionPixelSize(R.dimen.dimen_p_16),
            resources.getDimensionPixelSize(R.dimen.dimen_p_8)
        )
    }

    private fun applyMaxLength(maxLength: Int) {
        this.maxLength = maxLength
        numChars = maxLength.toFloat()
        filters = arrayOf<InputFilter>(LengthFilter(maxLength))
        text = null
        invalidate()
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
                if (pinBackground != null && (pinType == Password || textLength <= i)) {
                    updateDrawableState(i < textLength, i == textLength)
                    pinBackground?.setBounds(
                        cords[i]?.left?.toInt() ?: 0,
                        (cords[i]?.top?.toInt() ?: 0) - DEFAULT_TEXT_BOTTOM_PADDING.toInt(),
                        cords[i]?.right?.toInt() ?: 0,
                        (cords[i]?.bottom?.toInt() ?: 0) - DEFAULT_TEXT_BOTTOM_PADDING.toInt()
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
                            charBottom[i],
                            charPaint
                        )
                    } else {
                        canvas.drawText(
                            fullText,
                            i,
                            i + 1,
                            middle - textWidths[i] / 2,
                            charBottom[i],
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

    private fun Float.withPadding(): Int {
        return this.toInt() + ViewCompat.getPaddingEnd(this@SPPinEntryEditText)
    }

    private fun updateDrawableState(hasText: Boolean, isNext: Boolean) {
        if (hasError) {
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
            this@SPPinEntryEditText.invalidate()
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

    /**
     * Enum class which is for Pin Type.
     *
     * @property Password sets view as password input field.
     * @property OTP sets view as OTP input field.
     */
    enum class PinType {
        Password,
        OTP
    }

    companion object {
        private const val XML_NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android"
        private const val DEFAULT_ANIMATION_DURATION = 200L
        private const val DEFAULT_LENGTH = 4
        private const val DEFAULT_PIN_TYPE = 0
        private const val DEFAULT_TEXT_BOTTOM_PADDING = 10f
        private const val DEFAULT_FLOAT = 0f
    }
}