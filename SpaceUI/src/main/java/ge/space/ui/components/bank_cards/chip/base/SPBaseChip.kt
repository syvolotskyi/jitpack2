package ge.space.ui.components.bank_cards.chip.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.setHeight
import ge.space.ui.util.extension.setWidth
import ge.space.ui.util.view_factory.SPViewFactoryData

/**
 * Abstract base chip which allows to abstract specific info by
 * next properties
 *
 * @property chipHeight [Int] instance that allows to change the size of the view
 */
abstract class SPBaseChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : SPBaseView(context, attrs, defStyleAttr, defStyleRes), SPViewFactoryData, SPViewStyling {

    /**
     * Changes the size of the view
     */
    var size: SPChipSize = SPChipSize.Big
        set(value) {
            field = value

        }

    /**
     * Changes the height size of the view
     */
    var chipHeight: Int = 0
        set(value) {
            field = value

            setHeight(chipHeight)
        }

    /**
     * Changes the width size of the view
     */
    var chipWidth: Int = 0
        set(value) {
            field = value

            setWidth(chipWidth)
        }

    /**
     * Allows to hide or show a border for the view
     */
    var hasBorder: Boolean = false
        set(value) {
            field = value

            handleBorder()
        }


    /**
     * Name of style res, needs for getViewData to store chip data
     */
    protected var chipStyleRes: Int = defStyleRes


    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.SPBaseChip,
            defStyleAttr,
            defStyleRes
        ) {
            withStyledResource()
            chipStyleRes = getResourceId(
                R.styleable.SPBaseChip_chipStyleRes, defStyleRes
            )
        }
    }

    override fun setBaseViewStyle(defStyleRes: Int) {
        super.setBaseViewStyle(defStyleRes)
        chipStyleRes = defStyleRes
        context.withStyledAttributes(defStyleRes, R.styleable.SPBaseChip) { withStyledResource() }
    }

    private fun TypedArray.withStyledResource() {
        hasBorder = getBoolean(R.styleable.SPBaseChip_hasBorder, false)

        chipHeight = getDimensionPixelSize(
            R.styleable.SPBaseChip_chipHeight, DEFAULT_OBTAIN_VAL
        )
        chipWidth = getDimensionPixelSize(
            R.styleable.SPBaseChip_chipWidth, DEFAULT_OBTAIN_VAL
        )
    }

    private fun handleBorder() {
        if (hasBorder) {
            changeBorder(
                context.getColorFromAttribute(R.attr.separator_non_opaque),
                resources.getDimensionPixelSize(R.dimen.dimen_p_0_5).toFloat()
            )
        } else {
            changeBorder(DEFAULT_OBTAIN_VAL, DEFAULT_OBTAIN_VAL.toFloat())
        }
    }

    abstract fun handleChipSize()

    protected abstract fun setChipStyle(styleRes: Int)

    /**
     * Allows to update chip style and BaseViewStyle programmatically
     */
    override fun setViewStyle(@StyleRes newStyle: Int) {
        with(newStyle) {
            setBaseViewStyle(this)
            setChipStyle(this)
        }
    }
}