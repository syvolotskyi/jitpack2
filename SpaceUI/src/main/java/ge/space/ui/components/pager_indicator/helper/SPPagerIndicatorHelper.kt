package ge.space.ui.components.pager_indicator

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

interface SPPagerIndicatorHelper {
    fun getItemCount(): Int
    fun removeListener()
}

class SPRecyclerViewHelper(
    var recyclerView: RecyclerView,
    var listener: RecyclerView.OnScrollListener
) : SPPagerIndicatorHelper {

    init {
        recyclerView.addOnScrollListener(listener)
    }

    override fun getItemCount(): Int = recyclerView.adapter?.itemCount ?: 0
    override fun removeListener() {
        recyclerView.removeOnScrollListener(listener)
    }
}

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

class SPViewPager2Strategy(
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

