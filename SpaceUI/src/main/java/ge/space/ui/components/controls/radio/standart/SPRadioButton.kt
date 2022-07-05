package ge.space.ui.components.controls.radio.standart

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpRadioButtonBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_INT
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.controls.radio.base.SpBaseRadioButton
import ge.space.ui.util.extension.*
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setTextStyle
import ge.space.ui.util.extension.visibleIf

/**
 *  Extended view from [SpBaseRadioButton] contains radio button and description label.
 */
class SPRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPRadioButton_Standard
) : SpBaseRadioButton(context, attrs, defStyleAttr), SPViewStyling {

    /**
     * Sets a description text
     */
    var description: String = EMPTY_TEXT
        set(value) {
            field = value

            handleDesc()
        }

    /**
     * Sets a text appearance in case if view description isn't empty
     */
    @StyleRes
    private var titleTextAppearanceWithDesc: Int = DEFAULT_INT

    /**
     * Sets a description appearance
     */
    @StyleRes
    private var descriptionTextAppearance: Int = DEFAULT_INT

    /**
     * Sets a button resource
     */
    var button: Int? = null
        set(value) {
            field = value

            handleButtonColor(value)
        }

    private fun handleButtonColor(value: Int?) {
        value?.let { binding.radioButton.setButtonDrawable(it) }
        binding.radioButton.backgroundTintList = ColorStateList.valueOf(
            context.getColorFromAttribute(R.attr.brand_primary)
        )
    }

    private val binding =
        SpRadioButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPRadioButton,
            defStyleAttr,
            defStyleRes
        ) {
            applyStyledAttributes()
        }
    }

    private fun TypedArray.applyStyledAttributes() {

        val text = getString(
            R.styleable.SPRadioButton_android_text
        ).orEmpty()

        getResourceId(
            R.styleable.SPRadioButton_titleTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            titleTextAppearance = it
        }

        getResourceId(
            R.styleable.SPRadioButton_titleTextAppearanceWithDesc,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            titleTextAppearanceWithDesc = it

            updateTextAppearance(titleTextAppearance)
        }

        getResourceId(
            R.styleable.SPRadioButton_android_button,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            button = it
        }

        getResourceId(
            R.styleable.SPRadioButton_descriptionTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            descriptionTextAppearance = it

            updateDescTextAppearance(descriptionTextAppearance)
        }

        getString(
            R.styleable.SPRadioButton_descriptionText
        ).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                description = it
            }

        binding.titleText.text = text
    }

    /**
     * Sets a description textAppearance to view
     */
    fun updateDescTextAppearance(descriptionTextAppearance: Int) {
        binding.descriptionText.setTextStyle(descriptionTextAppearance)
    }

    /**
     * Sets a textAppearance to view
     */
    override fun updateTextAppearance(
        textAppearance: Int
    ) {
        binding.titleText.setTextStyle(textAppearance)
    }

    override fun handleTitle(value: String) {
        binding.titleText.text = value
        requestLayout()
    }

    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPRadioButton
        ) {
            applyStyledAttributes()
        }
    }

    override fun handleCheckingState() {
        binding.radioButton.isChecked = isChecked
    }

    private fun handleDesc() {
        binding.descriptionText.text = description
        binding.descriptionText.visibleIf(description.isNotEmpty())

        updateTextAppearance(
            textAppearance = if (description.isEmpty())
                titleTextAppearance
            else titleTextAppearanceWithDesc
        )

        updateDescTextAppearance(descriptionTextAppearance)
    }
}