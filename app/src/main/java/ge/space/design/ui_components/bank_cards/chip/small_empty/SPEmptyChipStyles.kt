package ge.space.design.ui_components.bank_cards.chip.small_empty

import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle

data class SPEmptyChipBankCardSupport(
    val style: SPEmptyChipStyle = SPEmptyChipStyle.White
)

object SPEmptyChipStyles {
    val list = listOf(
        SPEmptyChipBankCardSupport(),
        SPEmptyChipBankCardSupport(
            style = SPEmptyChipStyle.Dark
        ),
    )
}