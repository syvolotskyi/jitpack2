package ge.space.design.ui_components.controls

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.controls.SPToggleSwitch

class SPToggleSwitchComponent : ShowCaseComponent {

    override fun getNameResId() = R.string.toggle_switch

    override fun getDescriptionResId() = R.string.toggle_switch_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPToggleSwitch>()

            SPToggleSwitchStyles.list.onEach { switchSample ->

                /*val itemBinding = SpItemIconicButtonsShowcaseBinding.inflate(
                    environment.requireLayoutInflater(),
                    layoutBinding.buttonsLayout,
                    true
                )

                with(itemBinding.buttonName) {
                    val resName = resources.getResourceEntryName(switchSample.styleId)
                    text = resName.substringAfter(".", resName)
                }

                itemBinding.distractiveCheck.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.button.isDistractive = isChecked
                }

                itemBinding.button.src = switchSample.src

                buttons.add(itemBinding.button)

                itemBinding.button.setOnClickListener {
                    Toast.makeText(environment.context, "Clicked", Toast.LENGTH_SHORT).show()
                }

                itemBinding.disableCheck.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.button.isEnabled = !isChecked
                }


                itemBinding.button.setViewStyle(switchSample.styleId)*/
            }
            return layoutBinding.root

        }
    }
}