package ge.space.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpAddBankButtonLayoutBinding
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.util.extension.heightByIsBig
import ge.space.ui.util.extension.widthByIsBig

/**
 * Base view for small chips that allows to abstract some specific properties.
 * The view apply appearances the same as size of the view, size of the image, and
 * a style of the view
 *
 * @property cardSize allows to change the size of the view
 * @property isBig allows to get a state if the view is big
 */
abstract class SPBaseChipIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Applies a size of the view
     */
    var cardSize : SPChipSize = SPChipSize.Big
        set(value) {
            field = value

            handleCardAppearance()
        }

    /**
     * Checks if [cardSize] == [SPChipSize.Big]
     */
    protected val isBig
        get() = cardSize == SPChipSize.Big

    /**
     * Binds a view
     */
    protected val binding by lazy {
        getAddBankButtonLayoutBinding()
    }

    private fun handleCardAppearance() {
        setImageSize()
        val style = getStyle()
        setStyle(style)
        setSize()
    }

    private fun getAddBankButtonLayoutBinding() =
        SpAddBankButtonLayoutBinding.inflate(LayoutInflater.from(context), this)

    private fun getStyle() = if (isBig) R.style.SPBankCardView_Chip
        else R.style.SPBankCardView_Chip_Small

    protected open fun setImageSize() { }

    private fun setSize() {
        widthByIsBig(isBig, R.dimen.sp_bank_chip_width, R.dimen.sp_bank_chip_width_small)
        heightByIsBig(isBig, R.dimen.sp_bank_chip_height, R.dimen.sp_bank_chip_height_small)
    }
}