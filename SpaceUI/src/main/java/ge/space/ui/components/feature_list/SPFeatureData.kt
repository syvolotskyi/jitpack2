package ge.space.ui.components.feature_list

import ge.space.spaceui.R
import ge.space.ui.components.feature_list.SPFeatureItem.Orientation

data class SPFeatureData(
    val title: String,
    val desc: String,
    var orientation: Orientation = Orientation.Horizontal,
    var style:Int = R.style.SPFeatureItem
)