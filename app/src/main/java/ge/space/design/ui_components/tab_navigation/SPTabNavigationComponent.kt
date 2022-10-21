package ge.space.design.ui_components.tab_navigation

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTabNavigationShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.tab_navigation.SPTabNavigation

class SPTabNavigationComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.tab_navigation_showcase

    override fun getDescriptionResId(): Int = R.string.tab_navigation_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTabNavigationShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            ).apply {
                tabNavigation.apply {
                    setTabs(listOf("Tab 1","Tab 2","Tab 3"))
                    setSelectedTab(SPTabNavigation.SPNavigationTabs.FIRST_TAB)
                    setOnTabSelectedListener { title, key ->
                        when (key){
                            SPTabNavigation.SPNavigationTabs.FIRST_TAB -> environment.showToast(title, key)
                            SPTabNavigation.SPNavigationTabs.SECOND_TAB -> environment.showToast(title, key)
                            SPTabNavigation.SPNavigationTabs.THIRD_TAB -> environment.showToast(title, key)
                        }
                    }
                }
            }

            return layoutBinding.root
        }

        private fun SPShowCaseEnvironment.showToast(title: String,tab:SPTabNavigation.SPNavigationTabs ) =
            Toast.makeText(requireActivity(), "$title\nKey:${tab.tabIndex}", Toast.LENGTH_SHORT).show()
    }
}