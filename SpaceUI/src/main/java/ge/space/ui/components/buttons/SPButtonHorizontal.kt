package ge.space.ui.components.buttons

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ge.space.extensions.setHeight
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonHorizontalLayoutBinding
import ge.space.ui.components.buttons.base.SPButtonBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView

/**
 * Button view extended from [SPButtonBaseView] that allows to change its configuration.
 *
 * @property src [Int] value which applies a button image using a resource ID.
 * @property distractiveColor [Int] value sets image color in distractive state
 */
class SPButtonHorizontal @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPButtonBaseView<SpButtonHorizontalLayoutBinding>(context, attrs, defStyleAttr) {

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
            R.styleable.SPButtonHorizontal,
            defStyleAttr
        ) {
            setButtonStyle(
                getResourceId(R.styleable.SPButtonHorizontal_style, R.style.SPButton_Hollow_Size48)
            )
            src = getResourceId(R.styleable.SPButtonHorizontal_android_src, 0)
            text = getString(R.styleable.SPButtonHorizontal_android_text).orEmpty()
        }
    }

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
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.sp_button_view_style)

        styleAttrs.run {

            text = getString(R.styleable.SPButtonHorizontal_android_text).orEmpty()
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
                DEFAULT_OBTAIN_VAL
            )
            binding.buttonContentWrapper.setHeight(resources.getDimensionPixelSize(buttonHeight))
            recycle()
        }
    }

    override fun handleDistractiveState() {
        updateTextAppearance(if (isDistractive) distractiveTextAppearance else textAppearance)
        binding.ivRight.setColorFilter(
            if (isDistractive) distractiveColor
            else
                ContextCompat.getColor(
                    context,
                    R.color.appPrimaryColor
                ), android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    fun updateTextAppearance(textAppearance: Int) =
        binding.buttonLabel.setTextStyle(textAppearance)

}