package ge.space.design.ui_components.buttons.vertical_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val supportsDisabled: Boolean = false,
    val src: Int = R.drawable.ic_launcher_background,
)

object SPVerticalButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseVerticalButton, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseVerticalButton_Large, true),
    )
}