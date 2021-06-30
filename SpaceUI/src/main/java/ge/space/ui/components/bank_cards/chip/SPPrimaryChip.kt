package ge.space.ui.components.bank_cards.chip

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPrimaryChipLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPChipBankCardSize
import ge.space.ui.util.extension.heightByIsBig
import ge.space.ui.util.extension.widthByIsBig

/**
 * Comment
 */
class SPPrimaryChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Comment
     */
    var size : SPChipBankCardSize = SPChipBankCardSize.Big
        set(value) {
            field = value

            handleCardAppearance()
        }

    /**
     * Comment
     */
    private val isBig
        get() = size == SPChipBankCardSize.Big

    /**
     * Comment
     */
    private val binding =
        SpPrimaryChipLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        handleCardAppearance()
    }

    private fun handleCardAppearance() {
        setStyle(
            getStyle()
        )
        handleImageBackground()
        setSize()
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

    private fun getStyle() = if (isBig) R.style.SPBankCardView_Chip
        else R.style.SPBankCardView_Chip_Small
}