package ge.space.ui.components.feature_list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.space.ui.components.feature_list.SPFeatureItem.Orientation
import ge.space.ui.components.list_adapter.SPMenuAdapterListener

/**
 * SPFeatureRecyclerView view extended from RecyclerView generic that allows to change its configuration.
 * SPFeatureRecyclerView has LinearLayoutManager and disabled scroll.
 *
 */
class SPFeatureRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private val adapter = SPFeatureAdapter(true)

    init {
        layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically() = false
        }
        isVerticalScrollBarEnabled = false
        setAdapter(adapter)
    }

    /**
     * Set list of SPFeatureData items
     */
    fun setItems(items: List<SPFeatureData>) {
        adapter.setAdapterItems(items)
    }

    /**
     * Set title text and custom src
     */
    fun setTitle(title: String, src: Int? = null) = adapter.setTitle(title, src)

    /**
     * Set SPMenuAdapterListener<SPFeatureData> for adapter
     */
    fun setOnSelectListener(listener: SPMenuAdapterListener<SPFeatureData>){
        adapter.adapterListener = listener
    }

    /**
     * Set a footer view
     */
    fun setFooterView(view: View) {
        adapter.setFooterView(view)
    }
}

/**
 * Set list of SPFeatureData items, title, titleSrc and footerView
 */
fun SPFeatureRecyclerView.setup(
    items: List<SPFeatureData>,
    title: String?,
    titleSrc: Int? = null,
    footer: View?
) {
    title?.let { setTitle(it, titleSrc) }
    footer?.let { setFooterView(it) }
    setItems(items)
}