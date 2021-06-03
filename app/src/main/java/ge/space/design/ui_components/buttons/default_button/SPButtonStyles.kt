package ge.space.design.ui_components.buttons.default_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val supportsDisabled: Boolean = true,
)

object SPButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPWhiteButtonView, true),
        SPButtonSupportsLoading(R.style.SPWhiteButtonViewWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPWhiteButtonViewWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPAccentButtonView, true),
        SPButtonSupportsLoading(R.style.SPAccentButtonViewWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPAccentButtonViewWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPDarkButtonView, true),
        SPButtonSupportsLoading(R.style.SPDarkButtonViewWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPDarkButtonViewWithRightArrow, true),
        SPButtonSupportsLoading(R.style.SPTransparentButtonView, true),
        SPButtonSupportsLoading(R.style.SPTransparentButtonViewWithLeftArrow, true),
        SPButtonSupportsLoading(R.style.SPTransparentButtonViewWithRightArrow, true)
    )
}