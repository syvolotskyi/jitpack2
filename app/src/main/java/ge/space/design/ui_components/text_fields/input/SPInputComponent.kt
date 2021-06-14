package ge.space.design.ui_components.text_fields.input

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpTextInputShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.input.text_input.SPTextFieldInput
import kotlinx.android.synthetic.main.sp_item_component.view.*

class SPInputComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.text_input

    override fun getDescriptionResId(): Int = R.string.text_input_desc

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding =
                SpTextInputShowcaseBinding.inflate(environmentSP.requireLayoutInflater())

            with(binding) {
                setupInputTextWithDone(phoneSimpleInput, environmentSP.context)
                cbDisable.setOnCheckedChangeListener { _, isChecked ->
                    phoneSimpleInput.isEnabled = !isChecked
                }
                cbDescription.setOnCheckedChangeListener { _, isChecked ->
                    phoneSimpleInput.descriptionText = if (isChecked) {
                        phoneSimpleInput.resources.getString(R.string.description)
                    } else {
                        EMPTY_STRING
                    }
                }
            }

            binding.labelTextInput.doOnTextChanged { text, _, _, _ ->
                binding.phoneSimpleInput.labelText = text.toString()
                binding.phoneRemovableInput.labelText = text.toString()
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

        private fun showToast(context: Context, text: String) {
            Toast.makeText(
                context,
                text,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val EMPTY_STRING = ""
    }

}