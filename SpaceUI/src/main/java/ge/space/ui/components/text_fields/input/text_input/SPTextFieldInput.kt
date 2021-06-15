package ge.space.ui.components.text_fields.input.text_input

import android.content.Context
import android.os.Build
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.RequiresApi
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldTextLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView

class SPTextFieldInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPTextFieldBaseView<SpTextFieldTextLayoutBinding>(context, attrs, defStyleAttr) {

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

    private var canRemove: Boolean = false
        set(value) {
            field = value
            inputTextBinding.ivClear.isVisible = value
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_text_field_input,
            defStyleAttr
        ) {
            canRemove = getBoolean(R.styleable.sp_text_field_input_canRemove, false)

        }

        inputTextBinding.ivClear.setOnClickListener { inputTextBinding.etInputField.setText("") }
    }

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        inputTextBinding.etInputField.setOnEditorActionListener(listener)
    }

    override fun setOnFocusChangeListener(listener: OnFocusChangeListener) {
        inputTextBinding.etInputField.onFocusChangeListener = listener
    }

    fun addTextChangedListener(watcher: TextWatcher){
        inputTextBinding.etInputField.addTextChangedListener(watcher)
    }

    fun removeTextChangedListener(watcher: TextWatcher){
        inputTextBinding.etInputField.addTextChangedListener(watcher)
    }

    override fun getChildViewBinding(): SpTextFieldTextLayoutBinding {
        return SpTextFieldTextLayoutBinding.inflate(
            LayoutInflater.from(context),
            this,
            false
        )
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        inputTextBinding.etInputField.isEnabled = enabled
        inputTextBinding.ivClear.isEnabled = enabled
    }

    override fun handleImeOption() {
        inputTextBinding.etInputField.imeOptions = imeOption
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun updateTextAppearance(textAppearance: Int) {
        TextViewCompat.setTextAppearance(inputTextBinding.etInputField, textAppearance)
        inputTextBinding.etInputField.setTextAppearance( textAppearance)
    }
}