package ge.space.ui.components.pager_indicator.callbacks

import androidx.viewpager.widget.ViewPager
import ge.space.ui.components.pager_indicator.SPPageIndicator

internal class SPOnPageChangeListener(private val pageIndicator: SPPageIndicator) : ViewPager.OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        pageIndicator.onPageScrolled(
            position,
            positionOffset
        )
    }

    override fun onPageSelected(position: Int) {
        pageIndicator.onPageSelected(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        // Not implemented
    }
}