package ge.space.design.ui_components.bank_cards.chip.small_empty

import ge.space.ui.components.bank_cards.data.SPSmallEmptyStyle

data class SPEmptyChipBankCardSupport(
    val style: SPSmallEmptyStyle = SPSmallEmptyStyle.White
)

object SPEmptyChipStyles {
    val list = listOf(
        SPEmptyChipBankCardSupport(),
        SPEmptyChipBankCardSupport(
            style = SPSmallEmptyStyle.Dark
        ),
    )
}