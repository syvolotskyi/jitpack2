package ge.space.design.ui_components.buttons.bank_card_button

import ge.space.ui.components.bank_cards.data.SPChipSize

data class SPBankCardButtonSupport(
    val size: SPChipSize = SPChipSize.Big
)

object SPBankCardButtonStyles {
    val list = listOf(
        SPBankCardButtonSupport(),
        SPBankCardButtonSupport(
            SPChipSize.Small
        )
    )
}