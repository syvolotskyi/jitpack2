package ge.space.ui.components.text_fields.pin

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import androidx.core.widget.addTextChangedListener
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.makeVibration
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPinEntryViewLayoutBinding
import ge.space.ui.components.text_fields.password.SPMaskedEditText.Companion.DEFAULT_LENGTH

/**
 * Field view extended from [LinearLayout] that allows to change its configuration.
 *
 * @property text [String] value which sets a label text.
 * @property isError [Boolean] value which applies a error to a field.
 * @property maxLength [Int] value which applies a max Length.
 */
class SPOtpEntryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: SpPinEntryViewLayoutBinding by lazy {
        SpPinEntryViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

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
     * Sets a labelText
     */
    var descriptionText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.buttonDescription.text = value
        }

    /**
     * Sets a error
     */
    var isError: Boolean = false
        set(hasError) {
            binding.pinEntryEditText.setError(hasError)
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
            R.styleable.SPPinEntryView,
            defStyleAttr
        ) {
            text = getString(R.styleable.SPPinEntryView_android_text).orEmpty()
            labelText = getString(R.styleable.SPPinEntryView_pinLabelText).orEmpty()
            descriptionText = getString(R.styleable.SPPinEntryView_pinDescriptionText).orEmpty()
            isEnabled = getBoolean(R.styleable.SPPinEntryView_android_enabled, true)
            maxLength = getInt(R.styleable.SPPinEntryView_android_maxLength, DEFAULT_LENGTH)

            binding.pinEntryEditText.setStyle(
                getResourceId(
                    R.styleable.SPPinEntryView_sp_pinStyle,
                    R.style.SPPinEntryEditText
                )
            )

            binding.pinEntryEditText.addTextChangedListener {
                binding.pinEntryEditText.setError(false)
            }
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
}

