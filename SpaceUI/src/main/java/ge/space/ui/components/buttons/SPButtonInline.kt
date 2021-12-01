package ge.space.ui.components.buttons

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.setTextStyle
import ge.space.extensions.setWidth
import ge.space.extensions.tintColor
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonInlineLayoutBinding
import ge.space.ui.components.buttons.SPButtonInline.ButtonGravity.Center
import ge.space.ui.components.buttons.SPButtonInline.ButtonGravity.Left
import ge.space.ui.components.buttons.base.SPButtonBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.util.extension.getColorFromTextAppearance
import ge.space.ui.util.extension.handleAttributeAction

class SPButtonInline @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPButtonInline_Primary
) : SPButtonBaseView<SpButtonInlineLayoutBinding>(context, attrs, defStyleAttr) {

    /**
     * Makes a button gravity.
     */
    var buttonGravity = Left
        set(value) {
            field = value

            handleGravity()
        }

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
     * Sets a description text
     */
    var description = EMPTY_TEXT
        set(value) {
            field = value

            binding.buttonDesc.text = description
            binding.buttonDesc.isVisible = description.isNotEmpty()
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = R.style.h700_bold_caps_brand_primary

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var descriptionTextAppearance: Int = DEFAULT_INT

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPBaseView,
            defStyleAttr
        ) {
            setViewStyle(
                getResourceId(
                    R.styleable.SPBaseView_style,
                    defStyleRes
                )
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPButtonInline,
            defStyleAttr
        ) {
            getString(R.styleable.SPButtonInline_android_text).orEmpty()
                .handleAttributeAction(EMPTY_TEXT) {
                    text = it
                }

            val gravityInd = getInt(
                R.styleable.SPButtonInline_containerGravity,
                DEFAULT_OBTAIN_VAL
            )

            descriptionTextAppearance = getResourceId(
                R.styleable.SPButtonInline_descriptionTextAppearance,
                DEFAULT_OBTAIN_VAL
            )
            getString(
                R.styleable.SPButtonInline_description
            ).orEmpty().handleAttributeAction(EMPTY_TEXT) {
                description = it
            }

            buttonGravity = ButtonGravity.values()[gravityInd]
            getResourceId(R.styleable.SPButtonInline_android_src, DEFAULT_OBTAIN_VAL)
                .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                    if (it != DEFAULT_OBTAIN_VAL)
                        src = it
                }
            updateTextAppearance()
        }
    }

    /**
     * Inflates and returns [SpButtonInlineLayoutBinding] value
     */
    override fun getViewBinding(): SpButtonInlineLayoutBinding =
        SpButtonInlineLayoutBinding.inflate(LayoutInflater.from(context), this)

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
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPButtonInline)

        styleAttrs.run {
            withStyledResource()
            recycle()
        }
    }

    private fun TypedArray.withStyledResource() {
        getString(R.styleable.SPButtonInline_android_text).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                text = it
            }

        val gravityInd = getInt(
            R.styleable.SPButtonInline_containerGravity,
            DEFAULT_OBTAIN_VAL
        )

        descriptionTextAppearance = getResourceId(
            R.styleable.SPButtonInline_descriptionTextAppearance,
            DEFAULT_OBTAIN_VAL
        )
        textAppearance = getResourceId(
            R.styleable.sp_button_view_style_android_textAppearance,
            R.style.h700_bold_caps_brand_primary
        )
        getString(
            R.styleable.SPButtonInline_description
        ).orEmpty().handleAttributeAction(EMPTY_TEXT) {
            description = it
        }
        buttonGravity = ButtonGravity.values()[gravityInd]
        getResourceId(R.styleable.SPButtonInline_android_src, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                src = it
            }
        updateTextAppearance()
    }

    fun updateTextAppearance() {
        binding.buttonLabel.setTextStyle(textAppearance)
        binding.buttonDesc.setTextStyle(descriptionTextAppearance)
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

    private fun handleGravity() {
        if (buttonGravity == ButtonGravity.Left) {
            binding.buttonContentWrapper.setWidth(MATCH_PARENT)
        } else {

            binding.buttonContentWrapper.setWidth(WRAP_CONTENT)
        }
    }

    /**
     * Enum class which is for Button gravity direction.
     *
     * @property Left applies views by the left side.
     * @property Center applies views by the  center.
     */
    enum class ButtonGravity {
        Left,
        Center
    }
}