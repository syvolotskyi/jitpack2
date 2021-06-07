package ge.space.extensions

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup

inline fun ViewGroup.forEachNonNullChildWithIndex(action: (Int, View) -> Unit) {
    for (i in 0..childCount - 1) {
        getChildAt(i)?.let {
            action(i, it)
        }
    }
}

fun ViewGroup.getViewRectRelativeToSelf(view: View): Rect {
    val viewRect = Rect()
    val schirmGlobalRect = Rect()
    getViewRectRelativeToSelf(view, viewRect, schirmGlobalRect)
    return viewRect
}

fun ViewGroup.getViewRectRelativeToSelf(view: View, viewRect: Rect, schirmGlobalRect: Rect) {
    view.getGlobalVisibleRect(viewRect)
    getGlobalVisibleRect(schirmGlobalRect)
    viewRect.top -= schirmGlobalRect.top
    viewRect.left -= schirmGlobalRect.left
    viewRect.bottom -= schirmGlobalRect.top
    viewRect.right -= schirmGlobalRect.left
}

fun ViewGroup.viewScale(scaleX: Float = 1.0f, scaleY: Float = 1.0f) {
    this.scaleX = scaleX
    this.scaleY = scaleY
}