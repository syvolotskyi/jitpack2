package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.extensions.layoutParams
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpDigitalChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.util.extension.heightByIsBig
import ge.space.ui.util.extension.widthByIsBig
import ge.space.ui.util.view_factory.SPViewData

/**
 * A chip which allows to show a bank logo with its payment system icon. Also the view
 * can change its background
 *
 * @property cardBackground [SPBankCardGradient] instance which applies a background
 * by a type of it and colors
 */
class SPDigitalChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseChip(context, attrs, defStyleAttr) {

    /**
     * Applies a bank card view background
     */
    var cardBackground: SPBankCardGradient = SPBankCardGradient.SPLinear()
        set(value) {
            field = value

            binding.vGradient.gradient = value
        }

    /**
     * Binds a view
     */
    private val binding =
        SpDigitalChipLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        handleCardAppearance()
    }

    override fun onHandleChipAppearance() {
        handleComponentsSizes()
    }

    override fun getViewData(): SPViewData =
        SPViewData.SPDigitalChipData(size, cardBackground, getStyle())


    private fun handleComponentsSizes() {
        changeBackgroundSize()
        changeBrandLogoSize()
        changePaymentSystemSize()
        changePaymentSystemMargins()
    }

    private fun changeBackgroundSize() {
        with(binding) {
            vGradient.widthByIsBig(
                isBig,
                R.dimen.sp_bank_chip_width,
                R.dimen.sp_bank_chip_width_small
            )
            vGradient.heightByIsBig(
                isBig,
                R.dimen.sp_bank_chip_height,
                R.dimen.sp_bank_chip_height_small
            )
        }
    }

    private fun changeBrandLogoSize() {
        with(binding) {
            ivBankLogo.widthByIsBig(
                isBig,
                R.dimen.sp_digital_chip_bank_logo_width,
                R.dimen.sp_digital_chip_bank_logo_width_small
            )
            ivBankLogo.heightByIsBig(
                isBig,
                R.dimen.sp_digital_chip_bank_logo_height,
                R.dimen.sp_digital_chip_bank_logo_height_small
            )
        }
    }

    private fun changePaymentSystemSize() {
        with(binding) {
            ivPaymentSystem.widthByIsBig(
                isBig,
                R.dimen.sp_digital_chip_payment_system_width,
                R.dimen.sp_digital_chip_payment_system_width_small
            )
            ivPaymentSystem.heightByIsBig(
                isBig,
                R.dimen.sp_digital_chip_payment_system_height,
                R.dimen.sp_digital_chip_payment_system_height_small
            )
        }
    }

    private fun changePaymentSystemMargins() {
        with(binding) {
            ivPaymentSystem.layoutParams<MarginLayoutParams> {
                marginEnd = resources.getDimensionPixelSize(getPaymentSystemLogoMargin())
                bottomMargin = resources.getDimensionPixelSize(getPaymentSystemLogoMargin())
            }
        }
    }

    private fun getPaymentSystemLogoMargin() =
        if (isBig) R.dimen.sp_digital_chip_payment_system_margin
        else R.dimen.sp_digital_chip_payment_system_margin_small
}