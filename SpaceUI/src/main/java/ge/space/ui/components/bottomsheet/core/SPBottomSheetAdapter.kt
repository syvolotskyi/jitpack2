package ge.space.ui.components.bottomsheet.core

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import ge.space.ui.components.list_adapter.SPBaseListAdapter
import ge.space.ui.components.list_adapter.SPBaseViewHolder
import ge.space.ui.components.list_adapter.SPSelectedItem
import ge.space.ui.util.extension.onClick

/**
 * SPOnBottomSheetAdapter help to handle dropdown the binding after selecting an item
 * Data is onResult return type
 */
open class SPBottomSheetAdapter<VB : ViewBinding, Data> :
    SPBaseListAdapter<SPBottomSheetAdapter.ListViewHolder, Data>() {

    private var _onCreate: OnCreate<VB> = { throw IllegalStateException() }
    private var _onBind: OnBind<VB, Data> = { _, _, _ -> }

    /**
     * Calls for initializing blocks OnCreate and OnBind
     */
    fun setup(block: SPBottomSheetAdapter<VB, Data>.() -> Unit): SPBottomSheetAdapter<VB, Data> {
        block()
        return this
    }

    /**
     * OnCreate calls for creating View and return binding of it
     */
    fun onCreate(block: SPBottomSheetAdapter<VB, Data>.(parent: ViewGroup) -> VB) {
        _onCreate = { block(it) }
    }

    /**
     * OnBind calls when item should be created and throw binding of it, item and position
     */
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

    /**
     * Returns items list size
     */
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
                adapterListener?.onItemClick(
                    holder.adapterPosition,
                    it.item
                )
            }
        }
    }

    /**
     * Child of SPMenuViewHolder, contains binding of item layout
     */
    class ListViewHolder(itemView: View) : SPBaseViewHolder(itemView) {
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
