package ge.space.design.ui_components.buttons.default_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
    val resId: Int,
    val supportsDisabled: Boolean = false,
)

object SPButtonStyles {
    val list = listOf(
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_White, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_White_ArrowLeft, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_White_ArrowRight, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_Accent, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_Accent_ArrowLeft, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_Accent_ArrowRight, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_Dark, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_Dark_ArrowLeft, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_Dark_ArrowRight, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_Transparent, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_Transparent_ArrowLeft, true),
        SPButtonSupportsLoading(R.style.SPBaseView_SPBaseButton_Transparent_ArrowRight, true)
    )
}