package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPrimaryChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.util.view_factory.SPViewData

/**
 * A chip extended by [SPBaseChip] allows to show a primary info of bank.
 * The info depends on the flavor (TBC or Space)
 */
class SPPrimaryChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseChip(context, attrs, defStyleAttr) {

    /**
     * Binds a view
     */
    private val binding =
        SpPrimaryChipLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        handleCardAppearance()
    }

    override fun onHandleChipAppearance() {
        handleImageBackground()
        setSize()
    }

    override fun getViewData(): SPViewData {
        return SPViewData.SPrimaryChipData(size, getStyle())
    }

    private fun setSize() {
        setWidth(getWidthSize())
        setHeight(getHeightSize())
    }

    private fun handleImageBackground() {
        binding.ivBackground.setImageResource(getPrimaryChipImage())
    }

    private fun getWidthSize(): Int =
        when (size) {
            SPChipSize.Big -> R.dimen.sp_bank_chip_width
            SPChipSize.Medium -> R.dimen.sp_bank_chip_width_small
            SPChipSize.Small -> R.dimen.sp_bank_chip_width_small
        }

    private fun getHeightSize(): Int =
        when (size) {
            SPChipSize.Big -> R.dimen.sp_bank_chip_height
            SPChipSize.Medium -> R.dimen.sp_bank_chip_height_small
            SPChipSize.Small -> R.dimen.sp_bank_chip_height_small
        }

    private fun getPrimaryChipImage() = when (size) {
        SPChipSize.Big -> R.drawable.img_primary_chip
        SPChipSize.Medium -> R.drawable.img_primary_chip_medium
        SPChipSize.Small -> R.drawable.img_primary_chip_medium
    }
}