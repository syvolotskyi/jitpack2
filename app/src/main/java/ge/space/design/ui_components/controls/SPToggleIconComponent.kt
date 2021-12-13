package ge.space.design.ui_components.controls

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemIconicButtonsShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.buttons.iconic_button.SPIconicButtonStyles
import ge.space.ui.components.buttons.SPButtonIconic

class SPToggleIconComponent : ShowCaseComponent {
    override fun getNameResId() = R.string.toggle_icon

    override fun getDescriptionResId() = R.string.toggle_icon_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPButtonIconic>()

            SPIconicButtonStyles.list.onEach { buttonSample ->

                val resId = buttonSample.resId

                val itemBinding = SpItemIconicButtonsShowcaseBinding.inflate(
                    environment.requireLayoutInflater(),
                    layoutBinding.buttonsLayout,
                    true
                )

                with(itemBinding.buttonName) {
                    val resName = resources.getResourceEntryName(resId)
                    text = resName.substringAfter(".", resName)
                }

                itemBinding.distractiveCheck.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.button.isDistractive = isChecked
                }

                itemBinding.button.src = buttonSample.src

                buttons.add(itemBinding.button)

                itemBinding.button.setOnClickListener {
                    Toast.makeText(environment.context, "Clicked", Toast.LENGTH_SHORT).show()
                }

                itemBinding.disableCheck.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.button.isEnabled = !isChecked
                }


                itemBinding.button.setViewStyle(buttonSample.resId)
            }
            return layoutBinding.root

        }
    }

}