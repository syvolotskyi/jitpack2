package ge.space.design.ui_components.tab_navigation.child

import android.content.Context
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpTabNavigationShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.tab_navigation.extensions.createNavigationWithThreeTabs
import ge.space.design.ui_components.tab_navigation.extensions.createNavigationWithTwoTabs
import ge.space.design.ui_components.tab_navigation.mode.SPTabNavigationMode
import ge.space.ui.components.tab_navigation.SPTabNavigationView

class SPTabNavigationViewComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_tab_navigation

    override fun getDescriptionResId(): Int = R.string.component_tab_navigation_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpTabNavigationShowCaseBinding.inflate(environment.requireLayoutInflater())
            with(binding) {
                setUpTabNavigationView(twoTabNavigationView, environment.context,SPTabNavigationMode.DOUBLE)
                setUpTabNavigationView(threeTabNavigationView, environment.context,SPTabNavigationMode.TRIPLE)

            }
            return binding.root
        }

        private fun setUpTabNavigationView(tabNavigationView: SPTabNavigationView, context: Context, tabMode: SPTabNavigationMode) {
            when(tabMode){
                SPTabNavigationMode.DOUBLE ->{
                    tabNavigationView.items = createNavigationWithTwoTabs(context)
                    tabNavigationView.setUp(clickListener = { selectedModel ->
                        Toast.makeText(context, selectedModel.text, Toast.LENGTH_SHORT).show() })
                }
                SPTabNavigationMode.TRIPLE ->{
                    tabNavigationView.items = createNavigationWithThreeTabs(context)
                    tabNavigationView.setUp(clickListener = { selectedModel ->
                        Toast.makeText(context, selectedModel.text, Toast.LENGTH_SHORT).show() })

                }
            }
        }
    }
}