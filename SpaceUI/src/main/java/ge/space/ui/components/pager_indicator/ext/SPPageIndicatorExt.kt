package ge.space.ui.components.pager_indicator.ext

import ge.space.ui.components.pager_indicator.core.SPPageIndicator

/**
 * [onPageScrolled] is used from ViewPager onPageCallback to handle
 * ui state changes after onPageScrolled call back
 *
 * @param position is a view pager page index
 * @param positionOffset – Value from [0, 1) indicating the offset from the page at position.
 */
internal fun SPPageIndicator.onPageScrolled(position: Int, positionOffset: Float) {
    if (!indicatorConfig.centralizeSelectedDot) {
        selectedItemPosition = position
        intermediateSelectedItemPosition = position
        offsetPercent = positionOffset * -1

        invalidate()
    }
}

/**
 * [onPageScrolled] is used from ViewPager onPageCallback to handle
 * ui state changes after onPageSelected call back
 *
 * @param position is a view pager page index
 */
internal fun SPPageIndicator.onPageSelected(position: Int) {
    intermediateSelectedItemPosition = selectedItemPosition
    selectedItemPosition = position
    invalidate()
}