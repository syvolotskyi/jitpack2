package ge.space.ui.components.pager_indicator.callbacks

import androidx.viewpager2.widget.ViewPager2
import ge.space.ui.components.pager_indicator.SPPageIndicator

internal class SPPageChangeCallback(private val pageIndicator: SPPageIndicator) :
    ViewPager2.OnPageChangeCallback() {

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        pageIndicator.onPageScrolled(
            position,
            positionOffset
        )
    }

    override fun onPageSelected(position: Int) {
        pageIndicator.onPageSelected(position)
    }
}
