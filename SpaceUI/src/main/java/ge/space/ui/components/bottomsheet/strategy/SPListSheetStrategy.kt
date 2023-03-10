package ge.space.ui.components.bottomsheet.strategy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ge.space.spaceui.databinding.SpBottomsheetListBinding
import ge.space.ui.components.bottomsheet.core.SPBottomSheetFragment
import ge.space.ui.components.list_adapter.SPBaseListAdapter
import ge.space.ui.components.list_adapter.SPBaseAdapterListener

/**
 * List strategy realization of [SPBottomSheetStrategy]
 * Data is onResult return type
 *
 * @param adapter [SPBaseListAdapter] is child of SPMenuAdapter recycler adaprer
 * @param decorator [ItemDecoration] is nullable recycler item decorator
 */

open class SPListSheetStrategy<Data>(
    private val adapter:  SPBaseListAdapter<*, Data>,
    private val decorator: ItemDecoration? = null
) : SPBottomSheetStrategy<Data> {

    /**
     * Calls for initializing strategy
     *
     * @param sheetFragment [ SPBottomSheetFragment<*>] is a bottom sheet fragment
     * @param container [ViewGroup] is parent container
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    override fun onCreate(
        sheetFragment: SPBottomSheetFragment<*>,
        container: ViewGroup,
        dismissEvent: (Data?) -> Unit
    ) {
        super.onCreate(sheetFragment, container, dismissEvent)
        SpBottomsheetListBinding.inflate(LayoutInflater.from(container.context),container, true ).apply {
            decorator?.let { recyclerView.addItemDecoration(it) }
            adapter.adapterListener = object : SPBaseAdapterListener<Data> {
                override fun onItemClick(position: Int, data: Data?) {
                    dismissEvent(data)
                }
            }
            recyclerView.adapter = adapter
        }
    }
}
