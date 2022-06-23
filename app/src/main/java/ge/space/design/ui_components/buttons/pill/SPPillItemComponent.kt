package ge.space.design.ui_components.buttons.pill

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutButtonPillShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPPillItemComponent  : ShowCaseComponent {

    override fun getNameResId() = R.string.drawer_pill

    override fun getDescriptionResId() = R.string.drawer_pill_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonPillShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            layoutBinding.radioGroup.setOnCheckedChangeListener { _updateStatesTextAppearances, id ->
                when (id) {
                    layoutBinding.toggleSwitch1.id -> {
                        Toast.makeText(environment.context, "Press 1", Toast.LENGTH_SHORT).show()
                    }
                    layoutBinding.toggleSwitch2.id -> {
                        Toast.makeText(environment.context, "Press 2", Toast.LENGTH_SHORT).show()
                    }
                    layoutBinding.toggleSwitch3.id -> {
                        Toast.makeText(environment.context, "Press 3", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            return layoutBinding.root
        }
    }
}