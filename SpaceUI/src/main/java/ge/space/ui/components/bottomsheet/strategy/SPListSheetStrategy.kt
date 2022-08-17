package ge.space.ui.components.bottomsheet.strategy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ge.space.spaceui.databinding.SpBottomsheetListBinding
import ge.space.ui.components.list_adapter.SPMenuAdapter
import ge.space.ui.components.list_adapter.SPMenuAdapterListener

/**
 * List strategy realization of [SPBottomSheetStrategy]
 * Data is onResult return type
 *
 * @param adapter [SPMenuAdapter] is child of SPMenuAdapter recycler adaprer
 * @param decorator [ItemDecoration] is nullable recycler item decorator
 */

open class SPListSheetStrategy<Data>(
    private val adapter: SPMenuAdapter<*, Data>,
    private val decorator: ItemDecoration? = null
) : SPBottomSheetStrategy<Data> {

    /**
     * Calls for initializing strategy
     *
     * @param fm [FragmentManager] is supportFragmentManager
     * @param container [ViewGroup] is parent container
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    override fun onCreate(
        fm: FragmentManager,
        container: ViewGroup,
        dismissEvent: (Data?) -> Unit
    ) {
        SpBottomsheetListBinding.inflate(LayoutInflater.from(container.context)).apply {
            decorator?.let { recyclerView.addItemDecoration(it) }
            adapter.adapterListener = object : SPMenuAdapterListener<Data> {
                override fun onItemClickListener(position: Int, data: Data?) {
                    dismissEvent(data)
                }
            }
            recyclerView.adapter = adapter
            container.addView(this.root)
        }
    }
}
