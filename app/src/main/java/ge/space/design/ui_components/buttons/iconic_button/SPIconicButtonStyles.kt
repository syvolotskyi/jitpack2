package ge.space.design.ui_components.buttons.iconic_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val supportsDisabled: Boolean = false,
    val src: Int = R.drawable.ic_share_ios_24_regular,
)

object SPiconicButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPButton_IconicalBase, true),
    )
}