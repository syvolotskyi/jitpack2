package ge.space.ui.components.pager_indicator.helper.view_pager

import androidx.viewpager.widget.ViewPager
import ge.space.ui.components.pager_indicator.core.SPPageIndicator
import ge.space.ui.components.pager_indicator.ext.onPageScrolled
import ge.space.ui.components.pager_indicator.ext.onPageSelected

/**
 * Callback interface for responding to changing state of the selected page.
 *
 * @property pageIndicator is a page indicator, which helps us to update [SPPageIndicator] state
 */
internal class SPVPOnPageChangeListener(
    private val pageIndicator: SPPageIndicator
) : ViewPager.OnPageChangeListener {

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        pageIndicator.onPageScrolled(position, positionOffset)
    }

    override fun onPageSelected(position: Int) {
        pageIndicator.onPageSelected(position)
    }

    override fun onPageScrollStateChanged(state: Int) = Unit

}