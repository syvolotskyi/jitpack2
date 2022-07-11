package ge.space.ui.components.dropdowns.data

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SPMenuAdapter<T : SPMenuViewHolder?, Data> : RecyclerView.Adapter<T>() {
    var adapterListener: SPMenuAdapterListener<Data>? = null

    protected var items: List<Data> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    open fun setAdapterItems(items: List<Data>) {
        this.items = items
        notifyDataSetChanged()
    }
}


interface SPMenuAdapterListener<Data> {
    fun onItemClickListener(position: Int, data: Data?)
}

open class SPMenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
}