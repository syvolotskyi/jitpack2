package ge.space.design.ui_components.chip.country

import com.example.spacedesignsystem.R


data class SPCountryChipSupport(
    val resId: Int = R.style.SPBankCardView_Chip_WithBorder,
    val url: String =
        SPCountryChipStyles.sakartvelo
)

object SPCountryChipStyles {
    val sakartvelo =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Flag_of_Georgia.svg/2560px-Flag_of_Georgia.svg.png"
    val ukraine =
        "https://dnmu.edu.ua/wp-content/uploads/2020/07/derzhavnyj-prapor-ukrayiny.jpg"
    val list = listOf(
        SPCountryChipSupport(),
        SPCountryChipSupport(R.style.SPBankCardView_Chip, ukraine),
        SPCountryChipSupport(R.style.SPBankCardView_Chip_WithBorder),
        SPCountryChipSupport(R.style.SPBankCardView_Chip, ukraine),
        SPCountryChipSupport(R.style.SPBankCardView_Chip_Medium_WithBorder),
        SPCountryChipSupport(R.style.SPBankCardView_Chip_Medium, ukraine)
    )
}