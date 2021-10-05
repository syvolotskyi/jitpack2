package ge.space.design.ui_components.buttons.default_button

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemButtonsShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsDefaultShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.spaceui.databinding.SpButtonLayoutBinding
import ge.space.ui.components.buttons.SPButton
import ge.space.ui.components.buttons.base.SPButtonBaseView

class SPDefaultButtonsComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.default_buttons

    override fun getDescriptionResId(): Int = R.string.default_button_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsDefaultShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPButtonBaseView<SpButtonLayoutBinding>>()

            SPButtonStyles.list.onEach { buttonSample ->

                val resId = buttonSample.resId

                val itemBinding = SpItemButtonsShowcaseBinding.inflate(
                    environment.requireLayoutInflater(),
                    layoutBinding.buttonsLayout,
                    true
                )
                itemBinding.button.directionIcon = buttonSample.iconDirection
                itemBinding.button.src = buttonSample.src

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

                itemBinding.wrapContentCheck.setOnCheckedChangeListener { _, isChecked ->
                    with(itemBinding.button) {
                        val newParams = layoutParams

                        newParams.width = if (isChecked) {
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        } else {
                            LinearLayout.LayoutParams.MATCH_PARENT
                        }

                        layoutParams = newParams
                    }
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
                itemBinding.button.text = "Button"
            }
            return layoutBinding.root

        }
    }

}