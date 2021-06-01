package ge.space.design.ui_components.buttons.horizontal_button

import com.example.spacedesignsystem.R


data class SPButtonSupportsLoading(
        val resId: Int,
        val supportsDisabled: Boolean = false,
        val src: Int = R.drawable.ic_launcher_foreground,
)

object SPHorizontalButtonStyles {
    val list = listOf(
            SPButtonSupportsLoading(R.style.SPBaseView_SPBaseHorizontalButton, true),
    )
}