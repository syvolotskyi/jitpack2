package ge.space.ui.components.accordion

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpAccordionLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.onClick
import ge.space.ui.util.extension.setTextStyle


/**
 * SPAccordionView view extended from [ConstraintLayout]
 *
 * Call initAccordionData and set SPAccordionData to change title text, expanded text and divider visibility
 * @property isExpanded [Boolean] value which expand or hide expanded text.
 *
 */
class SPAccordionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.SPAccordionView
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy {
        SpAccordionLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = SPBaseView.DEFAULT_INT

    /**
     * Sets a expanded text appearance
     */
    @StyleRes
    private var expandedTextAppearance: Int = SPBaseView.DEFAULT_INT

    /**
     * Expand or hide expandedText
     */
    var isExpanded: Boolean
        get() = binding.expandableLayout.isExpanded
        set(expand) {
            binding.expandableLayout.setExpanded(expand, true)
            handleRightButton()
        }

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.SPAccordionLayout,
            defStyleAttr,
            defStyleRes
        ) {
            withStyledAttributes()
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.withStyledAttributes(newStyle, R.styleable.SPAccordionLayout) {
            withStyledAttributes()
        }
    }

    private fun TypedArray.withStyledAttributes() {
        val titleText = getString(R.styleable.SPAccordionLayout_titleText).orEmpty()
        val descText = getString(R.styleable.SPAccordionLayout_expandedText).orEmpty()
        val showDivider = getBoolean(R.styleable.SPAccordionLayout_showDivider, true)
        initAccordionData(SPAccordionData(titleText, descText, showDivider))

        getResourceId(
            R.styleable.SPAccordionLayout_titleTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { textAppearance = it }

        getBoolean(R.styleable.SPAccordionLayout_expanded, false)
            .handleAttributeAction(false) { isExpanded = it }
        getResourceId(
            R.styleable.SPAccordionLayout_expandedTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { expandedTextAppearance = it }

        updateTextAppearance()
        binding.expandableButton.onClick {
            binding.expandableLayout.toggle()
            handleRightButton()
        }
    }

    /**
     * Sets title, description and divider visibility
     */
    fun initAccordionData(data: SPAccordionData) {
        binding.expandedText.text = data.expandedText
        binding.expandableButton.text = data.titleText
        binding.vDivider.isVisible = data.showDivider
    }

    /**
     * Sets title and description text appearance
     */
    fun updateTextAppearance(
        titleTextAppearance: Int = textAppearance,
        expandedAppearance: Int = expandedTextAppearance
    ) {
        binding.expandableButton.setTextStyle(titleTextAppearance)
        binding.expandedText.setTextStyle(expandedAppearance)
    }

    private fun handleRightButton() {
        binding.expandableButton.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(context, getArrowDrawable()),
            null
        )
    }

    private fun getArrowDrawable() =
        if (binding.expandableLayout.isExpanded) R.drawable.ic_chevron_up_16_regular else R.drawable.ic_chevron_down_16_regular
}