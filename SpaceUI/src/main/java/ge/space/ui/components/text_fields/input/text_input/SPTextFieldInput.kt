package ge.space.ui.components.text_fields.input.text_input

import android.content.Context
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import ge.space.extensions.isVisible
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldTextLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.components.text_fields.input.utils.extension.setTextLength
import ge.space.ui.util.extension.EMPTY_STRING

class SPTextFieldInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPTextFieldBaseView<SpTextFieldTextLayoutBinding>(context, attrs, defStyleAttr) {

    var textLength: Int = DEFAULT_TEXT_LENGTH
        set(value) {
            field = value

            handleTextLength()
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

    private var canRemove: Boolean = false
        set(value) {
            field = value
            inputTextBinding.ivClear.isVisible = value
        }

    @IdRes
    var drawableStart: Int = DEFAULT_INT
        set(value) {
            field = value
            handleDrawableStart()
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_text_field_input,
            defStyleAttr
        ) {
            canRemove = getBoolean(R.styleable.sp_text_field_input_canRemove, false)
            text = getString(R.styleable.sp_text_field_input_android_text) ?: EMPTY_STRING
            drawableStart = getResourceId(R.styleable.sp_text_field_input_drawableLeft, DEFAULT_INT)
            textLength =
                getInt(R.styleable.sp_text_field_input_inputTextLength, DEFAULT_TEXT_LENGTH)

        }

        inputTextBinding.ivClear.setOnClickListener { inputTextBinding.etInputField.setText("") }
    }

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        inputTextBinding.etInputField.setOnEditorActionListener(listener)
    }

    override fun setOnFocusChangeListener(listener: OnFocusChangeListener) {
        inputTextBinding.etInputField.onFocusChangeListener = listener
    }

    override fun addTextChangedListener(watcher: TextWatcher) {
        inputTextBinding.etInputField.addTextChangedListener(watcher)
    }

    override fun removeTextChangedListener(watcher: TextWatcher) {
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

    /**
     * Sets a style for the SPButton view.
     *
     * <p>
     * Default style theme is SPBaseView.SPBaseButton style.
     * <p>
     *
     * @param defStyleRes [Int] style resource id
     */
    override fun setTextFieldStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.sp_text_field_input)

        styleAttrs.run {
            canRemove = getBoolean(R.styleable.sp_text_field_input_canRemove, false)
            drawableStart = getResourceId(R.styleable.sp_text_field_input_drawableLeft, DEFAULT_INT)
            textLength =
                getInt(R.styleable.sp_text_field_input_inputTextLength, DEFAULT_TEXT_LENGTH)

            recycle()
        }
    }

    fun focus() {
        inputTextBinding.etInputField.requestFocus()
    }

    private fun handleDrawableStart() {
        inputTextBinding.ivLeftImage.isVisible = drawableStart != SPBaseView.DEFAULT_OBTAIN_VAL
        inputTextBinding.ivLeftImage.setImageResource(drawableStart)
    }

    private fun handleTextLength() {
        if (textLength != DEFAULT_TEXT_LENGTH) {
            inputTextBinding.etInputField.setTextLength(textLength)
        }
    }

    override fun updateTextAppearance(textAppearance: Int) =
        inputTextBinding.etInputField.setTextStyle(textAppearance)


}