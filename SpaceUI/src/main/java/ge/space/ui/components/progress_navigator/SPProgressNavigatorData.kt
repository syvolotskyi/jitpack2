package ge.space.ui.components.progress_navigator

import androidx.annotation.DrawableRes

/**
 * [SPProgressNavigatorData] data class allows to handle [SPProgressNavigatorView]
 *
 * @param defaultText [String] is a text when the state is [SPProgressNavigatorView.ProgressState.NORMAL_STATE]
 * @param defaultIcon [Int] is an icon whe the state is [SPProgressNavigatorView.ProgressState.NORMAL_STATE]
 * @param successText [String]  is a text when the state is [SPProgressNavigatorView.ProgressState.SUCCESS_STATE]
 */
data class SPProgressNavigatorData(
    val defaultText: String,
    @DrawableRes val defaultIcon: Int,
    val successText: String
)
