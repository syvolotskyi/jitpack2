package ge.space.design.ui_components.pins.password

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpPasswordShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPPasswordComponent: SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.password_pin

    override fun getDescriptionResId(): Int = R.string.password_pin_desc

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding = SpPasswordShowCaseBinding.inflate(environmentSP.requireLayoutInflater())
            return binding.root
        }
    }
}