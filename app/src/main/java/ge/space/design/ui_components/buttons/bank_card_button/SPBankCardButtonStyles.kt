package ge.space.design.ui_components.buttons.bank_card_button

import ge.space.ui.components.bank_cards.data.SPChipBankCardSize

data class SPBankCardButtonSupport(
    val size: SPChipBankCardSize = SPChipBankCardSize.Big
)

object SPBankCardButtonStyles {
    val list = listOf(
        SPBankCardButtonSupport(),
        SPBankCardButtonSupport(
            SPChipBankCardSize.Small
        )
    )
}