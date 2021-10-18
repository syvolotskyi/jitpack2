package ge.space.extensions

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
import kotlinx.coroutines.*

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

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

inline fun ViewGroup.forEachVisibleChild(action: (View) -> Unit) {
    for (i in 0 until childCount) {
        if (getChildAt(i).visibility == View.VISIBLE)
            action(getChildAt(i))
    }
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

fun View.justVisibleIf(boolean: Boolean) {
    if (boolean)
        this.show()
    else
        this.hide()
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}


fun View.setBackgroundTintRes(@ColorRes resId: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, resId)

}

fun View.setBackgroundTint(@ColorInt color: Int) {
    backgroundTintList = ColorStateList.valueOf(color)

}

fun View.clipOutline(outlineProvider: ViewOutlineProvider) {
    setOutlineProvider(outlineProvider)
    clipToOutline = true
}

/**
 * Extension method to remove the required boilerplate for running code after a view has been
 * inflated and measured.
 *
 * @author Antonio Leiva
 * @see <a href="https://antonioleiva.com/kotlin-ongloballayoutlistener/>Kotlin recipes: OnGlobalLayoutListener</a>
 */
inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

/**
 * Extension method to get ClickableSpan.
 * e.g.
 * val loginLink = getClickableSpan(context.getColorCompat(R.color.colorAccent), { })
 */
fun View.doOnLayout(onLayout: (View) -> Boolean) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(
            view: View, left: Int, top: Int, right: Int, bottom: Int,
            oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int
        ) {
            if (onLayout(view)) {
                view.removeOnLayoutChangeListener(this)
            }
        }
    })
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

/**
 * Extension method to resize View with height & width.
 */
fun View.resizeWithFloat(width: Float? = null, height: Float? = null) {
    val lp = layoutParams
    lp?.let {
        width?.let {
            lp.width = dpToPx(it)
        }
        height?.let {
            lp.height = dpToPx(it)
        }
        layoutParams = lp
    }
}

fun View.margin(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

fun View.marginInteger(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = (this) }
        top?.run { topMargin = (this) }
        right?.run { rightMargin = (this) }
        bottom?.run { bottomMargin = (this) }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun View.viewScale(scaleX: Float = 1.0f, scaleY: Float = 1.0f) {
    this.scaleX = scaleX
    this.scaleY = scaleY
}

fun View.updateSize(scaleX: Float = 1.0f, scaleY: Float = 1.0f) {

    val newWidth = (this.width * scaleX).toInt()
    val newHeight = (this.height * scaleY).toInt()
    val existingLayoutParams = this.layoutParams
    existingLayoutParams.width = newWidth
    existingLayoutParams.height = newHeight

    this.layoutParams = existingLayoutParams
}

fun Rect.scale(factor: Float) {
    val diffHorizontal = (this.right - this.left) * (factor - 1f)
    val diffVertical = (this.bottom - this.top) * (factor - 1f)

    this.top -= (diffVertical / 2f).toInt()
    this.bottom += (diffVertical / 2f).toInt()

    this.left -= (diffHorizontal / 2f).toInt()
    this.right += (diffHorizontal / 2f).toInt()
}


fun View.disableWhenClick(duration: Long = 500) {
    this.isEnabled = false
    runDelayed(duration, action = {
        if (this.context != null)
            this.isEnabled = true
    })
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

var View.layoutWeight: Float
    get() = (layoutParams as? LinearLayout.LayoutParams)?.weight ?: 0f
    set(value) {
        (layoutParams as? LinearLayout.LayoutParams)?.weight = value
    }

var ImageView.tintRes: Int
    get() = 0
    set(value) {
        drawable?.setTint(value)
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

fun View.focusWithKeyboard(postDelay: Long = 200, after: (() -> Unit)? = null) {
    postDelayed({
        dispatchTouchEvent(
            MotionEvent.obtain(
                SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(),
                MotionEvent.ACTION_DOWN,
                0f,
                0f,
                0
            )
        )
        dispatchTouchEvent(
            MotionEvent.obtain(
                SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(),
                MotionEvent.ACTION_UP,
                0f,
                0f,
                0
            )
        )
        after?.invoke()
    }, postDelay)
}