package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPrimaryChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.util.extension.heightByIsBig
import ge.space.ui.util.extension.widthByIsBig
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
        widthByIsBig(isBig, R.dimen.sp_bank_chip_width, R.dimen.sp_bank_chip_width_small)
        heightByIsBig(isBig, R.dimen.sp_bank_chip_height, R.dimen.sp_bank_chip_height_small)
    }

    private fun handleImageBackground() {
        binding.ivBackground.setImageResource(
            getImage()
        )
    }

    private fun getImage() = if (isBig) R.drawable.img_primary_chip
        else R.drawable.img_primary_chip_small
}