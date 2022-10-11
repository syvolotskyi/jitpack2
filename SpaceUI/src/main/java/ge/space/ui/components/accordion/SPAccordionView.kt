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
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.onClick
import ge.space.ui.util.extension.setTextStyle

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
     * Sets a component title.
     */
    var titleText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.expandableButton.text = value
        }

    /**
     * Sets a expanded title.
     */
    var expandedText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.expandedText.text = value
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
        getString(R.styleable.SPAccordionLayout_titleText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { titleText = it }
        getString(R.styleable.SPAccordionLayout_expandedText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { expandedText = it }
        getResourceId(
            R.styleable.SPAccordionLayout_titleTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { textAppearance = it }
        getBoolean(R.styleable.SPAccordionLayout_showDivider, true)
            .handleAttributeAction(true) { setDividerVisibility(it) }
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
     * Set true if it's needed to show a  divider
     */
    fun setDividerVisibility(isVisible: Boolean) {
        binding.vDivider.isVisible = isVisible
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