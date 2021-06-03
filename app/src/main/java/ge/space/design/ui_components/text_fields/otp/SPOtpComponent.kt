package ge.space.design.ui_components.text_fields.otp

import android.content.Context
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpOtpShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.text_fields.password.SPPasswordComponent
import ge.space.ui.view.text_field.OnPinEnteredListener
import ge.space.ui.view.text_field.SPPinEntryView

class SPOtpComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.otp_input

    override fun getDescriptionResId(): Int = R.string.otp_description

    override fun getComponentClass(): Class<*> = FactorySP::class.java
    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding = SpOtpShowcaseBinding.inflate(environmentSP.requireLayoutInflater())
            with(binding) {
                setupBigOtpView(pinEntryViewOTP, environmentSP.context)
                setupSmallOtpView(pinEntryViewOTPSmall, environmentSP.context)
            }

            binding.labelTextInput.doOnTextChanged { text, start, before, count ->
                binding.pinEntryViewOTP.labelText = text.toString()
                binding.pinEntryViewOTPSmall.labelText = text.toString()
                binding.pinEntryViewOTPDisabled.labelText = text.toString()
            }
            return binding.root
        }

        private fun setupBigOtpView(pinEntryViewOtp: SPPinEntryView, context: Context) {
            pinEntryViewOtp.setPinEnteredListener(object : OnPinEnteredListener {
                override fun onPinEntered(pinCode: CharSequence) {
                    // correct password is 888888
                    if (pinCode.toString() == SPPasswordComponent.CORRECT_BIG_PASSWORD) {
                        pinEntryViewOtp.handleResult(
                            context.getString(R.string.correct_password),
                            false
                        )
                    } else {
                        pinEntryViewOtp.handleResult(
                            context.getString(R.string.incorrect_password),
                            true
                        )
                    }
                }

            })
        }

        private fun SPPinEntryView.handleResult(messageText: String, isError: Boolean) {
            this.isError = isError
            Toast.makeText(context, messageText, Toast.LENGTH_SHORT).show()
        }

        private fun setupSmallOtpView(pinEntryViewOtp: SPPinEntryView, context: Context) {
            pinEntryViewOtp.setPinEnteredListener(object : OnPinEnteredListener {
                override fun onPinEntered(pinCode: CharSequence) {
                    // correct password is 1010
                    if (pinCode.toString() == SPPasswordComponent.CORRECT_SMALL_PASSWORD) {
                        pinEntryViewOtp.isError = false
                        Toast.makeText(context, "correct password", Toast.LENGTH_SHORT).show()
                    } else {
                        pinEntryViewOtp.isError = true
                        Toast.makeText(context, "incorrect password", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

}