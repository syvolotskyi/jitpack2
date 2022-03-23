package ge.space.ui.util.extension

import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Request focus and open keyboard
 */
fun View.showKeyboard(){
    requestFocus()
    (context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        showSoftInput(this@showKeyboard, InputMethodManager.SHOW_IMPLICIT)
    }
}

/**
 * Clear focus and hide keyboard
 */
fun View.hideKeyboard(){
    clearFocus()
    (context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(this@hideKeyboard.windowToken, 0)
    }
}