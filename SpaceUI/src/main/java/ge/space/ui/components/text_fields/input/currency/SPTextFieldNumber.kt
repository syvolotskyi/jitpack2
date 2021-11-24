package ge.space.ui.components.text_fields.input.currency

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldTextCurrencyBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput

class SPTextFieldNumber @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextField_Number
) : SPTextFieldInput(context, attrs, defStyleAttr) {
/*
    var textLength: Int = DEFAULT_TEXT_LENGTH
        set(value) {
            field = value
            handleTextLength()
        }

    override var text: String = EMPTY_TEXT
        get() = inputTextBinding.etInputField.text.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.setText(value)
        }

    override var hint: String = EMPTY_TEXT
        get() = inputTextBinding.etInputField.hint.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.hint = value
        }

    var currency: String = EMPTY_TEXT
        set(value) {
            field = value
            inputTextBinding.tvCurrency.text = value
        }

    @StyleRes
    private var distractiveTextAppearance: Int = DEFAULT_INT

    @StyleRes
    private var currencyTextAppearance: Int = DEFAULT_INT

    override var isDistractive: Boolean = false
        set(value) {
            field = value
            handleDistractive()
        }

    var isActive: Boolean = true
        set(value) {
            field = value
            handleActive()
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextFieldBaseView,
            defStyleAttr
        ) {
            setViewStyle(
                getResourceId(
                    R.styleable.SPTextFieldBaseView_style,
                    defStyleRes
                )
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextFieldNumber,
            defStyleAttr
        ) {
            currency = getString(R.styleable.SPTextFieldNumber_currency).orEmpty()
            distractiveTextAppearance = getResourceId(
                R.styleable.SPTextFieldNumber_distractiveTextAppearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
            currencyTextAppearance = getResourceId(
                R.styleable.SPTextFieldNumber_currencyTextAppearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
        }
    }

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) =
        inputTextBinding.etInputField.setOnEditorActionListener(listener)

    override fun setOnFocusChangeListener(listener: OnFocusChangeListener) {
        inputTextBinding.etInputField.onFocusChangeListener = listener
    }

    override fun addTextChangedListener(watcher: TextWatcher) =
        inputTextBinding.etInputField.addTextChangedListener(watcher)

    override fun removeTextChangedListener(watcher: TextWatcher) =
        inputTextBinding.etInputField.addTextChangedListener(watcher)

    override fun getChildViewBinding(): SpTextFieldTextCurrencyBinding {
        return SpTextFieldTextCurrencyBinding.inflate(
            LayoutInflater.from(context),
            this,
            false
        )
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        inputTextBinding.etInputField.isEnabled = enabled
        inputTextBinding.tvCurrency.isEnabled = enabled
    }

    override fun handleImeOption() {
        inputTextBinding.etInputField.imeOptions = imeOption
    }

    override fun setTextFieldStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPTextFieldNumber)

        styleAttrs.run {
            currency = getString(R.styleable.SPTextFieldNumber_currency).orEmpty()
            distractiveTextAppearance = getResourceId(
                R.styleable.SPTextFieldNumber_distractiveTextAppearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
            currencyTextAppearance = getResourceId(
                R.styleable.SPTextFieldNumber_currencyTextAppearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
            recycle()
        }
    }

    fun focus() {
        isActive = true
        inputTextBinding.etInputField.requestFocus()
    }

    private fun handleTextLength() {
        if (textLength != DEFAULT_TEXT_LENGTH) {
            inputTextBinding.etInputField.setTextLength(textLength)
        }
    }

    private fun handleDistractive() =
        updateTextAppearance(if (isDistractive) distractiveTextAppearance else textAppearance)

    private fun handleActive() {
        alpha = if (isActive) ALPHA_ACTIVE else ALPHA_INACTIVE
    }

    override fun updateTextAppearance(textAppearance: Int) =
        inputTextBinding.etInputField.setTextStyle(textAppearance)

    companion object {
        private const val ALPHA_ACTIVE: Float = 1.0f
        private const val ALPHA_INACTIVE: Float = 0.5f
    }*/
}