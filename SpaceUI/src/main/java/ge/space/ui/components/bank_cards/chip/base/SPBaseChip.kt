package ge.space.ui.components.bank_cards.chip.base

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.util.extension.SPStyleInterface
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
) : SPBaseView(context, attrs, defStyleAttr), SPViewFactoryData, SPStyleInterface {

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
            R.styleable.sp_chip,
            defStyleAttr
        ) {
            withStyledResource()
        }
    }

    override fun setStyle(defStyleRes: Int) {
        super.setStyle(defStyleRes)
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.sp_chip)

        styleAttrs.run { withStyledResource() }
    }

    private fun TypedArray.withStyledResource() {
        chipHeight = getDimensionPixelSize(
            R.styleable.sp_chip_chipHeight, DEFAULT_OBTAIN_VAL
        )
        chipWidth = getDimensionPixelSize(
            R.styleable.sp_chip_chipWidth, DEFAULT_OBTAIN_VAL
        )
    }

    abstract fun handleChipSize()

    protected abstract fun setChipStyle(styleRes: Int)

    /**
     * Allows to update text style and BaseViewStyle programmatically
     */
    override fun style(@StyleRes newStyle: Int) {
        with(newStyle) {
            setStyle(this)
            setChipStyle(this)
        }
    }
}