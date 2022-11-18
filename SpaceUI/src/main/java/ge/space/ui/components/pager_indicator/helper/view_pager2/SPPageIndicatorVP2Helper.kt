package ge.space.ui.components.pager_indicator.helper.view_pager2

import androidx.viewpager2.widget.ViewPager2
import ge.space.ui.components.pager_indicator.helper.SPPageIndicatorStateHelper

/**
 * [SPPageIndicatorVP2Helper] is child [SPPageIndicatorStateHelper]. Allows to attach [SPPagerIndicator] with ViewPager 2.
 *
 * @param viewPager2 [ViewPager2] connected ViewPager2.
 * @param pageChangeCallback [ViewPager2.OnPageChangeCallback] implementation of ViewPager2.OnPageChangeCallback.
 */
class SPPageIndicatorVP2Helper(
    var viewPager2: ViewPager2,
    var pageChangeCallback: ViewPager2.OnPageChangeCallback
) : SPPageIndicatorStateHelper {

    init {
        viewPager2.registerOnPageChangeCallback(pageChangeCallback)
    }

    override fun getItemCount() = viewPager2.adapter?.itemCount ?: 0

    override fun removeListener() = viewPager2.unregisterOnPageChangeCallback(pageChangeCallback)

}

