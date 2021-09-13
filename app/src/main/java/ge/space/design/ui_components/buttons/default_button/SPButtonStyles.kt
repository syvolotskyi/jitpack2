package ge.space.design.ui_components.buttons.default_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val supportsDisabled: Boolean = true,
)

object SPButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPButton_Secondary, true),
        SPButtonSupportsLoading(R.style.SPButton_SecondaryWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_SecondaryWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_Primary, true),
        SPButtonSupportsLoading(R.style.SPButton_PrimaryWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_PrimaryWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_TransparentView, true),
        SPButtonSupportsLoading(R.style.SPButton_TransparentWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPButton_TransparentWithRightArrow, true)
    )
}