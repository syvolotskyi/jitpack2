package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
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
        context.withStyledAttributes(
            attrs,
            R.styleable.sp_chip_digital,
            defStyleAttr
        ) { withDigitalChipStyledResource() }

        handleChipSize()
    }

    override fun setChipStyle(styleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(styleRes, R.styleable.sp_chip_digital)

        styleAttrs.run {
            withDigitalChipStyledResource()
        }
    }

    private fun TypedArray.withDigitalChipStyledResource() {
        paymentLogoMargin = getDimensionPixelSize(
            R.styleable.sp_chip_digital_paymentLogoMargin, DEFAULT_OBTAIN_VAL
        )
        brandLogoSizeHeight = getDimensionPixelSize(
            R.styleable.sp_chip_digital_brandLogoHeight, DEFAULT_OBTAIN_VAL
        )
        paymentSystemHeightSize = getDimensionPixelSize(
            R.styleable.sp_chip_digital_paymentSystemHeight, DEFAULT_OBTAIN_VAL
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