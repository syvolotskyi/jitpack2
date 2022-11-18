package ge.space.ui.components.pager_indicator.helper.recycler_view

import androidx.recyclerview.widget.RecyclerView
import ge.space.ui.components.pager_indicator.helper.SPPageIndicatorStateHelper

/**
 * [SPPageIndicatorRecyclerViewHelper] is child [SPPageIndicatorStateHelper]. Allows to attach [SPPagerIndicator] with recycler view.
 *
 * @param recyclerView [RecyclerView] connected recycler view.
 * @param listener [RecyclerView.OnScrollListener] implementation of RecyclerView.OnScrollListener.
 */
class SPPageIndicatorRecyclerViewHelper(
    var recyclerView: RecyclerView,
    var listener: RecyclerView.OnScrollListener
) : SPPageIndicatorStateHelper {

    init {
        recyclerView.addOnScrollListener(listener)
    }

    override fun getItemCount(): Int = recyclerView.adapter?.itemCount ?: 0

    override fun removeListener() = recyclerView.removeOnScrollListener(listener)

}
