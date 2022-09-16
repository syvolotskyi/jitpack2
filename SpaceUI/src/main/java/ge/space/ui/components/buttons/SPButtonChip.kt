package ge.space.ui.components.buttons

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonChipLayoutBinding
import ge.space.ui.components.buttons.base.SPButtonBaseView
import ge.space.ui.util.extension.*

class SPButtonChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPButtonChip
) : SPButtonBaseView<SpButtonChipLayoutBinding>(context, attrs, defStyleAttr, defStyleRes) {
    /**
     * Sets a image resource
     */
    @IdRes
    var src = DEFAULT_INT
        set(value) {
            field = value

            handleIconButton()
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = R.style.h700_bold_caps_brand_primary

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
            withStyledResource()
        }
    }

    /**
     * Inflates and returns [SpButtonChipLayoutBinding] value
     */
    override fun getViewBinding(): SpButtonChipLayoutBinding =
        SpButtonChipLayoutBinding.inflate(LayoutInflater.from(context), this)

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
        context.withStyledAttributes(styleRes, R.styleable.SPButtonChip) {
            withStyledResource()
        }
    }

    private fun TypedArray.withStyledResource() {
        getString(R.styleable.SPButtonChip_android_text).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                text = it
            }

        getResourceId(R.styleable.SPButtonChip_android_src, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                src = it
            }
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
        updateTextAppearance()
        binding.buttonContentWrapper.setHeight(resources.getDimensionPixelSize(buttonHeight))
    }

    fun updateTextAppearance() {
        binding.buttonLabel.setTextStyle(textAppearance)
        binding.buttonIcon.tintColor = context.getColorFromTextAppearance(textAppearance)
    }

    override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    private fun handleIconButton() {
        if (src != DEFAULT_OBTAIN_VAL) {
            binding.buttonIcon.setImageResource(src)
        }
    }
}