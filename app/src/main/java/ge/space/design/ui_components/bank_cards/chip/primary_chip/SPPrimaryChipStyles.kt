package ge.space.design.ui_components.bank_cards.chip.primary_chip

import ge.space.ui.components.bank_cards.data.SPChipSize

data class SPPrimaryChipSupport(
    val size: SPChipSize = SPChipSize.Big,
)

object SPPrimaryChipStyles {
    val list = listOf(
        SPPrimaryChipSupport(),
        SPPrimaryChipSupport(
            size = SPChipSize.Small
        ),
    )
}