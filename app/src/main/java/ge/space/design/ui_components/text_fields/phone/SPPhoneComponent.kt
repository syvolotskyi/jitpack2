package ge.space.design.ui_components.text_fields.phone

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpPasswordShowCaseBinding
import com.example.spacedesignsystem.databinding.SpPhoneInputShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.phone.SPPhoneInput
import ge.space.ui.components.text_fields.pin.OnPinEnteredListener
import ge.space.ui.components.text_fields.pin.SPPinEntryView

class SPPhoneComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.phone_input

    override fun getDescriptionResId(): Int = R.string.phone_input_desc

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding = SpPhoneInputShowcaseBinding.inflate(environmentSP.requireLayoutInflater())
            with(binding) {
                setupPhoneInputTextWithDone(phoneInput, environmentSP.context)
                setupPhoneInputTextWithDone(phoneInputSecond, environmentSP.context)
            }

            binding.labelTextInput.doOnTextChanged { text, start, before, count ->
                binding.phoneInput.labelText = text.toString()
                binding.phoneInputSecond.labelText = text.toString()
            }
            return binding.root
        }

        private fun setupPhoneInputTextWithDone(phoneInput: SPPhoneInput, context: Context) {
            phoneInput.setOnEditorActionListener(TextView.OnEditorActionListener
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