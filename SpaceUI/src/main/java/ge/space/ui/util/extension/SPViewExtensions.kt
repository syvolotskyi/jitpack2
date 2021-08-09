package ge.space.ui.util.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
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
 * Return an empty string
 *
 */
const val EMPTY_STRING = ""

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
fun ImageView.fromResource( @DrawableRes res: Int): ImageView =
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


fun View.widthByIsBig(
    isBig: Boolean,
    @DimenRes widthId: Int,
    @DimenRes smallWidthId: Int
) {
    setWidth(
        resources.getDimension(
            if (isBig) widthId
            else smallWidthId
        ).toInt()
    )
}

fun View.heightByIsBig(
    isBig: Boolean,
    @DimenRes heightId: Int,
    @DimenRes smallHeightId: Int
) {
    setHeight(
        resources.getDimension(
            if (isBig) heightId
            else smallHeightId
        ).toInt()
    )
}