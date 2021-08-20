package ge.space.design.ui_components.bank_cards.chip.primary_chip

import com.example.spacedesignsystem.R

data class SPPrimaryChipSupport(val resId: Int = R.style.SPBankCardView_ChipPrimary)

object SPPrimaryChipStyles {
    val list = listOf(
        SPPrimaryChipSupport(),
        SPPrimaryChipSupport(
            R.style.SPBankCardView_ChipPrimary_Medium
        ),
        SPPrimaryChipSupport(
            R.style.SPBankCardView_ChipPrimary_Small
        ),
    )
}