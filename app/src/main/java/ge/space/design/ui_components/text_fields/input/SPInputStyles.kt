package ge.space.design.ui_components.text_fields.input

import com.example.spacedesignsystem.R

data class SPTextFieldsInputSupportsLoading(
    val resId: Int
)

object SPTextFieldsInputButtonStyles {
    val list = listOf(
        SPTextFieldsInputSupportsLoading(R.style.SPTextField_Input),
        SPTextFieldsInputSupportsLoading(R.style.SPTextField_InputRemovable),
    )
}