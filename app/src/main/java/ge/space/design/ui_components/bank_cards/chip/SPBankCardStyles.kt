package ge.space.design.ui_components.bank_cards.chip

import ge.space.ui.components.bank_cards.data.SPSmallEmptyStyle

data class SPChipBankCardSupport(
    val style: SPSmallEmptyStyle = SPSmallEmptyStyle.White
)

object SPChipStyles {
    val list = listOf(
        SPChipBankCardSupport(),
        SPChipBankCardSupport(
            style = SPSmallEmptyStyle.Dark
        ),
    )
}