package ge.space.design.ui_components.tab_navigation.one

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpOneTabNavigationShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment


class SPOneTabNavigationComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.component_one_tab_navigation

    override fun getDescriptionResId(): Int = R.string.component_one_tab_navigation_description

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding = SpOneTabNavigationShowCaseBinding.inflate(environmentSP.requireLayoutInflater())

            with(binding) {

            }
            return binding.root
        }

    }

}