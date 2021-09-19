package ge.space.design.ui_components.buttons.iconic_button

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemIconicButtonsShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.spaceui.databinding.SpButtonVerticalLayoutBinding
import ge.space.ui.components.buttons.SPButton
import ge.space.ui.components.buttons.SPButtonIconic
import ge.space.ui.components.buttons.base.SPButtonBaseView

class SPIconicButtonsComponent : ShowCaseComponent {
    override fun getNameResId(): Int = R.string.iconic_buttons

    override fun getDescriptionResId(): Int = R.string.iconic_button_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPButtonIconic>()
            
            SPVerticalButtonStyles.list.onEach { buttonSample ->

                val resId = buttonSample.resId

                val itemBinding = SpItemIconicButtonsShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(resId),
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