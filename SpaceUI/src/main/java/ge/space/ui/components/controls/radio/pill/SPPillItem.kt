package ge.space.ui.components.controls.radio.pill

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPillLayoutBinding
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.controls.radio.base.SpBaseRadioButton
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.onClick
import ge.space.ui.util.extension.setTextStyle

/**
 *  SpPillItem view extended from [SpBaseRadioButton] that allows to change its configuration.
 * It has to be extended to apply styled properties.
 *
 * @property title [String] value which applies a button label text
 */
class SPPillItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPPillItemStandard
) : SpBaseRadioButton(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private var selectedBackgroundColor: Int =
        context.getColorFromAttribute(R.attr.brand_primary)
    private var unselectedBackgroundColor: Int = Color.WHITE

    private var selectedShadowStyle: Int =
        R.style.shadow_300_brand
    private var unselectedShadowStyle: Int = R.style.shadow_300_primary_pills

    @StyleRes
    private var unselectedTitleTextAppearance: Int = DEFAULT_INT

    private val binding =
        SpPillLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPPillItem,
            defStyleAttr,
            defStyleRes
        ) {
            applyPillStyledAttributes()
        }
    }

    private fun TypedArray.applyPillStyledAttributes() {
        val text = getString(
            R.styleable.SPPillItem_android_text
        ).orEmpty()

        getResourceId(
            R.styleable.SPPillItem_selectedTextAppearance,
            DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(DEFAULT_OBTAIN_VAL) {
            titleTextAppearance = it
        }
        unselectedTitleTextAppearance = getResourceId(
            R.styleable.SPPillItem_unselectedTextAppearance,
            DEFAULT_OBTAIN_VAL
        )

        selectedShadowStyle = getResourceId(
            R.styleable.SPPillItem_selectedShadowStyle,
            DEFAULT_OBTAIN_VAL
        )

        unselectedShadowStyle = getResourceId(
            R.styleable.SPPillItem_unselectedShadowStyle,
            DEFAULT_OBTAIN_VAL
        )

        selectedBackgroundColor = getColor(
            R.styleable.SPPillItem_selectedBackgroundColor,
            context.getColorFromAttribute(R.attr.brand_primary)
        )
        unselectedBackgroundColor = getColor(
            R.styleable.SPPillItem_unselectedBackgroundColor,
            Color.WHITE
        )
        isChecked = getBoolean(
            R.styleable.SPPillItem_isChecked,
            false
        )


        title = text
        onClick { isChecked = !isChecked }
    }


    override fun handleTitle(value: String) {
        binding.radioButton.text = value
    }

    /**
     * Sets a textAppearance to view
     */
    override fun updateTextAppearance(textAppearance: Int) {
        binding.radioButton.setTextStyle(textAppearance)
    }

    /**
     * Sets a selectedTextAppearance and unselectedTextAppearance to view
     */
    private fun updateStatesTextAppearances(selectedTextAppearance: Int, unselectedTextAppearance: Int) {
        when (isChecked) {
            true -> binding.radioButton.setTextStyle(selectedTextAppearance)
            false -> binding.radioButton.setTextStyle(unselectedTextAppearance)
        }
    }

    override fun handleCheckingState() {
        color = if (isChecked) selectedBackgroundColor else unselectedBackgroundColor

        updateStatesTextAppearances(titleTextAppearance, unselectedTitleTextAppearance)
        setBaseViewShadowStyle(if (isChecked) selectedShadowStyle else unselectedShadowStyle)
    }


    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPPillItem
        ) {
            applyPillStyledAttributes()
        }
    }
}