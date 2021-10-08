package ge.space.ui.components.bank_cards.chip.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.util.extension.SPSetViewStyleInterface
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
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr), SPViewFactoryData, SPSetViewStyleInterface {

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

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.SPBaseChip,
            defStyleAttr
        ) {
            withStyledResource()
        }
    }

    override fun setStyle(defStyleRes: Int) {
        super.setStyle(defStyleRes)
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPBaseChip)

        styleAttrs.run { withStyledResource() }
    }

    private fun TypedArray.withStyledResource() {
        chipHeight = getDimensionPixelSize(
            R.styleable.SPBaseChip_chipHeight, DEFAULT_OBTAIN_VAL
        )
        chipWidth = getDimensionPixelSize(
            R.styleable.SPBaseChip_chipWidth, DEFAULT_OBTAIN_VAL
        )
    }

    abstract fun handleChipSize()

    protected abstract fun setChipStyle(styleRes: Int)

    /**
     * Allows to update chip style and BaseViewStyle programmatically
     */
    override fun setViewStyle(@StyleRes newStyle: Int) {
        with(newStyle) {
            setStyle(this)
            setChipStyle(this)
        }
    }
}