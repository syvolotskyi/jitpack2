package ge.space.design.ui_components.buttons.chip

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutButtonChipShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPButtonChipComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.button_chip_showcase

    override fun getDescriptionResId(): Int = R.string.button_chip_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonChipShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            return layoutBinding.root
        }
    }
}