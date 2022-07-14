package ge.space.ui.components.dropdowns.data

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * SPMenuAdapter is base adapter
 *
 * @params items [List<List<SPSelectedItem<Data><Data>] is list of List<SPSelectedItem<Data>
 */
abstract class SPMenuAdapter<T : SPMenuViewHolder?, Data>(
    protected var items: List<SPSelectedItem<Data>> = emptyList()
) : RecyclerView.Adapter<T>() {

    /**
     * Contains on Item Click Listener
     */
    var adapterListener: SPMenuAdapterListener<Data>? = null

    /**
     * Sets a list of Data items, which are mapped to list of SPSelectedItem
     */
    @SuppressLint("NotifyDataSetChanged")
    open fun setAdapterItems(items: List<Data>) {
        this.items = items.map { SPSelectedItem(it, false) }
        notifyDataSetChanged()
    }

    /**
     * Sets a selected Item
     */
    fun setSelectedItem(item: Data) {
        items.forEach { it.isSelected = item == it.item }
    }
}


/**
 * SPMenuAdapterListener contains on Item Click Listener. <Data> is item used in SPMenuAdapter
 *
 */
interface SPMenuAdapterListener<Data> {
    /**
     * onItemClickListener calls when user clicked on item
     *
     * @params position [Int] is position in list
     * @params data [Data] is a clicked item
     */
    fun onItemClickListener(position: Int, data: Data?)
}

/**
 * SPMenuViewHolder is base ViewHolder for SPMenuAdapter
 *
 * @params view [View] is model
 */
open class SPMenuViewHolder(view: View) : RecyclerView.ViewHolder(view)

/**
 * SPSelectedItem contains
 *
 * @params item [T] is model
 * @params isSelected [Boolean] is true when item is selectable
 */
data class SPSelectedItem<T>(
    val item: T,
    var isSelected: Boolean
)