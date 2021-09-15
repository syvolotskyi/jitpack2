package ge.space.design.ui_components.buttons.horizontal_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val src: Int = R.drawable.ic_share_ios_24_regular,
)

object SPHorizontalButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPButton_Horizontal_Size48),
        SPButtonSupportsLoading(R.style.SPButton_Horizontal_Size56),
    )
}