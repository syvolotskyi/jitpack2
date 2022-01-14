package ge.space.design.ui_components.controls

import com.example.spacedesignsystem.R

data class SPToggleSwitchSupportsLoading(val styleId: Int)

object SPToggleSwitchStyles {
    val list = listOf(
        SPToggleSwitchSupportsLoading(R.style.SPToggleSwitch_Disabled_Unselected),
        SPToggleSwitchSupportsLoading(R.style.SPToggleSwitch_Disabled_Selected),
        SPToggleSwitchSupportsLoading(R.style.SPToggleSwitch_Enabled_Unselected),
        SPToggleSwitchSupportsLoading(R.style.SPToggleSwitch_Enabled_Selected)
    )
}