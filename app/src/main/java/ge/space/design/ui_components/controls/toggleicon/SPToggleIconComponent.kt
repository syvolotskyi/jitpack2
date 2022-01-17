package ge.space.design.ui_components.controls.toggleicon

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.spaceui.databinding.SpToggleIconLayoutBinding
import ge.space.ui.components.controls.SPToggleIcon

class SPToggleIconComponent : ShowCaseComponent {
    override fun getNameResId() = R.string.toggle_icon

    override fun getDescriptionResId() = R.string.toggle_icon_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPToggleIcon>()

            SPToggleIconStyles.list.onEach { buttonSample ->

                val itemBinding = SpToggleIconLayoutBinding.inflate(
                    environment.requireLayoutInflater(),
                    layoutBinding.buttonsLayout
                )

                /*with(itemBinding.buttonName) {
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
                }*/


                //itemBinding.button.setViewStyle(buttonSample.styleId)
            }
            return layoutBinding.root

        }
    }

}