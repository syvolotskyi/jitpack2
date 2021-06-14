package ge.space.design.ui_components.text_fields.input

import com.example.spacedesignsystem.R

data class SPTextFieldInputSupportsLoading(
    val resId: Int
)

object SPTextFieldInputStyles {
    val list = listOf(
        SPTextFieldInputSupportsLoading(R.style.SPTextFieldInput),
        SPTextFieldInputSupportsLoading(R.style.SPTextFieldInput_Removable),
    )
}