package ge.space.ui.util.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Rect
import android.os.SystemClock
import android.text.*
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.hiddenIf(visible: Boolean) {
    visibility = if (visible) View.GONE else View.VISIBLE
}

fun View.onClick(click: () -> Unit) {
    onSafeClick(500, click)
}

fun View.onClick(duration: Long, click: () -> Unit) {
    onSafeClick(duration, click)
}

// used in onSafeClick to ignore quick subsequent clicks
private var clickPermissible: Boolean = true

/**
 * Adds click listener to a View. Makes sure no subsequent clicks are
 * ignored on any view that also uses this function to listen
 * for click events within the specified duration.
 * @param duration Time in milliseconds within which subsequent clicks are ignored.
 * @param click Click action.
 */
private fun View.onSafeClick(duration: Long, click: (() -> Unit)) {
    this.setOnClickListener {
        if (!clickPermissible) {
            return@setOnClickListener
        }

        click.invoke()
        clickPermissible = false
        runDelayed(duration) {
            clickPermissible = true
        }
    }
}

fun View.clickableVisible(clickable: Boolean) {
    this.isClickable = clickable
}

fun View.clickableHidden(clickable: Boolean) {
    this.isClickable = !clickable
}

fun View.enableVisible(isEnabled: Boolean) {
    this.isEnabled = isEnabled
}

fun View.enableHidden(isEnabled: Boolean) {
    this.isEnabled = !isEnabled
}

fun View.focusableVisible(isEnabled: Boolean) {
    this.isFocusableInTouchMode = isEnabled
}

fun View.focusableHidden(isEnabled: Boolean) {
    this.isFocusableInTouchMode = !isEnabled
}

fun View.goAway() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Extension method to set View's height.
 */
fun View.setHeight(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = value
        layoutParams = lp
    }
}

/**
 * Extension method to set View's height and width.
 */
fun View.setSize(width: Int, height: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = height
        lp.width = width
        layoutParams = lp
    }
    this.requestLayout()
}

/**
 * Extension method to set View's height.
 */
fun View.setHeightFloat(value: Float) {
    val lp = layoutParams
    lp?.let {
        lp.height = dpToPx(value)
        layoutParams = lp
    }
}

/**
 * Extension method to set View's width.
 */
fun View.setWidth(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = value
        layoutParams = lp
    }
}

/**
 * Extension method to resize View with height & width.
 */
fun View.resize(width: Int, height: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = width
        lp.height = height
        layoutParams = lp
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun Rect.scale(factor: Float) {
    val diffHorizontal = (this.right - this.left) * (factor - 1f)
    val diffVertical = (this.bottom - this.top) * (factor - 1f)

    this.top -= (diffVertical / 2f).toInt()
    this.bottom += (diffVertical / 2f).toInt()

    this.left -= (diffHorizontal / 2f).toInt()
    this.right += (diffHorizontal / 2f).toInt()
}

var ImageView.tintColor: Int
    get() = 0
    set(value) {
        setColorFilter(value, android.graphics.PorterDuff.Mode.SRC_IN)
    }

@SuppressLint("SetJavaScriptEnabled")
fun WebView.loadWebView(url: String, loadCallBack: (() -> Unit)? = null) {
    val settings: WebSettings = this.settings
    settings.javaScriptEnabled = true
    this.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
    this.settings.builtInZoomControls = false
    this.settings.useWideViewPort = true
    this.settings.loadWithOverviewMode = true
    this.webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            loadCallBack?.invoke()
        }

        override fun onReceivedError(
            view: WebView,
            errorCode: Int,
            description: String,
            failingUrl: String
        ) {
        }
    }
    this.loadUrl(url)
}
