package ge.space.design.ui_components.buttons.horizontal_button

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemHorizontalBtnShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.buttons.SPButtonHorizontal

class SPHorizontalButtonsComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.horizontal_buttons

    override fun getDescriptionResId(): Int = R.string.horizontal_button_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPButtonHorizontal>()
            SPHorizontalButtonStyles.list.onEach { buttonSample ->

                val resId = buttonSample.resId

                val itemBinding = SpItemHorizontalBtnShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(resId),
                    layoutBinding.buttonsLayout,
                    true
                )

                with(itemBinding.buttonName) {
                    val resName = resources.getResourceEntryName(resId)
                    text = resName.substringAfter(".", resName)
                }

                buttons.add(itemBinding.button)

                itemBinding.button.setOnClickListener {
                    Toast.makeText(environment.context, "Clicked", Toast.LENGTH_SHORT).show()
                }

                itemBinding.disableCheck.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.button.isEnabled = !isChecked
                }

                itemBinding.distractiveCheck.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.button.isDistractive = isChecked
                }

                layoutBinding.textInput.addTextChangedListener(object : TextWatcher {

                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        buttons.onEach { it.text = s.toString() }
                    }
                })
                itemBinding.button.setViewStyle(buttonSample.resId)
                itemBinding.button.src = buttonSample.src
                itemBinding.button.text = "Button"
            }
            return layoutBinding.root

        }
    }

}
