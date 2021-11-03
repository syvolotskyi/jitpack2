package ge.space.design.ui_components.text_fields.otp

import android.content.Context
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpOtpShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.text_fields.password.SPPasswordComponent
import ge.space.ui.components.text_fields.masked.base.OnPinEnteredListener
import ge.space.ui.components.text_fields.masked.base.SPPinState
import ge.space.ui.components.text_fields.masked.pin.SPOtpView

class SPOtpComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.otp_input

    override fun getDescriptionResId(): Int = R.string.otp_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpOtpShowcaseBinding.inflate(environment.requireLayoutInflater())
            with(binding) {
                setupBigOtpView(pinEntryViewOTP, environment.context)
                setupSmallOtpView(pinEntryViewOTPSmall, environment.context)
            }

            binding.labelTextInput.doOnTextChanged { text, start, before, count ->
                binding.pinEntryViewOTP.labelText = text.toString()
                binding.pinEntryViewOTPSmall.labelText = text.toString()
            }

            return binding.root
        }

        private fun setupBigOtpView(otpEntryViewOtp: SPOtpView, context: Context) {
            setupCounter(otpEntryViewOtp, context)
            otpEntryViewOtp.setPinEnteredListener(object : OnPinEnteredListener {
                override fun onPinEntered(pinCode: CharSequence) {
                    // correct password is 888888
                    if (pinCode.toString() == SPPasswordComponent.CORRECT_BIG_PASSWORD) {
                        otpEntryViewOtp.handleResult(
                            context.getString(R.string.correct_password),
                            false
                        )
                    } else {
                        otpEntryViewOtp.handleResult(
                            context.getString(R.string.incorrect_password),
                            true
                        )
                    }
                }

            })
        }

        private fun setupCounter(otpEntryViewOtp: SPOtpView, context: Context) {
            val action = {
                otpEntryViewOtp.startCount(100) {
                    Toast.makeText(
                        context,
                        "SMS was sent",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            action()
            otpEntryViewOtp.descriptionText =
                otpEntryViewOtp.context.getString(R.string.component_otp_resend_sms)
            otpEntryViewOtp.setOnDescriptionClickListener { action() }
        }

        private fun SPOtpView.handleResult(messageText: String, isError: Boolean) {
            this.state = if (isError) SPPinState.ERROR else SPPinState.SUCCESSFUL
            Toast.makeText(context, messageText, Toast.LENGTH_SHORT).show()
        }

        private fun setupSmallOtpView(otpEntryViewOtp: SPOtpView, context: Context) {
            setupCounter(otpEntryViewOtp, context)
            otpEntryViewOtp.setPinEnteredListener(object : OnPinEnteredListener {
                override fun onPinEntered(pinCode: CharSequence) {
                    // correct password is 1010
                    if (pinCode.toString() == SPPasswordComponent.CORRECT_SMALL_PASSWORD) {
                        otpEntryViewOtp.state = SPPinState.SUCCESSFUL
                        Toast.makeText(context, CORRECT_PASSWORD, Toast.LENGTH_SHORT).show()
                    } else {
                        otpEntryViewOtp.state = SPPinState.ERROR
                        Toast.makeText(context, INCORRECT_PASSWORD, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    companion object {
        private const val CORRECT_PASSWORD = "correct password"
        private const val INCORRECT_PASSWORD = "incorrect password"
    }

}