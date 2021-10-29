package ge.space.ui.components.text_fields.password

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPasswordEntryViewLayoutBinding
import ge.space.ui.components.text_fields.password.SPMaskedEditText.Companion.DEFAULT_LENGTH
import ge.space.ui.components.text_fields.pin.OnPinEnteredListener
import ge.space.ui.util.extension.makeVibration

class SPPasswordView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: SpPasswordEntryViewLayoutBinding =
        SpPasswordEntryViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)


    /**
     * Sets a text
     */
    var text: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.pinEntryEditText.setText(value)
        }

    /**
     * Sets a labelText
     */
    var labelText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.buttonLabel.text = value
        }

    /**
     * Sets a error
     */
    var isError: Boolean
        get() = binding.pinEntryEditText.isError
        set(hasError) {
            binding.pinEntryEditText.isError = hasError
            if (hasError) {
                showErrorAnimation()
                context.makeVibration()
            }
        }

    /**
     * Sets a maxLength
     */
    var maxLength: Int = DEFAULT_LENGTH
        set(value) {
            field = value

            binding.pinEntryEditText.setMaxLength(maxLength)
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPPasswordView,
            defStyleAttr
        ) {
            text = getString(R.styleable.SPPasswordView_android_text).orEmpty()
            labelText = getString(R.styleable.SPPasswordView_pinLabelText).orEmpty()
            maxLength = getInt(R.styleable.SPPasswordView_android_maxLength, DEFAULT_LENGTH)

            binding.pinEntryEditText.setStyle(R.style.SPPasswordView)
            binding.pinEntryEditText.setOnClickListener{ binding.pinEntryEditText.focus()}
        }
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
        binding.pinEntryEditText.setText(EMPTY_TEXT)
        binding.pinEntryEditText.isError = false
    }

    private fun showErrorAnimation() {
        val animation = AnimationUtils.loadAnimation(
            binding.pinEntryEditText.context,
            R.anim.sp_shake_anim
        )

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                resetPin()
            }
        })

        binding.pinEntryEditText.startAnimation(animation)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        binding.pinEntryEditText.isEnabled = enabled
    }

    fun setPinEnteredListener(onPinEnteredListener: OnPinEnteredListener) {
        binding.pinEntryEditText.onPinEnteredListener = onPinEnteredListener
    }
}