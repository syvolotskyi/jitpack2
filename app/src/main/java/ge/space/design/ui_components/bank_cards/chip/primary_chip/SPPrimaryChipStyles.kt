package ge.space.design.ui_components.bank_cards.chip.primary_chip

import ge.space.design.ui_components.bank_cards.chip.digital_chip.SPDigitalChipSupport
import ge.space.ui.components.bank_cards.data.SPChipBankCardSize

data class SPPrimaryChipSupport(
    val size: SPChipBankCardSize = SPChipBankCardSize.Big,
)

object SPPrimaryChipStyles {
    val list = listOf(
        SPPrimaryChipSupport(),
        SPPrimaryChipSupport(
            size = SPChipBankCardSize.Small
        ),
    )
}