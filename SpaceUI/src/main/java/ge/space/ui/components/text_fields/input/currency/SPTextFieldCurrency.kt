package ge.space.ui.components.text_fields.input.currency

import android.content.Context
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldTextCurrencyBinding
import ge.space.spaceui.databinding.SpTextFieldTextLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView

class SPTextFieldCurrency @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPTextFieldBaseView<SpTextFieldTextCurrencyBinding>(context, attrs, defStyleAttr) {

    var textLength: Int = -1
        set(value) {
            field = value
            if (value == -1) return
            inputTextBinding.etInputField.filters =
                arrayOf<InputFilter>(InputFilter.LengthFilter(value))
        }


    override var text: String = SPBaseView.EMPTY_TEXT
        get() = inputTextBinding.etInputField.text.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.setText(value)
        }

    override var hint: String = SPBaseView.EMPTY_TEXT
        get() = inputTextBinding.etInputField.hint.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.hint = value
        }

    var currency: String = ""
        set(value) {
            field = value
            inputTextBinding.tvCurrency.text = value
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_text_field_input,
            defStyleAttr
        ) {
            currency = getString(R.styleable.sp_text_field_currency_currency) ?: ""
        }

    }

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) =
        inputTextBinding.etInputField.setOnEditorActionListener(listener)


    override fun setOnFocusChangeListener(listener: OnFocusChangeListener) {
        inputTextBinding.etInputField.onFocusChangeListener = listener
    }

    fun addTextChangedListener(watcher: TextWatcher) =
        inputTextBinding.etInputField.addTextChangedListener(watcher)

    fun removeTextChangedListener(watcher: TextWatcher) =
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
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.sp_text_field_input)

        styleAttrs.run {
            currency = getString(R.styleable.sp_text_field_currency_currency) ?: ""
            textLength =
                getInt(R.styleable.sp_text_field_input_inputTextLength, -1)

            recycle()
        }
    }

    fun focus() =
        inputTextBinding.etInputField.requestFocus()

    override fun updateTextAppearance(textAppearance: Int) =
        TextViewCompat.setTextAppearance(inputTextBinding.etInputField, textAppearance)

}