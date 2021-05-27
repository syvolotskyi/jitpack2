package ge.space.ui.view.button

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonLayoutBinding
import ge.space.ui.base.SPBaseButton
import ge.space.ui.util.extension.handleAttributeAction


/**
 * Button view extended from abstract [SPBaseButton] generic that allows to change its configuration.
 * There are 4 realized styles which can be applied to the view:
 *
 * <p>
 *     1. SPBaseView.SPBaseButton.White
 *     2. SPBaseView.SPBaseButton.Accent
 *     3. SPBaseView.SPBaseButton.Dark
 *     4. SPBaseView.SPBaseButton.Transparent
 * <p>
 *
 * @property directionArrow [ArrowDirection] value which applies a button arrow direction.
 *  This property can have a value from [ArrowDirection.Right], [ArrowDirection.Left],
 *  [ArrowDirection.None].
 */
class SPButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseButton<SpButtonLayoutBinding>(context, attrs, defStyleAttr) {

    /**
     * Makes a button arrow direction.
     */
    private var directionArrow = ArrowDirection.None
        set(value) {
            field = value

            handleDirectionArrow()
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPBaseView,
            defStyleAttr
        ) {
            setButtonStyle(
                getResourceId(R.styleable.SPBaseView_sp_viewStyle, R.style.SPBaseView_SPBaseButton)
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPButton,
            defStyleAttr
        ) {
            getString(R.styleable.SPButton_android_text).orEmpty().handleAttributeAction(
                EMPTY_TEXT
            ) {
                text = it
            }
        }
    }

    /**
     * Inflates and returns [SpButtonLayoutBinding] value
     */
    override fun getViewBinding() : SpButtonLayoutBinding =
        SpButtonLayoutBinding.inflate(LayoutInflater.from(context), this)

    /**
     * Sets a style for the SPButton view.
     *
     * <p>
     * Default style theme is SPBaseView.SPBaseButton style.
     * <p>
     *
     * @param defStyleRes [Int] style resource id
     */
    override fun setButtonStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPViewStyle)

        styleAttrs.run {
            textColor = getColor(R.styleable.SPViewStyle_android_textColor, Color.WHITE)
            text = getString(R.styleable.SPButton_android_text).orEmpty()
            fontFamilyId = getResourceId(
                R.styleable.SPViewStyle_android_fontFamily,
                R.font.myriad_geo_bold
            )
            textSize = getDimension(R.styleable.SPViewStyle_android_textSize, FLOAT_ZERO)

            val directionArrowInd = getInt(
                R.styleable.SPViewStyle_sp_directionArrow, DEFAULT_OBTAIN_VAL
            )
            directionArrow = ArrowDirection.values()[directionArrowInd]

            recycle()
        }
    }

    override fun updateTextColor(color: Int) {
        with(binding) {
            buttonLabel.setTextColor(textColor)
            leftArrow.setColorFilter(textColor)
            rightArrow.setColorFilter(textColor)
        }
    }

    override fun updateFontFace(face: Typeface?) {
        binding.buttonLabel.typeface = face
    }

    override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    override fun updateTextSize(textSize: Float) {
        binding.buttonLabel.setTextSize(
            TypedValue.COMPLEX_UNIT_PX, textSize
        )
    }

    private fun handleDirectionArrow() {
        when (directionArrow) {
            ArrowDirection.None -> directNone()
            ArrowDirection.Left -> directLeft()
            ArrowDirection.Right -> directRight()
        }
    }

    private fun directNone() {
        with(binding) {
            leftArrow.isGone = true
            rightArrow.isGone = true
        }
    }

    private fun directLeft() {
        with(binding) {
            leftArrow.isVisible = true
            rightArrow.isGone = true
        }
    }

    private fun directRight() {
        with(binding) {
            leftArrow.isGone = true
            rightArrow.isVisible = true
        }
    }

    /**
     * Enum class which is for Button arrow direction.
     *
     * @property None removes all arrows. Just to show only text.
     * @property Left applies an arrow left from the text.
     * @property Right applies an arrow right from the text.
     */
    enum class ArrowDirection {
        None,
        Left,
        Right
    }

    companion object {
        private const val FLOAT_ZERO = 0f
    }
}