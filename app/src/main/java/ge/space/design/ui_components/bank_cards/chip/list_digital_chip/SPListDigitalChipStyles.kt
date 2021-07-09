package ge.space.design.ui_components.bank_cards.chip.list_digital_chip

import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.ui.components.bank_cards.data.SPBankCardGradient

data class SPListDigitalChipSupport(
    val enabled: Boolean = true,
    val chipBackground: SPBankCardGradient = SPBankCardGradient.SPNoneGradient(),
    val text: String = "",
    val currency: String = ""
)

object SPListDigitalChipStyles {
    val geList = listOf(
        SPListDigitalChipSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_BLUE_1,
                    SPButtonStyles.GRADIENT_BLUE_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "₾",
        ),
        SPListDigitalChipSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_BLUE_1,
                    SPButtonStyles.GRADIENT_BLUE_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "₾",
        ),
        SPListDigitalChipSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_BLUE_1,
                    SPButtonStyles.GRADIENT_BLUE_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "₾",
            enabled = false
        )
    )

    val usdList = listOf(
        SPListDigitalChipSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_GREEN_1,
                    SPButtonStyles.GRADIENT_GREEN_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "$",
        ),
        SPListDigitalChipSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_GREEN_1,
                    SPButtonStyles.GRADIENT_GREEN_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "$",
        ),
        SPListDigitalChipSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_GREEN_1,
                    SPButtonStyles.GRADIENT_GREEN_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "$",
            enabled = false
        )
    )

    val eurList = listOf(
        SPListDigitalChipSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_VIOLET_1,
                    SPButtonStyles.GRADIENT_VIOLET_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "€",
        ),
        SPListDigitalChipSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_VIOLET_1,
                    SPButtonStyles.GRADIENT_VIOLET_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "€",
        ),
        SPListDigitalChipSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_VIOLET_1,
                    SPButtonStyles.GRADIENT_VIOLET_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "€",
            enabled = false
        )
    )
}