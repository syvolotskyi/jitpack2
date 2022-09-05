package ge.space.design.ui_components.tooltip

import com.example.spacedesignsystem.R
import ge.space.ui.components.tooltips.SPTooltipView.ArrowDirection

data class SPTooltipsSupportsLoading(
    val resId: Int = R.style.SPTooltipDefault,
    val title: Int = R.string.small_example_text,
    val backColorAttr: Int = R.attr.status_tertiary_success,
    val arrowDirection: ArrowDirection = ArrowDirection.None
)

object SPTooltipStyles {
    val list = listOf(
        SPTooltipsSupportsLoading(),
        SPTooltipsSupportsLoading(backColorAttr = R.attr.status_tertiary_distractive, arrowDirection = ArrowDirection.TopLeft),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.TopCenter),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.TopRight),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.BottomRight),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.BottomCenter),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.BottomLeft),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.Left),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.Right),
    )
}
