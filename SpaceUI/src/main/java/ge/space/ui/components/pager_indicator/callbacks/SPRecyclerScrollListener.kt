package ge.space.ui.components.pager_indicator.callbacks

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.space.ui.components.pager_indicator.SPPageIndicator

internal class SPRecyclerScrollListener(var recyclerView: RecyclerView, var pageIndicator: SPPageIndicator) :
    RecyclerView.OnScrollListener() {

    /**
     * The previous most visible child page in the RecyclerView.
     *
     * Used to differentiate between the current most visible child page to correctly determine
     * the currently selected item and percentage scrolled.
     */
    private var previousMostVisibleChild: View? = null

    /**
     * Determine based on the percentage a child viewholder's view is visible what position
     * is the currently selected.
     *
     * Use this percentage to also calculate the offsetPercentage
     * used to scale dots.
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        val view = getMostVisibleChild()
        if (view != null) {
            setIntermediateSelectedItemPosition(
                mostVisibleChild = view
            )
            pageIndicator.offsetPercent = view.left.toFloat() / view.measuredWidth
        }

        with(recyclerView.layoutManager as LinearLayoutManager) {
            val visibleItemPosition =
                if (dx >= 0) findLastVisibleItemPosition() else findFirstVisibleItemPosition()

            if (previousMostVisibleChild !== findViewByPosition(visibleItemPosition)) {
                pageIndicator.selectedItemPosition = pageIndicator.intermediateSelectedItemPosition
            }
        }

        previousMostVisibleChild = view
        pageIndicator.invalidate()
    }

    /**
     * Returns the currently most visible viewholder view in the Recyclerview.
     *
     * The most visible view is determined based on percentage of the view visible. This is
     * calculated below in calculatePercentVisible().
     */
    private fun getMostVisibleChild(): View? {
        var mostVisibleChild: View? = null
        var mostVisibleChildPercent = 0f
        for (i in recyclerView.layoutManager?.childCount!! - 1 downTo 0) {
            val child = recyclerView.layoutManager?.getChildAt(i)
            if (child != null) {
                val percentVisible = calculatePercentVisible(
                    child = child
                )
                if (percentVisible >= mostVisibleChildPercent) {
                    mostVisibleChildPercent = percentVisible
                    mostVisibleChild = child
                }
            }
        }

        return mostVisibleChild
    }

    private fun calculatePercentVisible(child: View): Float {
        val left = child.left
        val right = child.right
        val width = child.width

        return when {
            left < 0 -> right / width.toFloat()
            right > pageIndicator.width -> (pageIndicator.width - left) / width.toFloat()
            else -> 1f
        }
    }

    private fun setIntermediateSelectedItemPosition(mostVisibleChild: View) {
        recyclerView.findContainingViewHolder(mostVisibleChild)?.adapterPosition
            ?.let { position ->
                pageIndicator.intermediateSelectedItemPosition = position

            }
    }
}
