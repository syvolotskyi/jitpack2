package ge.space.design.ui_components.buttons.default_button

import com.example.spacedesignsystem.R
import ge.space.ui.components.buttons.SPButton


data class SPButtonSupportsLoading(
    val resId: Int,
    val iconDirection: SPButton.IconDirection = SPButton.IconDirection.NONE,
    val src: Int = R.drawable.ic_arrow_left_24_regular,
)

object SPButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPButton_Secondary_Size40),
        SPButtonSupportsLoading(R.style.SPButton_Secondary_Size48,iconDirection =  SPButton.IconDirection.RIGHT),
        SPButtonSupportsLoading(R.style.SPButton_Secondary_Size56, SPButton.IconDirection.LEFT),
        SPButtonSupportsLoading(R.style.SPButton_Primary_Size40),
        SPButtonSupportsLoading(R.style.SPButton_Primary_Size48, SPButton.IconDirection.RIGHT),
        SPButtonSupportsLoading(R.style.SPButton_Primary_Size56, SPButton.IconDirection.LEFT),
        SPButtonSupportsLoading(R.style.SPButton_Hollow_Size40),
        SPButtonSupportsLoading(R.style.SPButton_Hollow_Size48, SPButton.IconDirection.RIGHT),
        SPButtonSupportsLoading(R.style.SPButton_Hollow_Size56, SPButton.IconDirection.LEFT)
    )
}