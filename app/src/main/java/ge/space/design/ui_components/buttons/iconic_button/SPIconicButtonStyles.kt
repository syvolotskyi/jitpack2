package ge.space.design.ui_components.buttons.iconic_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val src: Int = R.drawable.ic_plus_24_regular,
)

object SPIconicButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPButton_Iconic_Size24),
        SPButtonSupportsLoading(R.style.SPButton_Iconic_Size32),
        SPButtonSupportsLoading(R.style.SPButton_Iconic_Size40),
        SPButtonSupportsLoading(R.style.SPButton_Iconic_Size48),
        SPButtonSupportsLoading(R.style.SPButton_Iconic_Secondary_Size24),
        SPButtonSupportsLoading(R.style.SPButton_Iconic_Secondary_Size32),
        SPButtonSupportsLoading(R.style.SPButton_Iconic_Secondary_Size40),
        SPButtonSupportsLoading(R.style.SPButton_Iconic_Secondary_Size48)
    )
}