package ge.space.design.ui_components.buttons.inline

import com.example.spacedesignsystem.R


data class SPInlineButtonSupportsLoading(
    val resId: Int,
    val src: Int = R.drawable.ic_calendar_24_regular,
)

object SPInlineButtonStyles {
    val list = listOf(
        SPInlineButtonSupportsLoading(R.style.SPButtonInline_Primary),
        SPInlineButtonSupportsLoading(R.style.SPButtonInline_Secondary),
    )
}