package ge.space.ui.components.feature_list

import ge.space.spaceui.R
import ge.space.ui.components.feature_list.SPFeatureListItem.Orientation

/**
 * Data class helps to create SPFeatureListItem
 *
 * @param title [String] is item title.
 * @param desc [String] is item description.
 * @param orientation [String] sets a description text position.
 * @param style [String] is style of SPFeatureListItem.
 */
data class SPFeatureData(
    val title: String,
    val desc: String,
    var orientation: Orientation = Orientation.Horizontal,
    var style:Int = R.style.SPFeatureListItem
)