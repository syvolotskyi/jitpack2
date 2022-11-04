package ge.space.ui.components.feature_list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.space.ui.components.feature_list.adapter.SPFeatureListAdapter

/**
 * SPFeatureListContainerView view extended from RecyclerView generic that allows to change its configuration.
 * SPFeatureListContainerView has LinearLayoutManager.
 *
 */
class SPFeatureListContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val adapter = SPFeatureListAdapter()

    init {
        layoutManager = LinearLayoutManager(context)
        setAdapter(adapter)
    }

    /**
     * Set list of SPFeatureListItemData items
     */
    fun setItems(items: List<SPFeatureListItemData>) {
        adapter.setAdapterItems(items)
    }

    /**
     * Set title text and custom src
     */
    fun setTitle(title: String, src: Int? = null) = adapter.setTitle(title, src)

    /**
     * Set a footer view
     */
    fun setFooterView(view: View) {
        adapter.setFooterView(view)
    }
}

/**
 * [setup] function setups and initializes feature list component
 *
 * @param items [List<SPFeatureListItemData>] sets list of SPFeatureListItemData items
 * @param title [String?] is title text
 * @param titleSrc [Int?] is a title image, default is null
 * @param footer [View?] is footer view, default is null
 */
fun SPFeatureListContainerView.setup(
    items: List<SPFeatureListItemData>,
    title: String?,
    titleSrc: Int? = null,
    footer: View? = null
) {
    title?.let { setTitle(it, titleSrc) }
    footer?.let { setFooterView(it) }
    setItems(items)
}