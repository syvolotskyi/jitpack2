package ge.space.design.ui_components.accordion

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutAccordionShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPAccordionComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.accordion_showcase

    override fun getDescriptionResId(): Int = R.string.accordion_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutAccordionShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            return layoutBinding.root
        }
    }
}