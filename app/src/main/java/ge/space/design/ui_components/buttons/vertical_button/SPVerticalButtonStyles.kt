package ge.space.design.ui_components.buttons.vertical_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val supportsDisabled: Boolean = false,
    val src: Int = R.drawable.ic_share_ios_24_regular,
)

object SPVerticalButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPBaseVerticalButton, true),
        SPButtonSupportsLoading(R.style.SPLargeVerticalButton, true),
    )
}