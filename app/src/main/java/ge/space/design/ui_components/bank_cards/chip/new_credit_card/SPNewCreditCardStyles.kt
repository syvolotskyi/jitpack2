package ge.space.design.ui_components.bank_cards.chip.new_credit_card

import ge.space.ui.components.bank_cards.data.SPChipSize

data class SPNewCreditCardSupport(
    val size: SPChipSize = SPChipSize.Big,
)

object SPNewCreditCardStyles {
    val list = listOf(
        SPNewCreditCardSupport(),
        SPNewCreditCardSupport(
            size = SPChipSize.Small
        ),
    )
}