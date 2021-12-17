package ge.space.design.ui_components.text_fields.phone

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpPhoneInputShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.base.setupDateInput
import ge.space.ui.components.text_fields.input.base.setupNumberInput
import ge.space.ui.components.text_fields.input.base.setupPhoneInput

class SPMaskedComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.masked_input

    override fun getDescriptionResId(): Int = R.string.masked_input_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpPhoneInputShowcaseBinding.inflate(environment.requireLayoutInflater())
            with(binding) {
                setupPhoneInputTextWithDone(phoneInput, environment.context)
                setupPhoneInputTextWithDone(phoneInputSecond, environment.context)
            }
            with(binding.phoneInput) {
                setupPhoneInput(
                    resources.getString(R.string.phone_prefix),
                    resources.getString(R.string.phone_mask))
            }

            binding.dateInputSecond.setupDateInput(binding.phoneInputSecond.context.getString(R.string.day_mask))

            binding.labelTextInput.doOnTextChanged { text, _, _, _ ->
                binding.phoneInput.labelText = text.toString()
                binding.phoneInputSecond.labelText = text.toString()
            }
            return binding.root

        }

        private fun setupPhoneInputTextWithDone(phoneInput: SPTextFieldInput, context: Context) {
            phoneInput.setOnEditorActionListener(TextView.OnEditorActionListener
            { _: TextView?, actionId: Int, event: KeyEvent? ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event?.action == KeyEvent.ACTION_DOWN
                ) {
                    showToast(context,"$ACTION_DONE ${phoneInput.text}")
                    return@OnEditorActionListener true
                } else if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_FLAG_NAVIGATE_NEXT) {
                    showToast(context,"$ACTION_NEXT ${phoneInput.text}")
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
        private const val ACTION_DONE = "Action Done: "
        private const val ACTION_NEXT = "1010"
    }
}