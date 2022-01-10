package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextAreaLayoutBinding
import ge.space.spaceui.databinding.SpTextFieldLayoutBinding

class SPTextAreaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextField_Base
) : SPTextFieldInput(context, attrs, defStyleAttr,defStyleRes ) {

    /**
     * Inflates and returns [SpTextFieldLayoutBinding] value
     */
    private  val contentBinding by lazy {
        SpTextAreaLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        handleContentInputView()
    }

    private fun handleContentInputView() {
        binding.flInputFieldContainer.addContentView(contentBinding.root)
        contentBinding..setOnFocusChangeListener { _, focused ->
            onFocusChangeListener(focused)
        }
    }

    /**
     * Sets a button title.
     */
    override var text: String = EMPTY_TEXT
        get() = contentInputView.text.toString()
        set(value) {
            field = value

            contentInputView.setText(value)
        }

    /**
     * Sets a button hint.
     */
    override var hint: String = EMPTY_TEXT
        set(value) {
            field = value

            contentInputView.hint = value
        }
}