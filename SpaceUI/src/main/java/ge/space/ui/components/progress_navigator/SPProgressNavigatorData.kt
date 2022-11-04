package ge.space.ui.components.progress_navigator

import androidx.annotation.DrawableRes

/**
 * [SPProgressNavigatorData] data class allows to handle [SPProgressNavigatorItem]
 *
 * @param defaultText [String] is a text when the state is [SPProgressNavigatorItem.ProgressState.NORMAL_STATE]
 * @param iconSrc [Int] is an icon whe the state is [SPProgressNavigatorItem.ProgressState.NORMAL_STATE]
 * @param successText [String]  is a text when the state is [SPProgressNavigatorItem.ProgressState.SUCCESS_STATE]
 * @param state [SPProgressNavigatorItem.ProgressState]  is a state of the current progress navigator item
 */
data class SPProgressNavigatorData(
    val defaultText: String,
    val successText: String,
    @DrawableRes val iconSrc: Int,
    var state: SPProgressNavigatorItem.ProgressState
)