package ge.space.design.ui_components.chip.small_empty

import com.example.spacedesignsystem.R

data class SPEmptyChipBankCardSupport(val resId: Int = R.style.SPBankCardView_ChipEmpty_Dark)

object SPEmptyChipStyles {
    val list = listOf(
        SPEmptyChipBankCardSupport(),
        SPEmptyChipBankCardSupport(R.style.SPBankCardView_ChipEmpty_White),
        SPEmptyChipBankCardSupport(R.style.SPBankCardView_ChipEmpty_Medium_Dark),
        SPEmptyChipBankCardSupport(R.style.SPBankCardView_ChipEmpty_Medium_White),
        SPEmptyChipBankCardSupport(R.style.SPBankCardView_ChipEmpty_Small_Dark),
        SPEmptyChipBankCardSupport(R.style.SPBankCardView_ChipEmpty_Small_White),
    )
}