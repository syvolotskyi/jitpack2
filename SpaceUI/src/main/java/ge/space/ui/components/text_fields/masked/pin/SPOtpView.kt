package ge.space.ui.components.text_fields.masked.pin

import android.content.Context
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.view.isVisible
import ge.space.extensions.getTimeLabel
import ge.space.extensions.makeVibration
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPinEntryViewLayoutBinding
import ge.space.ui.components.text_fields.masked.base.OnPinEnteredListener
import ge.space.ui.components.text_fields.masked.base.SPPinEditText
import java.util.concurrent.TimeUnit

/**
 * Field view extended from [LinearLayout] that allows to change its configuration.
 *
 * @property text [String] value which sets a label text.
 * @property isError [Boolean] value which applies a error to a field.
 * @property maxLength [Int] value which applies a max Length.
 */
class SPOtpView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPPinEditText<SpPinEntryViewLayoutBinding>(context, attrs, defStyleAttr) {


    override fun getViewBinding(): SpPinEntryViewLayoutBinding {
        return SpPinEntryViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        binding.pinEntryEditText.setStyle(R.style.SPPinEntryEditText)
        binding.pinEntryEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                binding.pinEntryEditText.setError(false)
            }
        })
    }

    fun startCount(
        second: Long,
        onFinishListener: () -> Unit
    ) {
        val diff = 1000.toLong()
        val maxCount = TimeUnit.SECONDS.toMillis(second)
        binding.buttonDescription.isEnabled = false
        binding.buttonCounter.isVisible = true
        object : CountDownTimer(maxCount, diff) {
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

    fun setOnDescriptionClickListener(listener: () -> Unit) {
        binding.buttonDescription.setOnClickListener { listener() }
    }

    /**
     * Request focus on this PinEntryEditText
     */
    fun focus() {
        binding.pinEntryEditText.focus()
    }

    /**
     * Clean previously set password
     */
    fun resetPin() {
        binding.pinEntryEditText.setText("")
        binding.pinEntryEditText.setError(false)
    }

    private fun showErrorAnimation(){
        val animation = AnimationUtils.loadAnimation(
            binding.pinEntryContainer.context,
            R.anim.sp_shake_anim
        )

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                resetPin()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        binding.pinEntryContainer.startAnimation(animation)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        binding.pinEntryEditText.isEnabled = enabled
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

    override fun handleError() {
        binding.pinEntryEditText.setError(isError)
        if (isError) {
            showErrorAnimation()
            context.makeVibration()
        }
    }

    override fun handleMaxLength() {
        binding.pinEntryEditText.setMaxLength(maxLength)
    }
}

