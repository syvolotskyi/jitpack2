package ge.space.ui.components.list_adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * SPMenuAdapter is base adapter
 *
 * @params items [List<List<SPSelectedItem<Data><Data>] is list of List<SPSelectedItem<Data>
 * Data is onResult return type
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
 * SPMenuAdapterListener is interface which allow to set listener on click item in adapter,
 *  contains on Item Click Listener.
 * <Data> is item used in SPMenuAdapter
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
 * @params view [View] is item View
 */
open class SPMenuViewHolder(view: View) : RecyclerView.ViewHolder(view)

/**
 * SPSelectedItem contains
 *
 * @params item [Data] is a model type
 * @params isSelected [Boolean] is true when item is selected
 */
data class SPSelectedItem<Data>(
    val item: Data,
    var isSelected: Boolean
)