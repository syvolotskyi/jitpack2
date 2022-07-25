package ge.space.ui.components.controls.radio.list_item

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpListItemButtonBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.controls.radio.base.SpBaseRadioButton
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setHeight
import ge.space.ui.util.extension.setTextStyle
import ge.space.ui.util.extension.setWidth

/**
 *  Extended view from [SpBaseRadioButton] contains radio button, startView and title label.
 */
class SPListItemButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPListItemButton
) : SpBaseRadioButton(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding =
        SpListItemButtonBinding.inflate(LayoutInflater.from(context), this, true)

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

        updateTextAppearance(titleTextAppearance)

        title = text
        binding.radioButton.setOnCheckedChangeListener { _, boolean ->
            isChecked = boolean
            binding.background.isSelected = boolean
            binding.titleText.isSelected = boolean
        }
    }

    /**
     * Sets a Start View
     */
    fun setStartView(view: View) {
        binding.startView.removeView(view)
        binding.startView.addView(view)
        invalidate()
    }

    /**
     * Sets a Start View size height and width. Use height is WRAP_CONTENT if put image non from url
     */
    fun setStartViewSize(height: Int, width: Int) {
        binding.startView.setHeight(height)
        binding.startView.setWidth(width)
        invalidate()
    }

    override fun handleTitle(value: String) {
        binding.titleText.text = value
    }

    /**
     * Sets a textAppearance and descriptionTextAppearance to view
     */
    override fun updateTextAppearance(textAppearance: Int) {
        binding.titleText.setTextStyle(textAppearance)
    }

    override fun handleCheckingState() {
        binding.radioButton.apply {
            post {
                isChecked = this@SPListItemButton.isChecked
            }
        }
    }


    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPRadioButton
        ) {
            applyStyledAttributes()
        }
    }
}