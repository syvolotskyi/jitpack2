package ge.space.ui.components.dropdowns.strategy

import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewbinding.ViewBinding
import ge.space.spaceui.databinding.SpBottomsheetListBinding
import ge.space.ui.components.dropdowns.SpBottomSheetFragment
import ge.space.ui.components.dropdowns.data.SPOnBottomSheetAdapter

/**
 * List strategy realization of [SpBottomSheetStrategy]
 */

open class SpListSheetStrategy<B : ViewBinding, T>(
    private val adapter: SPOnBottomSheetAdapter<B, T>,
    private val decorator: ItemDecoration? = null
) : SpBottomSheetStrategy {

    /**
     * Binding a item view
     */
    override fun onAddCreate(container: LinearLayout, dissmisEvent : ()->Unit) {
        val listBinding = SpBottomsheetListBinding.inflate(LayoutInflater.from(container.context))
        adapter.onDismiss= {dissmisEvent()}
        listBinding.apply {
            decorator?.let { recyclerView.addItemDecoration(it) }
            recyclerView.adapter = adapter
        }
        container.addView(listBinding.root)
    }
}
