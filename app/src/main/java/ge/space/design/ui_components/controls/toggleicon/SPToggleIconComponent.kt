package ge.space.design.ui_components.controls.toggleicon

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutToggleIconShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPToggleIconComponent : ShowCaseComponent {
    override fun getNameResId() = R.string.toggle_icon

    override fun getDescriptionResId() = R.string.toggle_icon_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutToggleIconShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            // init addOnCheckedChangeListener
            with(layoutBinding) {
                listOf(toggleIcon, toggleIcon2, toggleIcon3, toggleIcon4).forEach { toggleBtn ->
                    toggleBtn.isChecked = true
                    toggleBtn.addOnCheckedChangeListener { button, isChecked ->
                        Toast.makeText(environment.context, "${button.id} is: $isChecked", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            return layoutBinding.root
        }
    }

}