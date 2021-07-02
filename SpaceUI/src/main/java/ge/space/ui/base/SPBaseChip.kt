package ge.space.ui.base

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.ui.components.bank_cards.data.SPChipSize

/**
 * Abstract base chip which allows to abstract specific info by
 * next properties
 *
 * @property size [SPChipSize] instance that allows to change the size of the view
 * @property isBig checks if [size] == [SPChipSize.Big]
 */
abstract class SPBaseChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Changes the size of the view
     */
    var size : SPChipSize = SPChipSize.Big
        set(value) {
            field = value

            handleCardAppearance()
        }

    /**
     * Checks if [size] == [SPChipSize.Big]
     */
    protected val isBig
        get() = size == SPChipSize.Big

    protected fun handleCardAppearance() {
        setStyle(
            getStyle()
        )
        onHandleCardAppearance()
    }

    protected abstract fun onHandleCardAppearance()

    private fun getStyle() = if (isBig) R.style.SPBankCardView_Chip
        else R.style.SPBankCardView_Chip_Small
}