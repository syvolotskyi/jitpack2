package ge.space.ui.components.pager_indicator.helper.view_pager

import androidx.viewpager.widget.ViewPager
import ge.space.ui.components.pager_indicator.helper.SPPageIndicatorStateHelper

/**
 * [SPPageIndicatorVPHelper] is child [SPPageIndicatorStateHelper]. Allows to attach [SPPagerIndicator] with ViewPager.
 *
 * @param viewPager [ViewPager] connected ViewPager.
 * @param listener [ViewPager.OnPageChangeListener] implementation of Callback.
 */
class SPPageIndicatorVPHelper(
    private val viewPager: ViewPager,
    private val listener: ViewPager.OnPageChangeListener
) : SPPageIndicatorStateHelper {

    init {
        viewPager.addOnPageChangeListener(listener)
    }

    override fun getItemCount(): Int = viewPager.adapter?.count ?: 0

    override fun removeListener() = viewPager.removeOnPageChangeListener(listener)

}