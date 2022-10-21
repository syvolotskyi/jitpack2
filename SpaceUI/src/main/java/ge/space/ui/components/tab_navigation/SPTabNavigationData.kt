package ge.space.ui.components.tab_navigation

import android.view.View
import ge.space.ui.util.extension.EMPTY_TEXT

/**
 * Data class allows to create a new unselectedTab data
 *
 * @param tabView [View] is unselected tab view
 * @param title [String] is title text
 */
data class SPTabNavigationData(var tabView: View, var title :String = EMPTY_TEXT)