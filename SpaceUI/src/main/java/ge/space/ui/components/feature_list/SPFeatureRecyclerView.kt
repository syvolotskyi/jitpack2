package ge.space.ui.components.feature_list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

    fun setItems(items: List<SPFeatureData>) {
        adapter.setAdapterItems(items)
    }
    fun setTitle(title: String, src: Int? = null)  = adapter.setTitle(title, src)

    fun setFooterView(view: View) {
        adapter.setFooterView(view)
    }
}