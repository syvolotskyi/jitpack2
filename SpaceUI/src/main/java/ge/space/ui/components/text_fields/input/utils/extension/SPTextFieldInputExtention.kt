package ge.space.ui.components.text_fields.input.utils.extension

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput

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
): TextWatcher {
    val watcher = createTextChangedListener(onTextChanged = action)
    addTextChangedListener( watcher)
    return watcher
}
/**
 * Add an action which will be invoked befoure the text is changing.
 *
 * @return the [TextWatcher] added to the SPTextFieldInput
 */
inline fun SPTextFieldInput.doBeforeTextChanged(
    crossinline action: (
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) -> Unit
): TextWatcher {
    val watcher = createTextChangedListener(beforeTextChanged = action)
    addTextChangedListener( watcher)
    return watcher
}

/**
 * Add a text changed listener to this SPTextFieldInput using the provided actions
 *
 * @return the [TextWatcher] added to the TextView
 */
inline fun createTextChangedListener(
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

    return object : TextWatcher {
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
}

fun EditText.setTextLength(length: Int) {
    this.filters =
        arrayOf<InputFilter>(InputFilter.LengthFilter(length))
}
