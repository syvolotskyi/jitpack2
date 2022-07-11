package ge.space.ui.components.dropdowns.data

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import ge.space.ui.util.extension.onClick
import ge.space.ui.util.extension.runDelayed

/**
 * SPOnBottomSheetBind help to handle dropdown the binding after selecting an item
 */
open class SPOnBottomSheetAdapter<VB : ViewBinding, Item> : SPMenuAdapter<SPOnBottomSheetAdapter.ListViewHolder, Item>() {

    private var selectedItemPosition: Int = -1
    private var _onCreate: OnCreate<VB> = { throw IllegalStateException() }
    private var _onBind: OnBind<VB, Item> = { _, _, _ -> }
    private var _onClick: OnClick<VB, Item> = { _, _, _ -> }

    fun setup(block: SPOnBottomSheetAdapter<VB, Item>.() -> Unit): SPOnBottomSheetAdapter<VB, Item> {
        block()
        return this
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSelectedItem(position: Int) {
        this.selectedItemPosition = position

        notifyDataSetChanged()
    }

    fun getSelectedItem() = selectedItemPosition

    fun onCreate(block: SPOnBottomSheetAdapter<VB, Item>.(parent: ViewGroup) -> VB) {
        _onCreate = { block(it) }
    }

    fun onBind(block: SPOnBottomSheetAdapter<VB, Item>.(binding: VB, item: Item, position: Int) -> Unit) {
        _onBind = { binding, item, position -> block(binding, item, position) }
    }

    fun onClick(block: SPOnBottomSheetAdapter<VB, Item>.(binding: VB, item: Item, position: Int) -> Unit) {
        _onClick = { binding, item, position -> block(binding, item, position) }
    }

    fun addOnClick(block: SPOnBottomSheetAdapter<VB, Item>.(binding: VB, item: Item, position: Int) -> Unit) {
        val previusAction = _onClick
        _onClick = { binding, item, position ->
            previusAction(
                binding,
                item,
                position
            )
            block(binding, item, position)
        }
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
                setSelectedItem(holder.adapterPosition)
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
typealias OnBind<B, T> = (binding: B, item: T, position: Int) -> Unit

/**
 * OnClick calls when item was clicked
 */
typealias OnClick<B, T> = (binding: B, item: T, position: Int) -> Unit
