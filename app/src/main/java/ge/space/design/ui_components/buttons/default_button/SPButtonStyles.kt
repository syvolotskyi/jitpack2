package ge.space.design.ui_components.buttons.default_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val supportsDisabled: Boolean = true,
)

object SPButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPButtonWhiteView, true),
        SPButtonSupportsLoading(R.style.SPButtonWhiteWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButtonWhiteWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPButtonAccentView, true),
        SPButtonSupportsLoading(R.style.SPButtonAccentWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButtonAccentWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPButtonDarkView, true),
        SPButtonSupportsLoading(R.style.SPButtonDarkWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButtonDarkWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPButtonTransparentView, true),
        SPButtonSupportsLoading(R.style.SPButtonTransparentWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButtonTransparentWithRightArrow, true)
    )
}