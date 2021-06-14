package ge.space.design.ui_components.text_fields.input

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemVerticalButtonsShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import com.example.spacedesignsystem.databinding.SpTextInputShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.buttons.vertical_button.SPVerticalButtonStyles
import ge.space.spaceui.databinding.SpButtonVerticalLayoutBinding
import ge.space.spaceui.databinding.SpTextFieldTextLayoutBinding
import ge.space.ui.components.buttons.base.SPButtonBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.components.text_fields.input.phone_input.SPTextFieldPhone
import ge.space.ui.components.text_fields.input.text_input.SPTextFieldInput

class SPInputComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.text_input

    override fun getDescriptionResId(): Int = R.string.text_input_desc

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding =
                SpTextInputShowcaseBinding.inflate(environmentSP.requireLayoutInflater())

            with(binding) {
                disableDescCheck.setOnCheckedChangeListener { _, isChecked -> inputDescInput.isEnabled = isChecked}
                disableSimpleCheck.setOnCheckedChangeListener { _, isChecked -> phoneSimpleInput.isEnabled = isChecked}
                setupInputTextWithDone(inputDescInput, environmentSP.context)
                setupInputTextWithDone(phoneSimpleInput, environmentSP.context)
            }

            binding.labelTextInput.doOnTextChanged { text, _, _, _ ->
                binding.phoneSimpleInput.labelText = text.toString()
                binding.inputDescInput.labelText = text.toString()
            }

            return binding.root
        }

        private fun setupInputTextWithDone(phoneInput: SPTextFieldInput, context: Context) {
            phoneInput.setOnEditorActionListener(
                TextView.OnEditorActionListener
                { _: TextView?, actionId: Int, event: KeyEvent? ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event?.action == KeyEvent.ACTION_DOWN
                    ) {
                        showToast(context, "Action Done: " + phoneInput.text)
                        return@OnEditorActionListener true
                    } else if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_FLAG_NAVIGATE_NEXT) {
                        showToast(context, "Action Next: " + phoneInput.text)
                    }

                    return@OnEditorActionListener false
                })
        }

        fun showToast(context: Context, text: String) {
            Toast.makeText(
                context,
                text,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val CORRECT_BIG_PASSWORD = "888888"
        const val CORRECT_SMALL_PASSWORD = "1010"
    }

}