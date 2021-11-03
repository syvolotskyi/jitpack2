package ge.space.ui.components.text_fields.masked.pin

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.view.isVisible
import ge.space.extensions.getTimeLabel
import ge.space.extensions.makeVibration
import ge.space.extensions.onTextChanged
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPinEntryViewLayoutBinding
import ge.space.ui.components.text_fields.masked.base.OnPinEnteredListener
import ge.space.ui.components.text_fields.masked.base.SPBasePinEditText
import ge.space.ui.components.text_fields.masked.base.SPPinState
import ge.space.ui.util.extension.getColorFromAttribute
import java.util.concurrent.TimeUnit

/**
 * Field view extended from [SPBasePinEditText] that allows to change its configuration.
 *
 * @property counterTextAppearance [Int] value which sets a counter view.
 */
class SPOtpView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPPinEntryOTPCode
) : SPBasePinEditText<SpPinEntryViewLayoutBinding>(context, attrs, defStyleAttr) {

    /**
     * Sets a  counter text appearance
     */
    @StyleRes
    var counterTextAppearance: Int = R.style.h700_bold_magenta

    private var counter: CountDownTimer? = null

    override fun getViewBinding(): SpPinEntryViewLayoutBinding {
        return SpPinEntryViewLayoutBinding.inflate(LayoutInflater.from(context), this)
    }

    init {

        binding.pinEntryEditText.setStyle(R.style.SPPinEntryEditText)
        binding.pinEntryEditText.onTextChanged {
            binding.pinEntryEditText.setError(false)
        }
    }

    fun startCount(
        seconds: Long,
        onFinishListener: () -> Unit
    ) {
        val diff = ONE_SECOND
        val maxCount = TimeUnit.SECONDS.toMillis(seconds)
        binding.buttonDescription.isEnabled = false
        binding.buttonCounter.isVisible = true

        counter = object : CountDownTimer(maxCount, diff) {
            override fun onTick(millisUntilFinished: Long) {
                binding.buttonDescription.alpha = 0.3f
                binding.buttonCounter.text = millisUntilFinished.getTimeLabel()
            }

            override fun onFinish() {
                onFinishListener()
                binding.buttonDescription.isEnabled = true
                binding.buttonDescription.alpha = 1f
                binding.buttonCounter.isVisible = false
            }
        }.start()
    }

    override fun setOnDescriptionClickListener(listener: () -> Unit) {
        binding.buttonDescription.setOnClickListener { listener() }
    }

    /**
     * Request focus on this PinEntryEditText
     */
    override fun focus() {
        binding.pinEntryEditText.focus()
    }

    /**
     * Clean previously set password
     */
    override fun resetPin() {
        binding.pinEntryEditText.setText("")
        state = SPPinState.DEFAULT
    }

    private fun showErrorAnimation(){
        binding.pinEntryContainer.startAnimation(errorAnimation)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        counter?.cancel()
        binding.pinEntryEditText.isEnabled = enabled
        binding.pinEntryContainer.changeBorder(context.getColorFromAttribute(R.attr.colorSecondary),
            resources.getDimensionPixelSize(R.dimen.dimen_p_1).toFloat())
    }

    fun setPinEnteredListener(onPinEnteredListener: OnPinEnteredListener) {
        binding.pinEntryEditText.onPinEnteredListener = onPinEnteredListener
    }

    override fun updateText(text: String) {
        binding.pinEntryEditText.setText(text)
    }

    override fun updateLabel(text: String) {
        binding.buttonLabel.text = text
    }

    override fun updateDescription(text: String) {
        binding.buttonDescription.text = text
    }

    override fun handleState() {
        binding.pinEntryEditText.setError(state == SPPinState.ERROR)
        when (state) {
            SPPinState.SUCCESSFUL -> {
                counter?.cancel()
                binding.pinEntryContainer.changeBorder(context.getColorFromAttribute(R.attr.brand_primary),
                    resources.getDimensionPixelSize(R.dimen.dimen_p_1).toFloat())
            }
            SPPinState.ERROR -> {
                showErrorAnimation()
                context.makeVibration()
                binding.pinEntryContainer.changeBorder(context.getColorFromAttribute(R.attr.accent_magenta),
                    resources.getDimensionPixelSize(R.dimen.dimen_p_1).toFloat())
            }
            else -> {
                binding.pinEntryContainer.changeBorder(context.getColorFromAttribute(R.attr.brand_primary),
                    resources.getDimensionPixelSize(R.dimen.dimen_p_1).toFloat())
            }
        }
    }

    override fun setMaxLength() =
        binding.pinEntryEditText.setMaxLength(maxLength)

    override fun updateTextAppearance() {
        binding.buttonLabel.setTextStyle(textAppearance)
        binding.buttonDescription.setTextStyle(descriptionTextAppearance)
        binding.buttonCounter.setTextStyle(counterTextAppearance)
    }

    companion object {
        private const val ONE_SECOND: Long = 1000
    }
}

