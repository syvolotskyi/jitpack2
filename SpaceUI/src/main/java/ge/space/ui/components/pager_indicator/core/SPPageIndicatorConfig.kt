package ge.space.ui.components.pager_indicator.core

import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.components.pager_indicator.core.SPPageIndicator.Companion.DEFAULT_DOT_COUNT
import ge.space.ui.components.pager_indicator.core.SPPageIndicator.Companion.DEFAULT_FADING_DOT_COUNT

/**
 * [SPPageIndicatorConfig] contains all [SPPageIndicator] ui attrs, We can define this class as a configuration helper class for [SPPageIndicator]
 * [SPPageIndicator] uses [SPPageIndicatorConfig] to store ui attrs and for drawing ui
 *
 * @property dotCount [Int] Value which sets total count of visible dots .
 * @property fadingDotCount [Int] Sets total count of visible fading dots.
 * @property selectedDotSizePx [Int] Sets size of the selected dot.
 * @property dotSizePx [Int] Sets size of the unselected dot.
 * @property selectedDotColor [Int] Sets color of the selected dot.
 * @property dotSeparationDistancePx [Int] Distance between dots.
 * @property dotColor [Int]Sets color of the unselected dot.
 */
data class SPPageIndicatorConfig(
    var dotCount: Int = DEFAULT_DOT_COUNT,
    var dotSizePx: Int = DEFAULT_OBTAIN_VAL,
    var dotColor: Int = R.attr.brand_secondary,
    var centralizeSelectedDot: Boolean = false,
    var selectedDotSizePx: Int = DEFAULT_OBTAIN_VAL,
    var selectedDotColor: Int = R.attr.brand_primary,
    var fadingDotCount: Int = DEFAULT_FADING_DOT_COUNT,
    var dotSeparationDistancePx: Int = DEFAULT_OBTAIN_VAL
)