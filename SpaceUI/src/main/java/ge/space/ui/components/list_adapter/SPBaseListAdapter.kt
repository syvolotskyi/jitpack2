package ge.space.ui.components.list_adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView

// todo add diff util
/**
 * [SPBaseListAdapter] is a base Recycler adapter
 *
 * @param items is List of [SPSelectedItem]<[Data]>
 */
@SuppressLint("NotifyDataSetChanged")
abstract class SPBaseListAdapter<T : SPBaseViewHolder?, Data>(
    protected var items: List<SPSelectedItem<Data>> = emptyList()
) : RecyclerView.Adapter<T>() {

    /**
     * Contains on Item Click Listener
     */
    var adapterListener: SPBaseAdapterListener<Data>? = null

    /**
     * [setAdapterItems] updates adapter by new [items]
     */
    open fun setAdapterItems(items: List<Data>) {
        this.items = items.map { SPSelectedItem(it, false) }
        notifyDataSetChanged()
    }

    /**
     * Sets a selected Item
     */
    fun setSelectedItem(item: Data) {
        items.forEach { it.isSelected = item == it.item }
        notifyDataSetChanged()
    }

    /**
     * Select an Item by Position
     */
    fun selectedItemByPosition(position: Int) {
        items[position].isSelected = true
        notifyDataSetChanged()
    }

    /**
     * Returns items list size
     */
    override fun getItemCount(): Int = items.size

}