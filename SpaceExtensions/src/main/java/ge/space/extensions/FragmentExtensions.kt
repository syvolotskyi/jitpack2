package ge.space.extensions

import androidx.fragment.app.Fragment

/**
 * Extension method to provide hide keyboard for [Fragment].
 */
fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}