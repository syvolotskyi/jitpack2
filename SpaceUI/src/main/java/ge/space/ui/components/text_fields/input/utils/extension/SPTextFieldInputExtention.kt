package ge.space.ui.components.text_fields.input.utils.extension

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.components.text_fields.input.text_input.SPTextFieldInput

/**
 * Add an action which will be invoked before the text changed.
 *
 * @return the [TextWatcher] added to the SPTextFieldInput
 */
inline fun SPTextFieldInput.doBeforeTextChanged(
    crossinline action: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) -> Unit
): TextWatcher = addTextChangedListener(beforeTextChanged = action)

fun EditText.setTextLength(length: Int) {
    this.filters =
        arrayOf<InputFilter>(InputFilter.LengthFilter(SPTextFieldBaseView.DEFAULT_TEXT_LENGTH))
}

/**
 * Add an action which will be invoked when the text is changing.
 *
 * @return the [TextWatcher] added to the SPTextFieldInput
 */
inline fun SPTextFieldInput.doOnTextChanged(
    crossinline action: (
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) -> Unit
): TextWatcher = addTextChangedListener(onTextChanged = action)

/**
 * Add an action which will be invoked after the text changed.
 *
 * @return the [TextWatcher] added to the SPTextFieldInput
 */
inline fun SPTextFieldInput.doAfterTextChanged(
    crossinline action: (text: Editable?) -> Unit
): TextWatcher = addTextChangedListener(afterTextChanged = action)

/**
 * Add a text changed listener to this SPTextFieldInput using the provided actions
 *
 * @return the [TextWatcher] added to the TextView
 */
inline fun SPTextFieldInput.addTextChangedListener(
    crossinline beforeTextChanged: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) -> Unit = { _, _, _, _ -> },
    crossinline onTextChanged: (
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) -> Unit = { _, _, _, _ -> },
    crossinline afterTextChanged: (text: Editable?) -> Unit = {}
): TextWatcher {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s)
        }

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged.invoke(text, start, count, after)
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(text, start, before, count)
        }
    }
    this.addTextChangedListener(textWatcher)

    return textWatcher
}
