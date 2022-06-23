package ge.space.ui.components.dropdowns.strategy

import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewbinding.ViewBinding
import ge.space.spaceui.databinding.SpBottomsheetListBinding
import ge.space.ui.components.dropdowns.data.SPOnBottomSheetAdapter

/**
 * List strategy realization of [SPBottomSheetStrategy]
 */

open class SPListSheetStrategy<B : ViewBinding, T>(
    private val adapter: SPOnBottomSheetAdapter<B, T>,
    private val decorator: ItemDecoration? = null
) : SPBottomSheetStrategy {

    /**
     * Binding a item view
     */
    override fun onCreate(container: LinearLayout, dismissEvent: () -> Unit) {
        SpBottomsheetListBinding.inflate(LayoutInflater.from(container.context)).apply {
            decorator?.let { recyclerView.addItemDecoration(it) }
            adapter.onDismiss = { dismissEvent() }
            recyclerView.adapter = adapter
            container.addView(this.root)
        }
    }
}
