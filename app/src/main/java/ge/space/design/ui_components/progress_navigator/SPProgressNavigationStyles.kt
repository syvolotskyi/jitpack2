package ge.space.design.ui_components.progress_navigator

import com.example.spacedesignsystem.R
import ge.space.ui.components.progress_navigator.SPProgressNavigatorData
import ge.space.ui.components.progress_navigator.SPProgressNavigatorItem

object SPProgressNavigationStyles {
    val list = listOf(
        SPProgressNavigatorData("Step 1", "Step 1 - Done!", R.drawable.ic_calendar_24_regular, SPProgressNavigatorItem.ProgressState.SUCCESS_STATE),
        SPProgressNavigatorData("Step 2", "Step 2 - Done!", R.drawable.ic_charity_24_regular, SPProgressNavigatorItem.ProgressState.SUCCESS_STATE),
        SPProgressNavigatorData("Step 3", "Step 3 - Done!", R.drawable.ic_clock_backward_24_regular, SPProgressNavigatorItem.ProgressState.NORMAL_STATE),
    )
}