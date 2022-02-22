package ge.space.ui.components.buttons

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.setHeight
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonLayoutBinding
import ge.space.ui.base.OnDistractiveInterface
import ge.space.ui.components.buttons.SPButton.IconDirection
import ge.space.ui.components.buttons.SPButton.IconDirection.*
import ge.space.ui.components.buttons.base.SPButtonBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.util.extension.getColorFromTextAppearance
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setCompoundDrawablesTint

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
 *  This property can have a value from [IconDirection.Right], [IconDirection.Left],
 *  [IconDirection.None].
 * @property isDistractive [Boolean] value sets distractive state
 */
class SPButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPButton_Primary_Size48
) : SPButtonBaseView<SpButtonLayoutBinding>(context, attrs, defStyleAttr, defStyleRes),
    OnDistractiveInterface {

    /**
     * Makes a button icon direction.
     */
    var directionIcon = None
        set(value) {
            field = value

            handleDirectionArrow()
        }

    override var isDistractive: Boolean = false
        set(value) {
            field = value

            handleDistractiveState()
        }

    /**
     * Sets a image resource
     */
    @IdRes
    var src = SPTextFieldBaseView.DEFAULT_INT
        set(value) {
            field = value

            handleDirectionArrow()
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = SPTextFieldBaseView.DEFAULT_INT

    /**
     * Sets a distractive text appearance
     */
    @StyleRes
    private var distractiveTextAppearance: Int = SPTextFieldBaseView.DEFAULT_INT

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

    fun updateTextAppearance(textAppearance: Int) {
        binding.buttonLabel.setTextStyle(textAppearance)
        binding.buttonLabel.setCompoundDrawablesTint(context.getColorFromTextAppearance(textAppearance))
    }

    override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    private fun handleDirectionArrow() {
        if (src != SPTextFieldBaseView.DEFAULT_INT)
            when (directionIcon) {
                None -> directNone()
                Left -> directLeft()
                Right -> directRight()
            }
        updateTextAppearance(textAppearance)
    }

    /**
     * remove all drawables
     */
    private fun directNone() {
        binding.buttonLabel.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
    }

    /**
     * sets left drawable only
     */
    private fun directLeft() {
        binding.buttonLabel.setCompoundDrawablesWithIntrinsicBounds(
            ContextCompat.getDrawable(context, src),
            null,
            null,
            null
        )
    }

    /**
     * sets right drawable only
     */
    private fun directRight() {
        binding.buttonLabel.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(context, src),
            null
        )
    }


    override fun handleDistractiveState() {
        color = if (isDistractive) distractiveBackground else background
        updateTextAppearance(if (isDistractive) distractiveTextAppearance else textAppearance)
    }

    /**
     * Enum class which is for Button arrow direction.
     *
     * @property None removes all arrows. Just to show only text.
     * @property Left applies an arrow left from the text.
     * @property Right applies an arrow right from the text.
     */
    enum class IconDirection {
        None,
        Left,
        Right
    }
}