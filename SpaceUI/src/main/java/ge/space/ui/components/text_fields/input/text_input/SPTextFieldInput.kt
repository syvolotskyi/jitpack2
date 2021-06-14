package ge.space.ui.components.text_fields.input.text_input

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldTextLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.components.text_fields.pin.SPPinEntryEditText

@SuppressLint("Recycle")
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

    var canRemove: Boolean = false
        set(value) {
            field = value
            inputTextBinding.ivClear.isVisible = value
        }

    init {
        val ta =
            context.obtainStyledAttributes(attrs, R.styleable.SPTextFieldInput, 0, 0)

        ta.run {
            canRemove = getBoolean(
                R.styleable.SPTextFieldInput_sp_canRemove, false
            )

        }

        inputTextBinding.ivClear.setOnClickListener { inputTextBinding.etInputField.setText("") }
    }

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        inputTextBinding.etInputField.setOnEditorActionListener(listener)
    }

    override fun setOnFocusChangeListener(listener: OnFocusChangeListener) {
        inputTextBinding.etInputField.onFocusChangeListener = listener
    }

    override fun getViewBinding(): SpTextFieldTextLayoutBinding {
        return SpTextFieldTextLayoutBinding.inflate(LayoutInflater.from(context), this, false)
    }

}