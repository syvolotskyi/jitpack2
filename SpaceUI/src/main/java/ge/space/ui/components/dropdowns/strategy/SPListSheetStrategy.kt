package ge.space.ui.components.dropdowns.strategy

import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ge.space.spaceui.databinding.SpBottomsheetListBinding
import ge.space.ui.components.dropdowns.data.SPMenuAdapter
import ge.space.ui.components.dropdowns.data.SPMenuAdapterListener

/**
 * List strategy realization of [SPBottomSheetStrategy]
 */

open class SPListSheetStrategy<Item>(
    private val adapter: SPMenuAdapter<*, Item>,
    private val decorator: ItemDecoration? = null
) : SPBottomSheetStrategy<Item> {

    /**
     * Calls for creation a content in bottom sheet fragment
     *
     * @param fm [FragmentManager] Child Fragment Manager of bottom sheet fragment
     * @param container [LinearLayout] for content view
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    override fun onCreate(
        fm: FragmentManager,
        container: LinearLayout,
        dismissEvent: (Item?) -> Unit
    ) {
        SpBottomsheetListBinding.inflate(LayoutInflater.from(container.context)).apply {
            decorator?.let { recyclerView.addItemDecoration(it) }
            adapter.adapterListener = object : SPMenuAdapterListener<Item> {
                override fun onItemClickListener(position: Int, data: Item?) {
                    dismissEvent(data)
                }
            }
            recyclerView.adapter = adapter
            container.addView(this.root)
        }
    }
}
