package ge.space.design.ui_components.controls.radio_buttons

import android.widget.Toast
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

            layoutBinding.radioGroup.setOnCheckedChangeListener { radioGroup, id ->
                when(id){
                    layoutBinding.radio1.id -> {
                        Toast.makeText(environment.context, "Press 1", Toast.LENGTH_SHORT).show()
                    }
                    layoutBinding.radio2.id -> {
                        Toast.makeText(environment.context, "Press 2", Toast.LENGTH_SHORT).show()
                    }
                    layoutBinding.radio3.id -> {
                        Toast.makeText(environment.context, "Press 3", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            return layoutBinding.root
        }
    }
}