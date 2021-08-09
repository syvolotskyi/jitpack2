package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.extensions.layoutParams
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpDigitalChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize
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
            vGradient.setWidth(size.getBackgroundSizeWidthSize())
            vGradient.setHeight(size.getBackgroundHeightSize())
        }
    }

    private fun SPChipSize.getBackgroundSizeWidthSize(): Int =
        when (this) {
            SPChipSize.Big -> R.dimen.sp_bank_chip_width
            SPChipSize.Medium -> R.dimen.sp_bank_chip_width_small
            SPChipSize.Small -> R.dimen.sp_bank_chip_width_small
        }

    private fun SPChipSize.getBackgroundHeightSize(): Int =
        when (this) {
            SPChipSize.Big -> R.dimen.sp_bank_chip_height
            SPChipSize.Medium -> R.dimen.sp_bank_chip_height_small
            SPChipSize.Small -> R.dimen.sp_bank_chip_height_small
        }

    private fun changeBrandLogoSize() {
        with(binding) {
            ivBankLogo.setWidth(getBrandLogoSizeWidthSize())
            ivBankLogo.setHeight(getBrandLogoSizeHeightSize())
        }
    }

    private fun getBrandLogoSizeWidthSize(): Int =
        when (size) {
            SPChipSize.Big -> R.dimen.sp_digital_chip_bank_logo_width
            SPChipSize.Medium -> R.dimen.sp_digital_chip_bank_logo_width_small
            SPChipSize.Small -> R.dimen.sp_digital_chip_bank_logo_width_small
        }

    private fun getBrandLogoSizeHeightSize(): Int =
        when (size) {
            SPChipSize.Big -> R.dimen.sp_digital_chip_bank_logo_height
            SPChipSize.Medium -> R.dimen.sp_digital_chip_bank_logo_height_small
            SPChipSize.Small -> R.dimen.sp_digital_chip_bank_logo_height_small
        }

    private fun changePaymentSystemSize() {
        with(binding) {
            ivPaymentSystem.setWidth(size.getPaymentSystemWidthSize())
            ivPaymentSystem.setHeight(size.getPaymentSystemHeightSize())
        }
    }

    private fun SPChipSize.getPaymentSystemWidthSize(): Int =
        when (this) {
            SPChipSize.Big -> R.dimen.sp_digital_chip_payment_system_width
            SPChipSize.Medium -> R.dimen.sp_digital_chip_payment_system_width
            SPChipSize.Small -> R.dimen.sp_digital_chip_payment_system_width_small
        }

    private fun SPChipSize.getPaymentSystemHeightSize(): Int =
        when (this) {
            SPChipSize.Big -> R.dimen.sp_digital_chip_payment_system_height
            SPChipSize.Medium -> R.dimen.sp_digital_chip_payment_system_height
            SPChipSize.Small -> R.dimen.sp_digital_chip_payment_system_height_small
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
        when (size) {
            SPChipSize.Big -> R.dimen.sp_digital_chip_payment_system_margin
            SPChipSize.Medium -> R.dimen.sp_digital_chip_payment_system_margin
            SPChipSize.Small -> R.dimen.sp_digital_chip_payment_system_margin_small
        }
}