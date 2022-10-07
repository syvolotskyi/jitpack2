package ge.space.ui.components.accordion

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpAccordionLayoutBinding
import ge.space.spaceui.databinding.SpEmptyStateLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.onClick

class SPAccordionLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {
    private val binding by lazy {
        SpAccordionLayoutBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
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

        binding.expandableButton.onClick {
            binding.expandableLayout.toggle()
        }
    }
}