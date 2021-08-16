package ge.space.design.ui_components.bank_cards.chip.digital_chip

import com.example.spacedesignsystem.R
import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize

data class SPDigitalChipSupport(
    val background: SPBankCardGradient = SPBankCardGradient.SPNoneGradient(),
    val resId: Int = R.style.SPBankCardView_ChipDigital
)

object SPDigitalChipStyles {
    val list = listOf(
        SPDigitalChipSupport(
            background = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_BLUE_1,
                    SPButtonStyles.GRADIENT_BLUE_2
                )
            ),
        ),
        SPDigitalChipSupport(
            background = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_BLUE_1,
                    SPButtonStyles.GRADIENT_BLUE_2
                )
            ),
            R.style.SPBankCardView_ChipDigital_Medium,
        ),
        SPDigitalChipSupport(
            background = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_GREEN_1,
                    SPButtonStyles.GRADIENT_GREEN_2
                )
            ),
        ),
        SPDigitalChipSupport(
            background = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_GREEN_1,
                    SPButtonStyles.GRADIENT_GREEN_2
                )
            ),
            R.style.SPBankCardView_ChipDigital_Small,
        ),
        SPDigitalChipSupport(
            background = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_VIOLET_1,
                    SPButtonStyles.GRADIENT_VIOLET_2
                )
            ),
        ),
        SPDigitalChipSupport(
            background = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_VIOLET_1,
                    SPButtonStyles.GRADIENT_VIOLET_2
                )
            ),
            R.style.SPBankCardView_ChipDigital_Medium,
        ),

        SPDigitalChipSupport(
            background = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_LIGHT_GREEN_1,
                    SPButtonStyles.GRADIENT_LIGHT_GREEN_2
                ), 33f
            ),
        ),
        SPDigitalChipSupport(
            background = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_LIGHT_GREEN_1,
                    SPButtonStyles.GRADIENT_LIGHT_GREEN_2
                )
            ),
            R.style.SPBankCardView_ChipDigital_Small,
        ),
    )
}