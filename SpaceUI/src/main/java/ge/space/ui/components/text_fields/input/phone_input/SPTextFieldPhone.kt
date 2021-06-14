package ge.space.ui.components.text_fields.input.phone_input

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldPhoneLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView

/**
 * Field view extended from [SPTextFieldBaseView] that allows
 * to change EditorAction and sets the mask.
 *
 */
class SPTextFieldPhone @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPTextFieldBaseView<SpTextFieldPhoneLayoutBinding>(context, attrs, defStyleAttr) {

    init {
        inputTextBinding.etInputField.inputType = InputType.TYPE_CLASS_PHONE
        inputTextBinding.etInputField.mask = resources.getString(R.string.phone_mask)
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

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        with(inputTextBinding.etInputField) {
            setImeActionEnabled(true)
            onActionListener = listener
        }
    }

    override fun setOnFocusChangeListener(listener: OnFocusChangeListener) {
        inputTextBinding.etInputField.onFocusChangeListener = listener
    }

    override fun getViewBinding(): SpTextFieldPhoneLayoutBinding {
        return SpTextFieldPhoneLayoutBinding.inflate(LayoutInflater.from(context), this, false)
    }

    override fun handleImeOption() {
        inputTextBinding.etInputField.imeOptions = imeOption
    }
}