package ge.space.ui.components.stepper

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

interface SPStepperStrategy {
    fun getItemCount(): Int
}

class SPRecyclerViewStrategy(var recyclerView: RecyclerView? = null) : SPStepperStrategy {
    override fun getItemCount(): Int = recyclerView?.adapter?.itemCount ?: 0
    fun removeListener(listener: RecyclerView.OnScrollListener?) {
        listener?.let { recyclerView?.removeOnScrollListener(it) }
    }

}

class SPViewPagerStrategy(var viewPager: ViewPager) : SPStepperStrategy {
    override fun getItemCount(): Int = viewPager.adapter?.count ?: 0
    fun removeListener(listener: ViewPager.OnPageChangeListener) {
        viewPager.removeOnPageChangeListener(listener)
    }
}

class SPViewPager2Strategy(var viewPager2: ViewPager2) : SPStepperStrategy {
    override fun getItemCount(): Int = viewPager2.adapter?.itemCount ?: 0
    fun removeListener(internalPageChangeCallback: SPInternalPageChangeCallback?) {
        internalPageChangeCallback?.let {
            viewPager2.unregisterOnPageChangeCallback(
                internalPageChangeCallback
            )
        }
    }
}

