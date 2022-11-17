import androidx.viewpager.widget.ViewPager
import ge.space.ui.components.pager_indicator.helper.SPPagerIndicatorHelper

/**
 * [SPViewPagerHelper] is child [SPPagerIndicatorHelper]. Allows to attach [SPPagerIndicator] with ViewPager.
 *
 * @param viewPager [ViewPager] connected ViewPager.
 * @param listener [ViewPager.OnPageChangeListener] implementation of Callback.
 */
class SPViewPagerHelper(var viewPager: ViewPager, var listener: ViewPager.OnPageChangeListener) :
    SPPagerIndicatorHelper {

    init {
        viewPager.addOnPageChangeListener(listener)
    }

    override fun getItemCount(): Int = viewPager.adapter?.count ?: 0
    override fun removeListener() {
        viewPager.removeOnPageChangeListener(listener)
    }
}