package ge.space.ui.view.layout

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import ge.space.ui.base.SPBaseView
import ge.space.ui.util.extension.withSideRatio
import ge.space.ui.util.extension.withSquareRatio
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * Simple extended view from [SPBaseView] which has no any additional properties.
 */
class SPFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr) {

    override fun handleShadowOffsetY(viewParams: LayoutParams) {
        val ratioOffsetY = shadowOffsetY.withSideRatio()
        if (ratioOffsetY < DEFAULT_OBTAIN_VAL) {
            setPadding(
                paddingStart + abs(ratioOffsetY.withSideRatio().roundToInt()),
                paddingTop + abs(ratioOffsetY.toInt()),
                paddingEnd + abs(ratioOffsetY.withSideRatio().roundToInt()),
                paddingBottom
            )
        } else {
            setPadding(
                paddingStart + ratioOffsetY.withSquareRatio().roundToInt(),
                paddingTop,
                paddingEnd + ratioOffsetY.withSquareRatio().roundToInt(),
                paddingBottom + ratioOffsetY.toInt()
            )
        }
    }
}