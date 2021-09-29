package ge.space.design.ui_components.buttons.vertical_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val src: Int = R.drawable.ic_plus_24_regular,
)

object SPVerticalButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPButton_Vertical_Size48),
        SPButtonSupportsLoading(R.style.SPButton_Vertical_Size40),
    )
}