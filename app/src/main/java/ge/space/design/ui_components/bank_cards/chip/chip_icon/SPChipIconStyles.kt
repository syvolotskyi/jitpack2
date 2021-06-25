package ge.space.design.ui_components.bank_cards.chip.chip_icon

import ge.space.spaceui.R
import ge.space.ui.components.bank_cards.data.SPChipBankCardSize
import ge.space.ui.components.bank_cards.data.SPChipIconAppearance

data class SPChipIconSupport(
    val size: SPChipBankCardSize = SPChipBankCardSize.Big,
    val iconAppearance: SPChipIconAppearance = SPChipIconAppearance.Accent,
    val icon: Int = R.drawable.ic_bank_24_regular
)

object SPChipIconStyles {
    val list = listOf(
        SPChipIconSupport(),
        SPChipIconSupport(
            size = SPChipBankCardSize.Small
        ),
        SPChipIconSupport(
            iconAppearance = SPChipIconAppearance.Dark
        ),
        SPChipIconSupport(
            size = SPChipBankCardSize.Small,
            iconAppearance = SPChipIconAppearance.Dark
        ),
        SPChipIconSupport(
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            size = SPChipBankCardSize.Small,
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            iconAppearance = SPChipIconAppearance.Dark,
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            size = SPChipBankCardSize.Small,
            iconAppearance = SPChipIconAppearance.Dark,
            icon = R.drawable.ic_info_24_regular
        ),
    )
}