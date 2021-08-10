package ge.space.design.ui_components.text_fields.password

import android.content.Context
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpPasswordShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.pin.OnPinEnteredListener
import ge.space.ui.components.text_fields.pin.SPPinEntryView

class SPPasswordComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.password_pin

    override fun getDescriptionResId(): Int = R.string.password_pin_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpPasswordShowCaseBinding.inflate(environment.requireLayoutInflater())
            with(binding) {
                setupBigPasswordView(pinEntryViewPassword, environment.context)
                setupSmallPasswordView(pinEntryViewPasswordSmall, environment.context)
            }

            binding.labelTextInput.doOnTextChanged { text, _, _, _ ->
                binding.pinEntryViewPassword.labelText = text.toString()
                binding.pinEntryViewPasswordSmall.labelText = text.toString()
            }
            return binding.root
        }

        private fun setupBigPasswordView(pinEntryViewPassword: SPPinEntryView, context: Context) {
            pinEntryViewPassword.setPinEnteredListener(object : OnPinEnteredListener {
                override fun onPinEntered(pinCode: CharSequence) {
                    // correct password is 888888
                    if (pinCode.toString() == CORRECT_BIG_PASSWORD) {
                        pinEntryViewPassword.isError = false
                        Toast.makeText(context, CORRECT_PASSWORD, Toast.LENGTH_SHORT).show()
                    } else {
                        pinEntryViewPassword.isError = true
                        Toast.makeText(context, INCORRECT_PASSWORD, Toast.LENGTH_SHORT).show()
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
                        Toast.makeText(context, CORRECT_PASSWORD, Toast.LENGTH_SHORT).show()
                    } else {
                        pinEntryViewPassword.isError = true
                        Toast.makeText(context, INCORRECT_PASSWORD, Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }

    companion object {
        private const val CORRECT_BIG_PASSWORD = "888888"
        private const val CORRECT_SMALL_PASSWORD = "1010"
        private const val CORRECT_PASSWORD = "correct password"
        private const val INCORRECT_PASSWORD = "incorrect password"
    }
}