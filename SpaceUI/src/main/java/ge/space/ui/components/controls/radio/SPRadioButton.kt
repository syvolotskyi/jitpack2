package ge.space.ui.components.controls.radio

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.extensions.*
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpRadioButtonBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.util.extension.handleAttributeAction

class SPRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPRadioButtonBase
) : LinearLayout(context, attrs, defStyleAttr), SPViewStyling {

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var titleTextAppearance: Int = SPTextFieldBaseView.DEFAULT_INT

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var descriptionTextAppearance: Int = SPTextFieldBaseView.DEFAULT_INT


    var description: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.descriptionText.text = description
        }

    var isChecked: Boolean = false
        set(value) {
            field = value

            binding.textView.isChecked = value
        }

    var hasDescription: Boolean = false
        set(value) {
            field = value

            binding.descriptionText.isVisible = value
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
        getString(
            R.styleable.SPRadioButton_descriptionText
        ).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                description = it
            }

        val text = getString(
            R.styleable.SPRadioButton_android_text
        ).orEmpty()

        hasDescription = getBoolean(R.styleable.SPRadioButton_hasDescription, false)

        getResourceId(
            R.styleable.SPRadioButton_android_textAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            titleTextAppearance = it
        }

        getResourceId(
            R.styleable.SPRadioButton_descriptionTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            descriptionTextAppearance = it
        }

        binding.textView.text = text
        updateTextAppearance(titleTextAppearance, descriptionTextAppearance)
    }

    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPRadioButton
        ) {
            applyStyledAttributes()
        }
    }

    fun updateTextAppearance(
        textAppearance: Int,
        descriptionTextAppearance: Int? = null
    ) {
        binding.textView.setTextStyle(textAppearance)
        descriptionTextAppearance?.let {
            binding.descriptionText.setTextStyle(
                descriptionTextAppearance
            )
        }
    }
}