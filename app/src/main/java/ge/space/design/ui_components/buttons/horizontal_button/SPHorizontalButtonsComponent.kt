package ge.space.design.ui_components.buttons.horizontal_button

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemHorizontalBtnShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.spaceui.databinding.SpButtonHorizontalLayoutBinding
import ge.space.ui.components.buttons.base.SPButtonBaseView

class SPHorizontalButtonsComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.horizontal_buttons

    override fun getDescriptionResId(): Int = R.string.horizontal_button_description

    override fun getComponentClass(): Class<*>? = FactorySP::class.java


    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPButtonBaseView<SpButtonHorizontalLayoutBinding>>()
            SPHorizontalButtonStyles.list.onEach { buttonSample ->

                val resId = buttonSample.resId
                val supportsDisable = buttonSample.supportsDisabled


                val itemBinding = SpItemHorizontalBtnShowcaseBinding.inflate(
                    environmentSP.requireThemedLayoutInflater(resId),
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
                    Toast.makeText(environmentSP.context, "Clicked", Toast.LENGTH_SHORT).show()
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
