package ge.space.design.ui_components.bank_cards.chip.secondary_chip

import com.example.spacedesignsystem.R

import ge.space.ui.util.extension.EMPTY_STRING

data class SPSecondaryChipSupport(
    val resId: Int = R.style.SPBankCardView_ChipSecondary,
    val bankLogoUrl: String = EMPTY_STRING,
    val paymentSystemUrl: String = EMPTY_STRING
)

object SPSecondaryChipStyles {
    private val BANK = "https://w7.pngwing.com/pngs/911/456/png-transparent-computer-icons-business-others-blue-angle-leaf-thumbnail.png"
    private val VISA = "https://www.tadviser.ru/images/8/86/Visa_Inc._logo.svg.png"
    val list = listOf(
        SPSecondaryChipSupport(
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_WithBorder,
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium,
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium_WithBorder,
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Small,
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        )
    )
}