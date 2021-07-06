package ge.space.ui.components.tab_navigation.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Applies a type of a Tab navigation. It can be next states:
 *
 * <p>
 *     1. Title resource
 *     2. Image resource
 *     3. Active status to check specific background
 * <p>
 */

data class SPTabNavigationModel(
    @StringRes var title: Int,
    @DrawableRes var image: Int
)
