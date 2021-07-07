package ge.space.design.ui_components.tab_navigation.child


import android.content.Context
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpTabNavigationShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.tab_navigation.extensions.createNavigationWithThreeTabs
import ge.space.design.ui_components.tab_navigation.extensions.createNavigationWithTwoTabs
import ge.space.design.ui_components.tab_navigation.mode.SPTabNavigationMode
import ge.space.ui.components.tab_navigation.SPTabNavigationView

class SPTabNavigationViewComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.component_tab_navigation

    override fun getDescriptionResId(): Int = R.string.component_tab_navigation_description

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {

            val binding = SpTabNavigationShowCaseBinding.inflate(environmentSP.requireLayoutInflater())

            with(binding) {
                setUpTabNavigationView(twoTabNavigationView, environmentSP.context,SPTabNavigationMode.TWO)
                setUpTabNavigationView(threeTabNavigationView, environmentSP.context,SPTabNavigationMode.THREE)

            }
            return binding.root
        }

        private fun setUpTabNavigationView(tabNavigationView: SPTabNavigationView, context: Context, tabMode: SPTabNavigationMode) {
            when(tabMode){
                SPTabNavigationMode.TWO ->{
                    tabNavigationView.setUp(createNavigationWithTwoTabs(), clickListener = { selectedModel -> Toast.makeText(context, selectedModel.title, Toast.LENGTH_SHORT).show() })

                }
                SPTabNavigationMode.THREE ->{
                    tabNavigationView.setUp(createNavigationWithThreeTabs(), clickListener = { selectedModel -> Toast.makeText(context, selectedModel.title, Toast.LENGTH_SHORT).show() })

                }
            }
        }
    }

}