package ge.space.design.ui_components.bank_cards.chip.digital_chip

import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize

data class SPDigitalChipSupport(
    val background: SPBankCardGradient = SPBankCardGradient.SPNoneGradient(),
    val size: SPChipSize = SPChipSize.Big
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
            size = SPChipSize.Small,
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
            size = SPChipSize.Small,
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
            size = SPChipSize.Small,
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
            size = SPChipSize.Small,
        ),
    )
}