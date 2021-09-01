package ge.space.design.ui_components.tab_navigation

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.tab_navigation.child.SPTabNavigationViewComponent

class SPTabNavigationComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_tab_navigation

    override fun getDescriptionResId(): Int = R.string.component_tab_navigation_description

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            SPTabNavigationViewComponent()
        )
    }
}