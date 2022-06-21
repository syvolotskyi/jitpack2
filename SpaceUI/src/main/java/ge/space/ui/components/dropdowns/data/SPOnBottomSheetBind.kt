package ge.space.ui.components.dropdowns.data

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ge.space.spaceui.databinding.SpBankCardBodyBinding
import ge.space.ui.components.buttons.SPButtonInline
import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import ge.space.ui.util.extension.runDelayed

typealias OnCreate<B> = (parent: ViewGroup) -> B
typealias OnBind<B, T> = (binding: B, item: T, position: Int) -> Unit
typealias OnClick<B, T> = (binding: B, item: T, position: Int) -> Unit

/**
 * SPOnBottomSheetBind help to handle dropdown the binding after selecting an item
 */
open class SPOnBottomSheetAdapter<B : ViewBinding, T>(
    private var items: List<T>,
    var delayTime: Long = 300
) : RecyclerView.Adapter<SPOnBottomSheetAdapter.ListViewHolder>() {

    var onDismiss: () -> Unit = {}
    private var _onCreate: OnCreate<B> = { throw IllegalStateException() }
    private var _onBind: OnBind<B, T> = { _, _, _ -> }
    private var _onClick: OnClick<B, T> = { _, _, _ -> }

    fun setup(block: SPOnBottomSheetAdapter<B, T>.() -> Unit): SPOnBottomSheetAdapter<B, T> {
        block()
        return this
    }

    fun setItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun onCreate(block: SPOnBottomSheetAdapter<B, T>.(parent: ViewGroup) -> B) {
        _onCreate = { block(it) }
    }

    fun onBind(block: SPOnBottomSheetAdapter<B, T>.(binding: B, item: T, position: Int) -> Unit) {
        _onBind = { binding, item, position -> block(binding, item, position) }
    }

    fun onClick(block: SPOnBottomSheetAdapter<B, T>.(binding: B, item: T, position: Int) -> Unit) {
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
                runDelayed(delayTime, action = { onDismiss() })
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