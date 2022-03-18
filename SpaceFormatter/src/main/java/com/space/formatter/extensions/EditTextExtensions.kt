package com.space.formatter.extensions

import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.widget.EditText
import com.space.formatter.format.StringFormatter

fun EditText.addFormattingTextWatcher(formatter: StringFormatter, afterTextChanged: ((String) -> Unit?)? = null): TextWatcher {

    val formattingTextWatcher = object: TextWatcher {

        private var prevText: String? = null
        private var currentAction: EditTextAction? =
            EditTextAction.INSERT

        override fun afterTextChanged(s: Editable?) {
            removeTextChangedListener(this)
            if (s.isNullOrEmpty()) {
                addTextChangedListener(this)
                return
            }

            val newText = formatter.format(s.toString())

            if (s.toString() == newText) {
                addTextChangedListener(this)
                afterTextChanged?.let { it(newText) }
                return
            }

            var selection = Selection.getSelectionStart(s)
            val lengthDifference = newText.length - s.length

            if (newText.length < s.length && selection > 0)
                selection += lengthDifference
            else if (newText.length > s.length && currentAction != EditTextAction.DELETE && selection < newText.length)
                selection += lengthDifference
            else if (currentAction == EditTextAction.DELETE && prevText != newText && prevText?.length == newText.length)
                selection++

            setText(newText)
            setSelection(selection)

            currentAction = null

            addTextChangedListener(this)
            afterTextChanged?.let { it(newText) }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            prevText = s.toString()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            currentAction = when (count - before) {
                in 0..Integer.MAX_VALUE -> EditTextAction.INSERT
                else -> EditTextAction.DELETE
            }
        }
    }
    addTextChangedListener(formattingTextWatcher)
    return formattingTextWatcher
}

enum class EditTextAction {
    INSERT,
    DELETE
}