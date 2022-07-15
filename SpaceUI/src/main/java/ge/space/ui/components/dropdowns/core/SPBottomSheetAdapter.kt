package ge.space.ui.components.dropdowns.core

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import ge.space.ui.components.list_adapter.SPMenuAdapter
import ge.space.ui.components.list_adapter.SPMenuViewHolder
import ge.space.ui.components.list_adapter.SPSelectedItem
import ge.space.ui.util.extension.onClick

/**
 * SPOnBottomSheetAdapter help to handle dropdown the binding after selecting an item
 */
open class SPBottomSheetAdapter<VB : ViewBinding, Item> : SPMenuAdapter<SPBottomSheetAdapter.ListViewHolder, Item>() {

    private var _onCreate: OnCreate<VB> = { throw IllegalStateException() }
    private var _onBind: OnBind<VB, Item> = { _, _, _ -> }
    private var _onClick: OnClick<VB, Item> = { _, _, _ -> }

    fun setup(block: SPBottomSheetAdapter<VB, Item>.() -> Unit): SPBottomSheetAdapter<VB, Item> {
        block()
        return this
    }

    fun onCreate(block: SPBottomSheetAdapter<VB, Item>.(parent: ViewGroup) -> VB) {
        _onCreate = { block(it) }
    }

    fun onBind(block: SPBottomSheetAdapter<VB, Item>.(binding: VB, item: SPSelectedItem<Item>, position: Int) -> Unit) {
        _onBind = { binding, item, position -> block(binding, item, position) }
    }

    fun onClick(block: SPBottomSheetAdapter<VB, Item>.(binding: VB, item: Item, position: Int) -> Unit) {
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
            holder.itemView.onClick {
                setSelectedItem(holder.item as Item)
                _onClick(holder.binding as VB, holder.item as Item, holder.adapterPosition)
                adapterListener?.onItemClickListener(holder.adapterPosition, holder.item as Item)
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
        _onBind(holder.binding as VB, item, position)
    }

    class ListViewHolder(itemView: View) : SPMenuViewHolder(itemView) {
        var item: Any? = null
        var binding: Any? = null
    }
}

/**
 * OnCreate calls for creating View and return binding of it
 */
typealias OnCreate<B> = (parent: ViewGroup) -> B

/**
 * OnBind calls when item should be created and throw binding of it, item and position
 */
typealias OnBind<B, T> = (binding: B, item: SPSelectedItem<T>, position: Int) -> Unit

/**
 * OnClick calls when item was clicked
 */
typealias OnClick<B, T> = (binding: B, item: T, position: Int) -> Unit
