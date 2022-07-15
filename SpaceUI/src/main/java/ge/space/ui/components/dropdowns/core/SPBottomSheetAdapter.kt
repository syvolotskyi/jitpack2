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
 * Data is onResult return type
 */
open class SPBottomSheetAdapter<VB : ViewBinding, Data> :
    SPMenuAdapter<SPBottomSheetAdapter.ListViewHolder, Data>() {

    private var _onCreate: OnCreate<VB> = { throw IllegalStateException() }
    private var _onBind: OnBind<VB, Data> = { _, _, _ -> }

    fun setup(block: SPBottomSheetAdapter<VB, Data>.() -> Unit): SPBottomSheetAdapter<VB, Data> {
        block()
        return this
    }

    fun onCreate(block: SPBottomSheetAdapter<VB, Data>.(parent: ViewGroup) -> VB) {
        _onCreate = { block(it) }
    }

    fun onBind(block: SPBottomSheetAdapter<VB, Data>.(binding: VB, item: SPSelectedItem<Data>, position: Int) -> Unit) {
        _onBind = { binding, item, position -> block(binding, item, position) }
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
        }
    }

    override fun getItemCount(): Int = items.size

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val item = items[position]
        _onBind(holder.binding as VB, item, position)
        holder.itemView.onClick {
            item.let {
                setSelectedItem(it.item)
                adapterListener?.onItemClickListener(
                    holder.adapterPosition,
                    it.item
                )
            }
        }
    }

    class ListViewHolder(itemView: View) : SPMenuViewHolder(itemView) {
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
