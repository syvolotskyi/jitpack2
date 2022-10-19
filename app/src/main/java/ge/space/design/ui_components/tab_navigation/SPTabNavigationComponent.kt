package ge.space.design.ui_components.tab_navigation

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTabNavigationShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.tab_switcher.SPTabNavigation

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
                    setSelectedTab(SPTabNavigation.FIRST_TAB)
                    setOnTabSelectedListener { title, key ->
                        Toast.makeText(
                            environment.requireActivity(),
                            S"$title\nKey:$key",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            return layoutBinding.root
        }
    }
}