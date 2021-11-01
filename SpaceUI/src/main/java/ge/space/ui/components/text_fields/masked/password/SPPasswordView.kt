package ge.space.ui.components.text_fields.masked.password

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.makeVibration
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPasswordEntryViewLayoutBinding
import ge.space.spaceui.databinding.SpPinEntryViewLayoutBinding
import ge.space.ui.components.text_fields.masked.password.SPPasswordEditText.Companion.DEFAULT_LENGTH
import ge.space.ui.components.text_fields.masked.base.OnPinEnteredListener
import ge.space.ui.components.text_fields.masked.base.SPPinEditText

class SPPasswordView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPPinEditText<SpPasswordEntryViewLayoutBinding>(context, attrs, defStyleAttr) {

    override fun getViewBinding(): SpPasswordEntryViewLayoutBinding =
        SpPasswordEntryViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

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

    override fun setOnDescriptionClickListener(listener: () -> Unit) {
        binding.labelDescription.setOnClickListener { listener() }
    }

    override fun updateText(text: String) {
        binding.pinEntryEditText.setText(text)
    }

    override fun updateLabel(text: String) {
        binding.buttonLabel.text = text
    }

    override fun updateDescription(text: String) {
        binding.labelDescription.text = text
    }

    override fun handleError() {
        binding.pinEntryEditText.isError = isError
        if (isError) {
            showErrorAnimation()
            context.makeVibration()
        }
    }

    override fun handleMaxLength() {
        binding.pinEntryEditText.setMaxLength(maxLength)
    }
}