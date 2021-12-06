package ge.space.ui.util.extension

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ge.space.ui.base.SPBaseView.Companion.EMPTY_BORDER_VALUE
import ge.space.ui.base.SPBaseView.Companion.SIDE_RATIO
import ge.space.ui.base.SPBaseView.Companion.SQUARE_RATIO
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Extension which scales [Float] value.
 *
 * @param scale sets a value for round.
 * @return [Float] rounded value.
 */
fun Float.scaleTo(scale: Int) =
    BigDecimal(
        this.toDouble()
    ).setScale(scale, RoundingMode.HALF_EVEN).toFloat()

/**
 * Creates specified view by [layout] and adds it to parent
 *
 * @param parent [ViewGroup] value where view is inflated
 * @param layout [Int] id value what are going to be inflated
 */
fun inflateToParent(parent: ViewGroup, layout: Int) {
    LayoutInflater.from(parent.context).inflate(layout, parent, true)
}

/**
 * Return a @ColorRes from an attr
 *
 * @param attrId [Int] @AttrRes attribute value
 */
fun Context.getColorFromAttribute(@AttrRes attrId:Int):  Int {
    val typedValue = TypedValue()
    val theme: Resources.Theme = theme
    theme.resolveAttribute(attrId, typedValue, true)
   return typedValue.data
}

/**
 * A helper method to move values check.
 *
 * @param defVal Default value for non-attribute.
 * @param action Delegate action to change a view property.
 */
fun <T> T.handleAttributeAction(defVal: T, action: (T) -> Unit) {
    if (this != defVal) {
        action(this)
    }
}

/**
 * Create a simple ImageView with icon from resource
 *
 * @param res resource
 */
fun ImageView.fromResource(@DrawableRes res: Int): ImageView =
    ImageView(context)
        .apply {
            setImageResource(res)
        }

/**
 * A helper method which allows to return a result divided
 * on [SIDE_RATIO].
 */
fun Float.withSideRatio() = this / SIDE_RATIO

/**
 * A helper method which allows to return a result divided
 * on [SQUARE_RATIO].
 */
fun Float.withSquareRatio() = this / SQUARE_RATIO

/**
 * Lazy instantiation of fragment argument by [key]
 *
 * @param key sets a key of value for getting from a bundle
 * @param default sets a default value if there is no any possibilities to fetching that
 */
fun <T> Fragment.argument(key: String, default: T?) =
    lazy { arguments?.get(key) as? T ?: default }

/**
 * Lazy instantiation of fragment argument by [key]
 *
 * @param key sets a key of value for getting from a bundle
 * @param default sets a default value if there is no any possibilities to fetching that
 */
fun <T> Fragment.nonNullArgument(key: String, default: T) =
    lazy { arguments?.get(key) as? T ?: default }

/**
 * Sets a visibility of a view
 *
 * @param visible allows to set a view visibility. if true the view is visible
 * if false - view is gone
 */
fun View.visibleOrGone(visible: Boolean) {
    if (visible) {
        isVisible = visible
    } else {
        isGone = !visible
    }
}

/**
 * Extension which draw borders by canvas
 *
 * @param path of the view
 * @param borderColor color of border
 * @param borderWidth width of border
 * @param borderPaint paint instance for border
 */
fun Canvas.drawBorder(
    path: Path,
    borderColor: Int,
    borderWidth: Float,
    borderPaint: Paint
) {
    if (borderColor != EMPTY_BORDER_VALUE && borderWidth != EMPTY_BORDER_VALUE.toFloat()) {
        drawPath(path, borderPaint.apply {
            color = borderColor
            strokeWidth = borderWidth
        })
    }
}

/**
 * Sets tint for all compound Drawables
 *
 * @param color color of drawables
 */
fun TextView.setCompoundDrawablesTint(
    color: Int
) {
    compoundDrawables.forEach {
        it?.colorFilter = PorterDuffColorFilter(
            color,
            PorterDuff.Mode.SRC_IN
        )
    }
}