package ge.space.design.ui_components.controls.toggleicon

import com.example.spacedesignsystem.R

data class SPToggleIconSupportsLoading(val styleId: Int)

object SPToggleIconStyles {
    val list = listOf(
        SPToggleIconSupportsLoading(R.style.SPToggleIcon_Unselected),
        SPToggleIconSupportsLoading(R.style.SPToggleIcon_Selected)
    )
}