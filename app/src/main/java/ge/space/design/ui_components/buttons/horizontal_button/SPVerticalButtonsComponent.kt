package ge.space.design.ui_components.buttons.horizontal_button

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemHorizontalBtnShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.ComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.ShowCaseEnvironment
import ge.space.spaceui.databinding.SpHorizontalButtonLayoutBinding
import ge.space.ui.base.SPBaseButton

class SPHorizontalButtonsComponent : ShowCaseComponent {
    override fun getNameResId(): Int = R.string.horizontal_buttons

    override fun getDescriptionResId(): Int = R.string.horizontal_button_description

    override fun getComponentClass(): Class<*>? = Factory::class.java


    class Factory : ComponentFactory {
        override fun create(environment: ShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPBaseButton<SpHorizontalButtonLayoutBinding>>()
            SPHorizontalButtonStyles.list.onEach { buttonSample ->

                val resId = buttonSample.resId
                val supportsDisable = buttonSample.supportsDisabled


                val itemBinding = SpItemHorizontalBtnShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(resId),
                    layoutBinding.buttonsLayout,
                    true
                )

                if (!supportsDisable)
                    itemBinding.disableCheck.visibility = View.GONE

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
                itemBinding.button.style(buttonSample.resId)
                itemBinding.button.src = buttonSample.src
                itemBinding.button.text = "Button"
            }
            return layoutBinding.root

        }
    }

}
