package ge.space.ui.components.tab_navigation.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Tab navigation item model with several parameters:
 *
 * @property title [Int] value which sets a title.
 * @property image [Int] value which sets a image.
 */

data class SPTabNavigationModel(
    @StringRes var title: Int,
    @DrawableRes var image: Int
)
