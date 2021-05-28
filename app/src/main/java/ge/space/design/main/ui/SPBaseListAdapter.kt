package ge.space.design.main.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

typealias OnCreate<B> = (parent: ViewGroup) -> B
typealias OnBind<B, T> = (binding: B, item: T, position: Int) -> Unit
typealias OnClick<B, T> = (binding: B, item: T, position: Int) -> Unit

class SimpleListAdapter<B : ViewBinding, T>(
    private var items: List<T>
) : RecyclerView.Adapter<SimpleListAdapter.ListViewHolder>() {

    private var _onCreate: OnCreate<B> = { throw IllegalStateException() }
    private var _onBind: OnBind<B, T> = { _, _, _ -> }
    private var _onClick: OnClick<B, T> = { _, _, _ -> }

    fun setup(block: SimpleListAdapter<B, T>.() -> Unit): SimpleListAdapter<B, T> {
        block()
        return this
    }

    fun setItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun onCreate(block: SimpleListAdapter<B, T>.(parent: ViewGroup) -> B) {
        _onCreate = { block(it) }
    }

    fun onBind(block: SimpleListAdapter<B, T>.(binding: B, item: T, position: Int) -> Unit) {
        _onBind = { binding, item, position -> block(binding, item, position) }
    }

    fun onClick(block: SimpleListAdapter<B, T>.(binding: B, item: T, position: Int) -> Unit) {
        _onClick = { binding, item, position -> block(binding, item, position) }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = _onCreate(parent)
        return ListViewHolder(
            binding.root
        ).also { holder ->
            holder.binding = binding
            holder.itemView.setOnClickListener {
                _onClick(
                    holder.binding as B,
                    holder.item as T,
                    holder.adapterPosition
                )
            }
        }
    }

    override fun getItemCount(): Int = items.size

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.item = item
        _onBind(holder.binding as B, item, position)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: Any? = null
        var binding: Any? = null
    }
}