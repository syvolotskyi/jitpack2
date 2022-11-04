package ge.space.ui.components.progress_navigator

import androidx.annotation.DrawableRes

/**
 * [SPProgressNavigatorData] data class allows to handle [SPProgressNavigatorItem]
 *
 * @param defaultText [String] is a text when the state is [SPProgressNavigatorItem.ProgressState.NORMAL_STATE]
 * @param defaultIcon [Int] is an icon whe the state is [SPProgressNavigatorItem.ProgressState.NORMAL_STATE]
 * @param successText [String]  is a text when the state is [SPProgressNavigatorItem.ProgressState.SUCCESS_STATE]
 */
data class SPProgressNavigatorData(
    val defaultText: String,
    val successText: String,
    @DrawableRes val defaultIcon: Int
)