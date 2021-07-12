package ge.space.design.ui_components.text_fields.dropdown

import com.example.spacedesignsystem.R
import ge.space.ui.components.text_fields.input.utils.extension.SPDropdownItemModel

data class SPTextFieldsDropDownsSupportsLoading(
    val resId: Int
)

object SPTextFieldsDropdownStyles {
    val list = listOf(
        SPTextFieldsDropDownsSupportsLoading(R.style.SPTextField_Dropdown),
        SPTextFieldsDropDownsSupportsLoading(R.style.SPTextField_DropdownWithIcon),
        SPTextFieldsDropDownsSupportsLoading(R.style.SPTextField_DropdownWithChip),
    )
}

object SPTextFieldsDropdownItems {
    val list = listOf(
        SPDropdownItemModel(
            1,
            "Card for payment",
            "https://uk.wikipedia.org/wiki/Google_%D0%A4%D0%BE%D1%82%D0%BE#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Google_Photos_icon_(2020).svg"
        ),
        SPDropdownItemModel(
            2,
            "Card for credit",
            "https://uk.wikipedia.org/wiki/Google_%D0%A4%D0%BE%D1%82%D0%BE#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Google_Photos_icon_(2020).svg"
        ),
        SPDropdownItemModel(
            3,
            "Card for wife",
            "https://uk.wikipedia.org/wiki/Google_%D0%A4%D0%BE%D1%82%D0%BE#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Google_Photos_icon_(2020).svg"
        )
    )
}