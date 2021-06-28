package ge.space.design.ui_components.tab_navigation.two

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpTwoTabNavigationShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPTwoTabNavigationComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.component_two_tab_navigation

    override fun getDescriptionResId(): Int = R.string.component_two_tab_navigation_description

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding = SpTwoTabNavigationShowCaseBinding.inflate(environmentSP.requireLayoutInflater())

            with(binding) {

            }
            return binding.root
        }

    }

}