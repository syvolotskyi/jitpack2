package ge.space.design.ui_components.bank_cards.chip.list_digital_chip

import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.extensions.EMPTY_TEXT
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPDefaultChipData

data class SPDigitalChipItemSupport(
    val enabled: Boolean = true,
    val chipBackground: SPBankCardGradient = SPBankCardGradient.SPNoneGradient(),
    val text: String = EMPTY_TEXT,
    val currency: String = EMPTY_TEXT
)

data class SPDefaultChipItemSupport(
    val defaultChipData: SPDefaultChipData = SPDefaultChipData.SPPhysicalChip
)

object SPChipListStyles {
    val geList = listOf(
        SPDigitalChipItemSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_BLUE_1,
                    SPButtonStyles.GRADIENT_BLUE_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "₾",
        ),
        SPDigitalChipItemSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_BLUE_1,
                    SPButtonStyles.GRADIENT_BLUE_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "₾",
        ),
        SPDigitalChipItemSupport(
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
        SPDigitalChipItemSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_GREEN_1,
                    SPButtonStyles.GRADIENT_GREEN_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "$",
        ),
        SPDigitalChipItemSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_GREEN_1,
                    SPButtonStyles.GRADIENT_GREEN_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "$",
        ),
        SPDigitalChipItemSupport(
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
        SPDigitalChipItemSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_VIOLET_1,
                    SPButtonStyles.GRADIENT_VIOLET_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "€",
        ),
        SPDigitalChipItemSupport(
            chipBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    SPButtonStyles.GRADIENT_VIOLET_1,
                    SPButtonStyles.GRADIENT_VIOLET_2
                )
            ),
            text = "ბარათი დოლარში",
            currency = "€",
        ),
        SPDigitalChipItemSupport(
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

    val defaultList = listOf(
        SPDefaultChipItemSupport(),
        SPDefaultChipItemSupport(
            defaultChipData = SPDefaultChipData.SPDigitalChip(
                background = SPBankCardGradient.SPLinear(
                    colors = arrayListOf(
                        SPButtonStyles.GRADIENT_BLUE_1,
                        SPButtonStyles.GRADIENT_BLUE_2
                    )
                ),
            )
        ),
        SPDefaultChipItemSupport(
            defaultChipData = SPDefaultChipData.SPDigitalChip(
                background = SPBankCardGradient.SPLinear(
                    colors = arrayListOf(
                        SPButtonStyles.GRADIENT_LIGHT_GREEN_1,
                        SPButtonStyles.GRADIENT_LIGHT_GREEN_2
                    )
                ),
            )
        )
    )
}