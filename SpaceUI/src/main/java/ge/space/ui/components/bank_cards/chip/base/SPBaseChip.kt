package ge.space.ui.components.bank_cards.chip.base

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.util.view_factory.SPViewFactoryData

/**
 * Abstract base chip which allows to abstract specific info by
 * next properties
 *
 * @property size [SPChipSize] instance that allows to change the size of the view
 */
abstract class SPBaseChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr), SPViewFactoryData {

    /**
     * Changes the size of the view
     */
    var size: SPChipSize = SPChipSize.Big
        set(value) {
            field = value

            handleCardAppearance()
        }

    protected fun handleCardAppearance() {
        setStyle(
            getStyle()
        )
        onHandleChipAppearance()
    }

    /**
     * The method is triggered after [size] change
     */
    protected open fun onHandleChipAppearance() {
    }

    fun getStyle() = when (size) {
        SPChipSize.Big -> R.style.SPBankCardView_Chip
        SPChipSize.Medium -> R.style.SPBankCardView_Chip_Medium
        SPChipSize.Small -> R.style.SPBankCardView_Chip_Small
    }
}