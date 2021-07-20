package ge.space.ui.components.tab_navigation.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
/**
 * Tab navigation item model with several parameters:
 *
 * @property text [Int] value which sets a title.
 * @property image [Int] value which sets a image.
 * @property style [StyleRes] value which sets a style.
 */
data class SPTabNavigationModel(
    var text: String,
    @DrawableRes var image: Int,
    @StyleRes val style: Int  = R.style.SPTabChildNavigation
)
