package ge.space.ui.components.feature_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpFeatureItemFooterLayoutBinding
import ge.space.spaceui.databinding.SpFeatureListItemLayoutBinding
import ge.space.ui.components.feature_list.SPFeatureAdapter.ViewTypes.Footer
import ge.space.ui.components.feature_list.SPFeatureAdapter.ViewTypes.Header
import ge.space.ui.components.feature_list.SPFeatureAdapter.ViewTypes.Normal
import ge.space.ui.components.list_adapter.SPMenuAdapter
import ge.space.ui.components.list_adapter.SPMenuViewHolder

class SPFeatureAdapter(private val useZebraPattern: Boolean) :
    SPMenuAdapter<SPMenuViewHolder, SPFeatureData>() {
    private var title: String? = null
    private var footerView: View? = null
    private var titleSrc: Int? = null

    fun setTitle(title: String, src: Int? = null) {
        this.title = title
        this.titleSrc = src
    }

    fun setFooterView(view: View) {
        this.footerView = view
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SPMenuViewHolder = when (viewType) {
        Header -> TitleViewHolder(SpFeatureListItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply { featureItem.setViewStyle(R.style.SPFeatureItem_Title) })

        Footer -> FooterViewHolder(
            SpFeatureItemFooterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        else -> ListViewHolder(
            SpFeatureListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SPMenuViewHolder, position: Int) {
        when (holder) {
            is ListViewHolder -> holder.binding.featureItem.apply {
                val item = getItemByPosition(position)
                setViewStyle(item.style)
                text = item.title
                orientation = item.orientation
                description = item.desc
                isZebraEffect = useZebraPattern && (position % 2) == 0
            }
            is TitleViewHolder -> holder.binding.featureItem.apply {
                text = title.orEmpty()
                titleSrc?.let { image = it }
                isZebraEffect = true
            }
            is FooterViewHolder -> holder.binding.vRoot.addView(footerView)
        }
    }

    private fun getItemByPosition(position: Int) =
        items[if (hasTitle()) position - Header else position].item

    override fun getItemViewType(position: Int): Int =
        when {
            hasTitle() && position == 0 -> Header
            (position + Header == itemCount && hasFooter()) -> Footer
            else -> Normal
        }

    /**
     * Returns items list size
     */
    override fun getItemCount(): Int {
        var size = items.size
        if (hasTitle()) size++
        if (hasFooter()) size++
        return size
    }

    private fun hasTitle() = !title.isNullOrEmpty()

    private fun hasFooter() = footerView != null

    /**
     * Child of SPMenuViewHolder, contains binding of item layout
     */
    class ListViewHolder(var binding: SpFeatureListItemLayoutBinding) :
        SPMenuViewHolder(binding.featureItem)

    /**
     * Child of SPMenuViewHolder, contains binding of item layout
     */
    class TitleViewHolder(val binding: SpFeatureListItemLayoutBinding) :
        SPMenuViewHolder(binding.root)

    /**
     * Child of SPMenuViewHolder, contains binding of item layout
     */
    class FooterViewHolder(val binding: SpFeatureItemFooterLayoutBinding) :
        SPMenuViewHolder(binding.vRoot)

    private object ViewTypes {
        const val Header = 1
        const val Normal = 2
        const val Footer = 3
    }
}