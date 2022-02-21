package ge.space.ui.components.buttons

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.setHeight
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonHorizontalLayoutBinding
import ge.space.ui.base.OnDistractiveInterface
import ge.space.ui.components.buttons.base.SPButtonBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.handleAttributeAction

/**
 * Button view extended from [SPButtonBaseView] that allows to change its configuration.
 *
 * @property src [Int] value which applies a button image using a resource ID.
 * @property isDistractive [Boolean] value sets distractive state
 */
class SPButtonHorizontal @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPButton_Horizontal_Size48
) : SPButtonBaseView<SpButtonHorizontalLayoutBinding>(context, attrs, defStyleAttr, defStyleRes), OnDistractiveInterface {

    /**
     * Inflates and returns [SpButtonHorizontalLayoutBinding] value
     */
    override fun getViewBinding(): SpButtonHorizontalLayoutBinding =
        SpButtonHorizontalLayoutBinding.inflate(LayoutInflater.from(context), this)

    /**
     * Sets a image resource
     */
    @IdRes
    var src = 0
        set(value) {
            field = value

            binding.ivRight.setImageResource(src)
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = SPTextFieldBaseView.DEFAULT_INT


    override var isDistractive: Boolean = false
        set(value) {
            field = value

            handleDistractiveState()
        }

    /**
     *  it is a specific state for buttons.
     *
     *  For example, we have two buttons - "Accept" and "Decline",
     *  and in our case "decline" buttons is with distractive = true attribute
     */
    @StyleRes
    private var distractiveTextAppearance: Int = SPTextFieldBaseView.DEFAULT_INT

    /**
     *  it is a distractive color for image color filter.
     */
    private var distractiveColor: Int = Color.WHITE


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
            R.styleable.SPButtonHorizontal,
            defStyleAttr,
            defStyleRes
        ) {
            applyHorizontalStyleAttrs()
        }
    }

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
        context.withStyledAttributes(styleRes, R.styleable.sp_button_view_style){
            applyStyledResource()
        }
        context.withStyledAttributes(styleRes, R.styleable.SPButtonHorizontal){
            applyHorizontalStyleAttrs()
        }
    }

    private fun TypedArray.applyHorizontalStyleAttrs() {
        src = getResourceId(R.styleable.SPButtonHorizontal_android_src, 0)
        getString(R.styleable.SPButtonHorizontal_android_text).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                text = it
            }
    }

    private fun TypedArray.applyStyledResource() {
        textAppearance =
            getResourceId(
                R.styleable.sp_button_view_style_android_textAppearance,
                DEFAULT_OBTAIN_VAL
            )

        distractiveTextAppearance = getResourceId(
            R.styleable.sp_button_view_style_distractiveTextAppearance,
            DEFAULT_OBTAIN_VAL
        )
        distractiveColor =
            getColor(R.styleable.sp_button_view_style_distractiveColor, Color.WHITE)
        updateTextAppearance(textAppearance)

        val buttonHeight = getResourceId(
            R.styleable.sp_button_view_style_buttonHeight,
            R.dimen.dimen_p_48
        )
        binding.buttonContentWrapper.setHeight(resources.getDimensionPixelSize(buttonHeight))
    }


    override fun handleDistractiveState() {
        updateTextAppearance(if (isDistractive) distractiveTextAppearance else textAppearance)
        binding.ivRight.setColorFilter(
            if (isDistractive) distractiveColor
            else
                context.getColorFromAttribute(R.attr.brand_primary),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    fun updateTextAppearance(textAppearance: Int) =
        binding.buttonLabel.setTextStyle(textAppearance)

}