package ge.space.design.ui_components.buttons.pill

import com.example.spacedesignsystem.R


data class SPPillButtonSupportsLoading(
    val resId: Int = R.style.SPPillItemStandard,
    val title: String,
)

object SPPillButtonStyles {
    val list = listOf(
        SPPillButtonSupportsLoading(title = "first"),
        SPPillButtonSupportsLoading(title = "second"),
        SPPillButtonSupportsLoading(title = "third"),
    )
}