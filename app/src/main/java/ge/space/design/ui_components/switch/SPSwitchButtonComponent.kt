package ge.space.design.ui_components.switch

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutSwitchButtonShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPSwitchButtonComponent : SPShowCaseComponent {

    override fun getNameResId() = R.string.switch_button

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {

        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            return SpLayoutSwitchButtonShowcaseBinding.inflate(environmentSP.requireLayoutInflater()).let {
                it.switchView.setOnCheckedChangeListener { _, isChecked ->
                    Toast.makeText(environmentSP.context, isChecked.toString(), Toast.LENGTH_SHORT).show()
                }
                it.root
            }
        }

    }
}