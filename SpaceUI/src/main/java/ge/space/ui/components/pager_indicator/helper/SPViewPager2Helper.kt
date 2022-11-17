package ge.space.ui.components.pager_indicator.helper

import androidx.viewpager2.widget.ViewPager2

/**
 * [SPViewPager2Helper] is child [SPPagerIndicatorHelper]. Allows to attach [SPPagerIndicator] with ViewPager 2.
 *
 * @param viewPager2 [ViewPager2] connected ViewPager2.
 * @param pageChangeCallback [ViewPager2.OnPageChangeCallback] implementation of ViewPager2.OnPageChangeCallback.
 */
class SPViewPager2Helper(
    var viewPager2: ViewPager2,
    var pageChangeCallback: ViewPager2.OnPageChangeCallback
) : SPPagerIndicatorHelper {

    init {
        viewPager2.registerOnPageChangeCallback(pageChangeCallback)
    }

    override fun getItemCount(): Int = viewPager2.adapter?.itemCount ?: 0
    override fun removeListener() {
        viewPager2.unregisterOnPageChangeCallback(pageChangeCallback)
    }
}

