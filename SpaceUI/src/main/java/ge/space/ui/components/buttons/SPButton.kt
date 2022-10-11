package ge.space.ui.components.buttons

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonLayoutBinding
import ge.space.ui.base.SPDistractiveMode
import ge.space.ui.components.buttons.SPButton.IconDirection
import ge.space.ui.components.buttons.SPButton.IconDirection.*
import ge.space.ui.components.buttons.base.SPButtonBaseView
import ge.space.ui.util.extension.*

/**
 * Button view extended from abstract [SPButtonBaseView] generic that allows to change its configuration.
 * There are 4 realized styles which can be applied to the view:
 *
 * <p>
 *     1. SPBaseView.SPBaseButton.Primary
 *     2. SPBaseView.SPBaseButton.Secondary
 *     3. SPBaseView.SPBaseButton.Hollow
 * <p>
 *
 * @property directionIcon [IconDirection] value which applies a button icon direction.
 *  This property can have a value from [IconDirection.RIGHT], [IconDirection.LEFT],
 *  [IconDirection.NONE].
 * @property isDistractive [Boolean] value sets distractive state
 */
class SPButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPButton_Primary_Size48
) : SPButtonBaseView<SpButtonLayoutBinding>(context, attrs, defStyleAttr, defStyleRes) , SPDistractiveMode{

    /**
     * Makes a button icon direction.
     */
    private var directionIcon = NONE

    override var isDistractive: Boolean = false
        set(value) {
            field = value

            handleDistractiveState()
        }

    /**
     * Sets a image resource
     */
     @DrawableRes private var src = DEFAULT_INT

    /**
     * Sets a text appearance
     */
    @StyleRes
    var textAppearance: Int = DEFAULT_INT

    /**
     * Sets a distractive text appearance
     */
    @StyleRes
    private var distractiveTextAppearance: Int = DEFAULT_INT

    /**
     * Sets a distractive Background
     */
    private var distractiveBackground: Int = color

    /**
     * Saved origin background to have a possibility to switch back from distractive mode
     */
    private var background: Int = color

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_button_view_style,
            defStyleAttr,
            defStyleRes
        ) {
            applyStyledResource()
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPButton,
            defStyleAttr,
            defStyleRes
        ) {
            applyButtonStylesResource()
        }
    }

    /**
     * Inflates and returns [SpButtonLayoutBinding] value
     */
    override fun getViewBinding(): SpButtonLayoutBinding =
        SpButtonLayoutBinding.inflate(LayoutInflater.from(context), this)

    /**
     * Sets a style for the SPButton view.
     *
     * <p>
     * Default style theme is SPBaseView.SPBaseButton style.
     * <p>
     *
     * @param styleRes [Int] style resource id
     */
    override fun setButtonStyle(@StyleRes styleRes: Int) {
        context.withStyledAttributes(styleRes, R.styleable.sp_button_view_style) {
            applyStyledResource()
        }
        context.withStyledAttributes(styleRes,  R.styleable.SPButton) {
            applyButtonStylesResource()
        }
    }

    private fun TypedArray.applyButtonStylesResource() {
        getString(R.styleable.SPButton_android_text).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                text = it
            }

        val directionIconInd = getInt(
            R.styleable.SPButton_directionIcon,
            DEFAULT_OBTAIN_VAL
        )
        directionIcon = values()[directionIconInd]
        src = getResourceId(R.styleable.SPButton_android_src, DEFAULT_OBTAIN_VAL)
        handleDirectionArrow()
        updateTextAppearance(textAppearance)
    }

    private fun TypedArray.applyStyledResource() {
        textAppearance = getResourceId(
            R.styleable.sp_button_view_style_android_textAppearance,
            DEFAULT_OBTAIN_VAL
        )

        val buttonHeight = getResourceId(
            R.styleable.sp_button_view_style_buttonHeight,
            DEFAULT_OBTAIN_VAL
        )

        distractiveTextAppearance = getResourceId(
            R.styleable.sp_button_view_style_distractiveTextAppearance,
            DEFAULT_OBTAIN_VAL
        )

        distractiveBackground = getColor(
            R.styleable.sp_button_view_style_distractiveBackground,
            Color.WHITE
        )

        background = color
        updateTextAppearance(textAppearance)
        binding.buttonContentWrapper.setHeight(resources.getDimensionPixelSize(buttonHeight))
    }

    fun setButtonIcon(icon: Int, direction: IconDirection = NONE) {
        src = icon
        directionIcon = direction
        handleDirectionArrow()
        updateTextAppearance(textAppearance)
    }

    private fun updateTextAppearance(textAppearance: Int) {
        binding.buttonLabel.setTextStyle(textAppearance)
        binding.buttonLabel.setCompoundDrawablesTint(context.getColorFromTextAppearance(textAppearance))
    }

    override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    private fun handleDirectionArrow() {
        if (src != DEFAULT_INT)
            binding.buttonLabel.setCompoundDrawablesWithIntrinsicBounds(
                if (directionIcon == LEFT) ContextCompat.getDrawable(context, src) else null, null,
                if (directionIcon == RIGHT) ContextCompat.getDrawable(context, src) else null, null
            )
    }

    override fun handleDistractiveState() {
        color = if (isDistractive) distractiveBackground else background
        updateTextAppearance(if (isDistractive) distractiveTextAppearance else textAppearance)
    }

    /**
     * Enum class which is for Button arrow direction.
     *
     * @property NONE removes all arrows. Just to show only text.
     * @property LEFT applies an arrow left from the text.
     * @property RIGHT applies an arrow right from the text.
     */
    enum class IconDirection {
        NONE,
        LEFT,
        RIGHT
    }
}