import androidx.recyclerview.widget.RecyclerView
import ge.space.ui.components.pager_indicator.helper.SPPagerIndicatorHelper

/**
 * [SPRecyclerViewHelper] is child [SPPagerIndicatorHelper]. Allows to attach [SPPagerIndicator] with recycler view.
 *
 * @param recyclerView [RecyclerView] connected recycler view.
 * @param listener [RecyclerView.OnScrollListener] implementation of RecyclerView.OnScrollListener.
 */
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
