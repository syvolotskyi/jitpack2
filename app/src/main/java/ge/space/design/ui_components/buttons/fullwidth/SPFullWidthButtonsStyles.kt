package ge.space.design.ui_components.buttons.fullwidth

import com.example.spacedesignsystem.R
import ge.space.ui.components.buttons.SPButton

data class SPFullwidthButtonSupportsLoading(
        val resId: Int,
        val iconDirection: SPButton.IconDirection = SPButton.IconDirection.None,
        val src: Int = R.drawable.ic_share_ios_24_regular,
)

object SPFullWidthButtonsStyles {
    val list = listOf(
            SPFullwidthButtonSupportsLoading(R.style.SPButton_FullWidth_Card),
            SPFullwidthButtonSupportsLoading(R.style.SPButton_FullWidth_Card, SPButton.IconDirection.Right),
            SPFullwidthButtonSupportsLoading(R.style.SPButton_FullWidth_Card, SPButton.IconDirection.Left),
            SPFullwidthButtonSupportsLoading(R.style.SPButton_FullWidth_Keyboard),
            SPFullwidthButtonSupportsLoading(R.style.SPButton_FullWidth_Keyboard, SPButton.IconDirection.Right),
            SPFullwidthButtonSupportsLoading(R.style.SPButton_FullWidth_Keyboard, SPButton.IconDirection.Left),
    )
}