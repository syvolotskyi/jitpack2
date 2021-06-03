package ge.space.ui.view.text_field

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import androidx.core.widget.addTextChangedListener
import ge.space.spaceui.R
import ge.space.spaceui.databinding.LayoutPinEntryViewBinding

/**
 * Field view extended from [LinearLayout] that allows to change its configuration.
 *
 * @property text [String] value which sets a label text.
 * @property isError [Boolean] value which applies a error to a field.
 * @property maxLength [Int] value which applies a max Length.
 */
class SPPinEntryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutPinEntryViewBinding by lazy {
        LayoutPinEntryViewBinding.inflate(LayoutInflater.from(context), this, true)
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
     * Sets a error
     */
    var isError: Boolean
        get() = binding.pinEntryEditText.isError
        set(hasError) {
            binding.pinEntryEditText.isError = hasError
            if (hasError) {
                showErrorAnimation()
                makeVibration()
            }
        }

    /**
     * Sets a maxLength
     */
    var maxLength: Int = DEFAULT_MAX_LENGTH
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
            labelText = getString(R.styleable.SPPinEntryView_sp_labelText).orEmpty()
            isEnabled = getBoolean(R.styleable.SPPinEntryView_android_enabled, true)
            maxLength = getInt(R.styleable.SPPinEntryView_android_maxLength, DEFAULT_MAX_LENGTH)

            binding.pinEntryEditText.setStyle(
                getResourceId(
                    R.styleable.SPPinEntryView_sp_pinStyle,
                    R.style.SPPinEntryEditText
                )
            )

            binding.pinEntryEditText.addTextChangedListener {
                binding.pinEntryEditText.isError = false
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
    fun clean() {
        binding.pinEntryEditText.setText("")
    }

    private fun showErrorAnimation(){
        val animation = AnimationUtils.loadAnimation(
            binding.pinEntryEditText.context,
            R.anim.shake
        )

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                clean()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        binding.pinEntryEditText.startAnimation(animation)
    }

    private fun makeVibration() {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(400, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(400)
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        binding.pinEntryEditText.isEnabled = enabled
    }

    fun setPinEnteredListener(onPinEnteredListener: OnPinEnteredListener) {
        binding.pinEntryEditText.onPinEnteredListener = onPinEnteredListener
    }

    companion object {
        private const val EMPTY_TEXT = ""
        private const val DEFAULT_MAX_LENGTH = 4
    }
}

