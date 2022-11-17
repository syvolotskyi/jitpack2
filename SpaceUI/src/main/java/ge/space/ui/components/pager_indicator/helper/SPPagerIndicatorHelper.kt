package ge.space.ui.components.pager_indicator

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

/**
 * [SPPagerIndicatorHelper] interface which help to store and handle information of pager for Page Indicator
 */
interface SPPagerIndicatorHelper {

    /**
     * Returns [Int] page indicator dots count
     */
    fun getItemCount(): Int

    /**
     * Remove listening selected page
     */
    fun removeListener()
}
