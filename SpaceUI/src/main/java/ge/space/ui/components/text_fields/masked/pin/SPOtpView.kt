package ge.space.ui.components.text_fields.masked.pin

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPinEntryViewLayoutBinding
import ge.space.ui.components.text_fields.masked.base.OnPinEnteredListener
import ge.space.ui.components.text_fields.masked.base.SPPinBaseEditText
import ge.space.ui.components.text_fields.masked.base.SPPinState
import ge.space.ui.util.extension.*
import java.util.concurrent.TimeUnit

/**
 * Field view extended from [SPPinBaseEditText] that allows to change its configuration.
 *
 * @property counterTextAppearance [Int] value which sets a counter view.
 */
class SPOtpView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPPinEntryOTPCode
) : SPPinBaseEditText<SpPinEntryViewLayoutBinding>(context, attrs, defStyleAttr) {

    private var counter: CountDownTimer? = null

    override fun getViewBinding(): SpPinEntryViewLayoutBinding {
        return SpPinEntryViewLayoutBinding.inflate(LayoutInflater.from(context), this)
    }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPBaseView,
            defStyleAttr
        ) {
            setViewStyle(
                getResourceId(
                    R.styleable.SPBaseView_style,
                    defStyleRes
                )
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPPinEditText,
            defStyleAttr
        ) {
            withStyledResource()
        }

        binding.pinEntryEditText.setStyle(R.style.SPPinEntryEditText)
        binding.pinEntryEditText.onTextChanged {
            binding.pinEntryEditText.setError(false)
        }
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
        handleEnabledStatus(enabled)
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
                counter?.onFinish()
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
                binding.pinEntryContainer.changeBorder(
                    context.getColorFromAttribute(R.attr.brand_primary),
                    resources.getDimensionPixelSize(R.dimen.dimen_p_1).toFloat()
                )
            }
        }
    }

    override fun setMaxLength() =
        binding.pinEntryEditText.setMaxLength(maxLength)

    override fun updateTextAppearances(
        @StyleRes labelAppearance: Int,
        @StyleRes descAppearance: Int
    ) {
        binding.buttonLabel.setTextStyle(labelAppearance)
        binding.buttonDescription.setTextStyle(descAppearance)
        binding.buttonCounter.setTextStyle(counterTextAppearance)
    }

    /**
     * Start count while user can resend a Sms
     *
     * @property seconds [Long] count of seconds.
     * @property onFinishListener [() -> Unit]listener of the end of counting.
     *
     */
    fun startCount(
        seconds: Long,
        onFinishListener: () -> Unit
    ) {
        with(binding) {
            buttonDescription.isEnabled = false
            buttonCounter.isVisible = true
            buttonDescription.setTextColor(context.getColorFromAttribute(R.attr.brand_secondary))
        }
        counter =
            getCounter(TimeUnit.SECONDS.toMillis(seconds), onFinishListener).start()
    }

    /**
     * Set a listener for description
     *
     */
    override fun setOnDescriptionClickListener(listener: () -> Unit) {
        binding.buttonDescription.onClick { listener() }
    }

    /**
     * Set interface which allow to listen view and are called when the text is entered
     *
     */
    fun setPinEnteredListener(onPinEnteredListener: OnPinEnteredListener) {
        binding.pinEntryEditText.onPinEnteredListener = onPinEnteredListener
    }

    private fun handleEnabledStatus(enabled: Boolean) {
        binding.pinEntryEditText.isEnabled = enabled

        if (enabled) {
            updateTextAppearances(labelTextAppearance, descriptionTextAppearance)
        } else {
            binding.pinEntryContainer.changeBorder(
                context.getColorFromAttribute(R.attr.colorSecondary),
                resources.getDimensionPixelSize(R.dimen.dimen_p_1).toFloat()
            )
            binding.buttonDescription.setTextColor(context.getColorFromAttribute(R.attr.label_tertiary))
        }
    }

    private fun getCounter(
        maxCount: Long,
        onFinishListener: () -> Unit
    ): CountDownTimer =
        object : CountDownTimer(maxCount, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                binding.buttonCounter.text = millisUntilFinished.getTimeLabel()
            }

            override fun onFinish() {
                onFinishListener()
                counter?.cancel()
                setPossibilityToSendSms()
            }
        }

    private fun setPossibilityToSendSms() {
        binding.buttonDescription.isEnabled = true
        binding.buttonDescription.setTextColor(context.getColorFromAttribute(R.attr.brand_primary))
        binding.buttonCounter.isVisible = false
    }

    companion object {
        private const val ONE_SECOND: Long = 1000
    }
}

