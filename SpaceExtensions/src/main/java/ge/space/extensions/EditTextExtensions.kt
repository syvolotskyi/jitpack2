package ge.space.extensions

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import kotlinx.coroutines.*

fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.onTextChanged(cb: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            cb(s.toString())
        }
    })
}

fun EditText.afterTextChangedDebounce(delayMillis: Long, input: (String) -> Unit) {
    var lastInput = ""
    var debounceJob: Job? = null
    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            if (editable != null) {
                val newtInput = editable.toString()
                debounceJob?.cancel()
                if (lastInput != newtInput) {
                    lastInput = newtInput
                    debounceJob = uiScope.launch {
                        delay(delayMillis)
                        if (lastInput == newtInput) {
                            input(newtInput)
                        }
                    }
                }
            }
        }

        override fun beforeTextChanged(cs: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(cs: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.formatAmount(maxWholeLength: Int = 5, maxFractionLength: Int = 2) {
    if (text.toString().startsWith("0") &&
        text.toString().length == maxFractionLength && text.toString() == "00"
    ) {
        val splitText = text.toString().substring(0, text.length - 1)
        this.text = splitText.toEditable()
        this.setSelection(splitText.length)
    }
    if (text.length > maxWholeLength && !text.contains(".")) {
        val splitText = text.substring(startIndex = 0, endIndex = text.length - 1)
        this.text = splitText.toEditable()
        setSelection(splitText.length)
    }
    if (text.contains(".") && text.substring(text.indexOf(".") + 1).length > 2) {
        val splitText = text.toString().substring(startIndex = 0, endIndex = (text.length - 1))
        this.text = splitText.toEditable()
        setSelection(splitText.length)
    }
}

fun EditText.focus(postDelay: Long = 200) {
    postDelayed({
        requestFocus()
        setSelection(length())
    }, postDelay)

}

fun EditText.clearActionOnGo() {
    imeOptions = EditorInfo.IME_ACTION_GO
    setOnEditorActionListener { _, actionId, _ ->
        return@setOnEditorActionListener false
    }
}

fun EditText.setActionOnGo(action: () -> Unit) {
    imeOptions = EditorInfo.IME_ACTION_GO
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_GO) {
            action()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}

fun EditText.setActionOnDone(action: () -> Unit) {
    imeOptions = EditorInfo.IME_ACTION_DONE
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            action()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}

fun EditText.setAction(imeAction: Int, action: () -> Unit) {
    imeOptions = imeAction
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == imeAction) {
            action()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}

fun EditText.moveCarretToEnd() {
    setSelection(text.length)
}

fun EditText.setMaxLength(maxLength: Int) {
    filters = arrayOf(InputFilter.LengthFilter(maxLength))
}