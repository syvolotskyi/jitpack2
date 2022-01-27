package ge.space.ui.components.bank_cards.card_list.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.viewbinding.ViewBinding
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView

/**
 * An abstract base chip item which is for extending it in other chip items
 */
abstract class SPBaseCardList<VB : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Reference to [VB] instance which is related to ViewBinding
     */
    protected val binding: VB

    /**
     * Lazy property for initialize ViewBinding in constructor
     */
    private val _binding by lazy {
        getViewBinding()
    }

    init {
        binding = _binding

        context.withStyledAttributes(
            attrs,
            R.styleable.SPBaseView,
            defStyleAttr
        ) {
            val style = getResourceId(
                R.styleable.SPBaseView_style,
                defStyleRes
            )
            handleInnerStyle(style)
        }
    }

    /**
     * Sets a specific style for the view
     */
    fun setChipItemStyle(@StyleRes style: Int) {
        setBaseViewStyle(style)
        handleInnerStyle(style)
    }

    private fun handleInnerStyle(@StyleRes style: Int) {
        context.theme.obtainStyledAttributes(
            style,
            R.styleable.sp_selectable_chip_item
        ).run {
            setTitlesAppearances(this)
        }
    }

    /**
     * Sets text appearances for all titles
     */
    protected abstract fun setTitlesAppearances(styledAttrs: TypedArray)

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB
}