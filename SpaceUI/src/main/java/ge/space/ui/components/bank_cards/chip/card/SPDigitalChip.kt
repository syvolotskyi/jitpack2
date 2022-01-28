package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.layoutParams
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpDigitalChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize
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
    @StyleRes defStyleRes: Int = R.style.SPBankCardView_ChipDigital
) : SPBaseChip(context, attrs, defStyleAttr) {

    /**
     * Applies a bank card view background
     */
    var cardBackground: SPBankCardGradient = SPBankCardGradient.SPLinear()
        set(value) {
            field = value

            handleCardBackground()
        }

    /**
     * Applies a payment logo Margin
     */
    private var paymentLogoMargin = 0
        set(value) {
            field = value

            changePaymentSystemMargins()
        }

    /**
     * Changes the width size of the view
     */
    var brandLogoSizeHeight: Int = 0
        set(value) {
            field = value

            binding.ivBankLogo.setHeight(brandLogoSizeHeight)
        }

    /**
     * Changes the width size of the payment system
     */
    var paymentSystemHeightSize: Int = 0
        set(value) {
            field = value

            binding.ivPaymentSystem.setHeight(paymentSystemHeightSize)
        }


    /**
     * Binds a view
     */
    private val binding =
        SpDigitalChipLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPDigitalChip,
            defStyleAttr,
            defStyleRes
        ) {
            withDigitalChipStyledResource()
        }

        handleChipSize()
        handleCardBackground()
    }

    override fun setChipStyle(styleRes: Int) {
        context.withStyledAttributes(styleRes, R.styleable.SPDigitalChip) {
            withDigitalChipStyledResource()
        }

        handleChipSize()
        handleCardBackground()
    }

    private fun TypedArray.withDigitalChipStyledResource() {
        paymentLogoMargin = getDimensionPixelSize(
            R.styleable.SPDigitalChip_paymentLogoMargin, DEFAULT_OBTAIN_VAL
        )
        brandLogoSizeHeight = getDimensionPixelSize(
            R.styleable.SPDigitalChip_brandLogoHeight, DEFAULT_OBTAIN_VAL
        )
        paymentSystemHeightSize = getDimensionPixelSize(
            R.styleable.SPDigitalChip_paymentSystemHeight, DEFAULT_OBTAIN_VAL
        )
    }

    override fun getViewData(): SPViewData =
        SPViewData.SPDigitalChipData(chipHeight, chipWidth, cardBackground, 0)


    override fun handleChipSize() {
        with(binding) {
            vGradient.setWidth(chipWidth)
            vGradient.setHeight(chipHeight)
        }
    }

    fun handleCardBackground() {
        binding.vGradient.gradient = cardBackground
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