package ge.space.ui.util.extension

import android.graphics.Rect
import android.text.*
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity

fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
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

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
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
    this.requestLayout()
}

/**
 * Extension method returns true if height is wrap content
 */
fun View.isHeightWrapContent() =
  layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT


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
 * Extension method returns central Y by view height.
 */
 fun View.getCentralY(): Float = height.toFloat().withSideRatio()

/**
 * Extension method returns central X by view width.
 */
 fun View.getCentralX(): Float = width.toFloat().withSideRatio()

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

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

var ImageView.tintColor: Int
    get() = 0
    set(value) {
        setColorFilter(value, android.graphics.PorterDuff.Mode.SRC_IN)
    }

/**
 * Extension method returns height of status bar
 */
 fun getStatusBarHeight(activity: FragmentActivity): Int {
    val rectangle = Rect()
    val window: Window = activity.window
    window.decorView.getWindowVisibleDisplayFrame(rectangle)
    return rectangle.top
}
