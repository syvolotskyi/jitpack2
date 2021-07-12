package ge.space.design.ui_components.text_fields.dropdown

import com.example.spacedesignsystem.R

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