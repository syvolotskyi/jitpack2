package ge.space.design.ui_components.tooltip

import com.example.spacedesignsystem.R
import ge.space.ui.components.tooltips.SPTooltipsView.ArrowDirection

data class SPTooltipsSupportsLoading(
    val resId: Int = R.style.SPTooltipDefault,
    val title: Int = R.string.small_example_text,
    val arrowDirection: ArrowDirection = ArrowDirection.None
)

object SPTooltipsStyles {
    val list = listOf(
        SPTooltipsSupportsLoading(),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.TopLeft),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.TopCenter),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.TopRight),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.BottomRight),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.BottomCenter),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.BottomLeft),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.Left),
        SPTooltipsSupportsLoading(arrowDirection = ArrowDirection.Right),
    )
}
