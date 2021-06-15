package ge.space.design.ui_components.buttons.default_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val supportsDisabled: Boolean = true,
)

object SPButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPButton_WhiteView, true),
        SPButtonSupportsLoading(R.style.SPButton_WhiteWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_WhiteWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_AccentView, true),
        SPButtonSupportsLoading(R.style.SPButton_AccentWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_AccentWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_DarkView, true),
        SPButtonSupportsLoading(R.style.SPButton_DarkWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_DarkWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_TransparentView, true),
        SPButtonSupportsLoading(R.style.SPButton_TransparentWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_TransparentWithRightArrow, true)
    )
}