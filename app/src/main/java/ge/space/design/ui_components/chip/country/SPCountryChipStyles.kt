package ge.space.design.ui_components.chip.country

import com.example.spacedesignsystem.R


data class SPCountryChipSupport(
    val resId: Int = R.style.SPBankCardView_Chip,
    val url:String =  "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/Flag_of_Georgia_rounded.svg/1200px-Flag_of_Georgia_rounded.svg.png",
)

object SPCountryChipStyles {
    val list = listOf(

        SPCountryChipSupport(),

        SPCountryChipSupport(R.style.SPBankCardView_Chip_WithBorder),
        SPCountryChipSupport(
            R.style.SPBankCardView_Chip_Medium_WithBorder
        ))
}