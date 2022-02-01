package ge.space.design.ui_components.controls.radio_buttons

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutRadioButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPRadioButtonComponent : ShowCaseComponent {

    override fun getNameResId() = R.string.radio_buttons

    override fun getDescriptionResId() = R.string.radio_buttons_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutRadioButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            return layoutBinding.root
        }
    }
}