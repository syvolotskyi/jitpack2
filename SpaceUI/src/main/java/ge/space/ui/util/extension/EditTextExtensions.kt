package ge.space.ui.util.extension

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText

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
