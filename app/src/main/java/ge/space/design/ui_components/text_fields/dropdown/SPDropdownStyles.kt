package ge.space.design.ui_components.text_fields.dropdown

import com.example.spacedesignsystem.R
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.image.SPIconFactory
import ge.space.ui.components.text_fields.input.utils.extension.SPDropdownItemModel

data class SPTextFieldsDropDownsSupportsLoading(
    val resId: Int
)

object SPTextFieldsDropdownStyles {
    val list = listOf(
        SPTextFieldsDropDownsSupportsLoading(R.style.SPTextField_Dropdown),
        SPTextFieldsDropDownsSupportsLoading(R.style.SPTextField_DropdownWithIcon),
    )
}

object SPTextFieldsDropdownItems {
    val list = listOf(
        SPDropdownItemModel(
            1,
            "Card for payment",
            SPIconFactory.SPIconData.SPrimaryChip(SPChipSize.Small)
        ),
        SPDropdownItemModel(
            2,
            "Card for credit",
            SPIconFactory.SPIconData.SPrimaryChip(SPChipSize.Small)
        ),
        SPDropdownItemModel(
            3,
            "Card for wife",
            SPIconFactory.SPIconData.SPrimaryChip(SPChipSize.Small)
        )
    )
}