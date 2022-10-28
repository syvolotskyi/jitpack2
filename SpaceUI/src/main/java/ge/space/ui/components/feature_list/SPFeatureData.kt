package ge.space.ui.components.feature_list

import ge.space.spaceui.R
import ge.space.ui.components.feature_list.SPFeatureItem.Orientation

/**
 * Data class helps to create SPFeatureItem
 *
 * @param title [String] is item title.
 * @param desc [String] is item description.
 * @param orientation [String] sets a description text position.
 * @param style [String] is style of SPFeatureItem.
 */
data class SPFeatureData(
    val title: String,
    val desc: String,
    var orientation: Orientation = Orientation.Horizontal,
    var style:Int = R.style.SPFeatureItem
)