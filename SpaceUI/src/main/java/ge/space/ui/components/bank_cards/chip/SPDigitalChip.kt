package ge.space.ui.components.bank_cards.chip

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpDigitalChipLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipBankCardSize
import ge.space.ui.util.extension.heightByIsBig
import ge.space.ui.util.extension.widthByIsBig

/**
 * Comment
 */
class SPDigitalChip @JvmOverloads constructor(
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
     * Applies a bank card view background
     */
    var cardBackground: SPBankCardGradient = SPBankCardGradient.SPLinear()
        set(value) {
            field = value

            binding.vGradient.gradient = value
        }

    /**
     * Comment
     */
    private val binding =
        SpDigitalChipLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        handleCardAppearance()
    }

    private fun handleCardAppearance() {
        setStyle(
            getStyle()
        )
        setSize()
    }

    private fun setSize() {
        with(binding) {
            vGradient.widthByIsBig(isBig, R.dimen.sp_bank_chip_width, R.dimen.sp_bank_chip_width_small)
            vGradient.heightByIsBig(isBig, R.dimen.sp_bank_chip_height, R.dimen.sp_bank_chip_height_small)

//            ivBankLogo.widthByIsBig(isBig, R.dimen.sp_digital_chip_bank_logo_width, R.dimen.sp_digital_chip_bank_logo_width_small)
//            ivBankLogo.heightByIsBig(isBig, R.dimen.sp_digital_chip_bank_logo_height, R.dimen.sp_digital_chip_bank_logo_height_small)
//            ivPaymentSystem.widthByIsBig(isBig, R.dimen.sp_digital_chip_payment_system_width, R.dimen.sp_digital_chip_payment_system_width_small)
//            ivPaymentSystem.heightByIsBig(isBig, R.dimen.sp_digital_chip_payment_system_height, R.dimen.sp_digital_chip_payment_system_width_small)
        }
    }

    private fun getStyle() = if (isBig) R.style.SPBankCardView_Chip
        else R.style.SPBankCardView_Chip_Small
}