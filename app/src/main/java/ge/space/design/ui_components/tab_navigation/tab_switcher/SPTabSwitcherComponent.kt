package ge.space.design.ui_components.tab_navigation.tab_switcher

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTabSwitcherShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPTabSwitcherComponent  : ShowCaseComponent {

    override fun getNameResId() = R.string.tab_switcher

    override fun getDescriptionResId() = R.string.tab_switcher_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            SpLayoutTabSwitcherShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            ).apply {


                return root
            }
        }
    }


}