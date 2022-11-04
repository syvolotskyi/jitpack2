package ge.space.ui.components.progress_navigator

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.space.spaceui.databinding.SpProgressNavigatorItemBinding
import ge.space.ui.components.bottomsheet.core.SPListAdapter
import ge.space.ui.components.progress_navigator.SPProgressNavigatorItem.ProgressState.NORMAL_STATE
import ge.space.ui.components.progress_navigator.SPProgressNavigatorItem.ProgressState.SUCCESS_STATE

/**
 * [SPProgressNavigatorContainer] view extended from RecyclerView generic that allows to change its configuration.
 * [SPProgressNavigatorContainer] has LinearLayoutManager.
 *
 */
class SPProgressNavigatorContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    /**
     * [adapter] of SPProgressNavigator data list
     */
    private val adapter =
        SPListAdapter<SpProgressNavigatorItemBinding, SPProgressNavigatorData>(false)
            .setup {
                onCreate { parent ->
                    SpProgressNavigatorItemBinding.inflate(LayoutInflater.from(context), parent, false)
                }
                onBind { binding, item, _ ->
                    binding.navigationItem.setupNavigationView(item.item)
                }
            }

    init {
        layoutManager = LinearLayoutManager(context)
        setAdapter(adapter)
    }

    /**
     * Set list of SPFeatureListItemData items
     */
    fun setItems(items: List<SPProgressNavigatorData>) {
        adapter.setAdapterItems(items)
    }

}