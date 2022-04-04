package ge.space.ui.components.text_fields.masked.password

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPasswordEntryViewLayoutBinding
import ge.space.ui.components.text_fields.masked.base.OnPinEnteredListener
import ge.space.ui.components.text_fields.masked.base.SPPinBaseEditText
import ge.space.ui.components.text_fields.masked.base.SPPinState
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.makeVibration
import ge.space.ui.util.extension.setTextStyle

/**
 * Field view extended from [SPPinBaseEditText] that allows to change its configuration.
 */
class SPPasswordView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPPasswordView
) : SPPinBaseEditText<SpPasswordEntryViewLayoutBinding>(context, attrs, defStyleAttr) {

    override fun getViewBinding(): SpPasswordEntryViewLayoutBinding =
        SpPasswordEntryViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

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

        binding.pinEntryEditText.setStyle(R.style.SPPasswordView)
        binding.pinEntryEditText.setOnClickListener{ binding.pinEntryEditText.focus()}
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
        binding.pinEntryEditText.setText(EMPTY_TEXT)
        binding.pinEntryEditText.isError = false
    }

    private fun showErrorAnimation() {
        binding.pinEntryEditText.startAnimation(errorAnimation)
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

    override fun handleState() {
        binding.pinEntryEditText.isError = state == SPPinState.ERROR
        if (state == SPPinState.ERROR) {
            showErrorAnimation()
            context.makeVibration()
        }
    }

    override fun setMaxLength() {
        binding.pinEntryEditText.setMaxLength(maxLength)
    }

    override fun updateTextAppearances(@StyleRes labelAppearance:Int,
                                       @StyleRes descAppearance:Int) {
        binding.buttonLabel.setTextStyle(labelAppearance)
        binding.labelDescription.setTextStyle(descAppearance)
    }
}