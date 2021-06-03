package ge.space.design.ui_components.text_fields.password

import android.content.Context
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpPasswordShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.view.text_field.OnPinEnteredListener
import ge.space.ui.view.text_field.SPPinEntryView

class SPPasswordComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.password_pin

    override fun getDescriptionResId(): Int = R.string.password_pin_desc

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding = SpPasswordShowCaseBinding.inflate(environmentSP.requireLayoutInflater())
            with(binding) {
                setupBigPasswordView(pinEntryViewPassword, environmentSP.context)
                setupSmallPasswordView(pinEntryViewPasswordSmall, environmentSP.context)
            }

            binding.labelTextInput.doOnTextChanged { text, start, before, count ->
                binding.pinEntryViewPassword.text = text.toString()
                binding.pinEntryViewPasswordSmall.text = text.toString()
            }
            return binding.root
        }

        private fun setupBigPasswordView(pinEntryViewPassword: SPPinEntryView, context: Context) {
            pinEntryViewPassword.setPinEnteredListener(object : OnPinEnteredListener {
                override fun onPinEntered(pinCode: CharSequence) {
                    // correct password is 888888
                    if (pinCode.toString() == CORRECT_BIG_PASSWORD) {
                        pinEntryViewPassword.isError = false
                        Toast.makeText(context, "correct password", Toast.LENGTH_SHORT).show()
                    } else {
                        pinEntryViewPassword.isError = true
                        Toast.makeText(context, "incorrect password", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }

        private fun setupSmallPasswordView(pinEntryViewPassword: SPPinEntryView, context: Context) {
            pinEntryViewPassword.setPinEnteredListener(object : OnPinEnteredListener {
                override fun onPinEntered(pinCode: CharSequence) {
                    // correct password is 1010
                    if (pinCode.toString() == CORRECT_SMALL_PASSWORD) {
                        pinEntryViewPassword.isError = false
                        Toast.makeText(context, "correct password", Toast.LENGTH_SHORT).show()
                    } else {
                        pinEntryViewPassword.isError = true
                        Toast.makeText(context, "incorrect password", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }

    companion object {
        const val CORRECT_BIG_PASSWORD = "888888"
        const val CORRECT_SMALL_PASSWORD = "1010"
    }

}