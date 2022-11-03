package ge.space.ui.components.feature_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpFeatureItemFooterLayoutBinding
import ge.space.spaceui.databinding.SpFeatureListItemLayoutBinding
import ge.space.ui.components.feature_list.SPFeatureListItemData
import ge.space.ui.components.list_adapter.SPBaseListAdapter
import ge.space.ui.components.list_adapter.SPBaseViewHolder

/**
 * [SPFeatureListAdapter] help to handle feature list.
 */
class SPFeatureListAdapter :
    SPBaseListAdapter<SPBaseViewHolder, SPFeatureListItemData>() {

    private var title: String? = null
    private var footerView: View? = null
    private var titleSrc: Int? = null

    /**
     * Set title text and custom src
     */
    fun setTitle(title: String, src: Int? = null) {
        this.title = title
        this.titleSrc = src
    }

    /**
     * Set a footer view
     */
    fun setFooterView(view: View) {
        this.footerView = view
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SPBaseViewHolder = when (viewType) {

        HEADER_VIEW -> SPTitleViewHolder(
            SpFeatureListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply { featureItem.setViewStyle(R.style.SPFeatureListItem_Title) })

        FOOTER_VIEW -> SPFooterViewHolder(
            SpFeatureItemFooterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        else -> SPListViewHolder(
            SpFeatureListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SPBaseViewHolder, position: Int) {
        when (holder) {
            is SPListViewHolder -> holder.bindUI(
                getItemByPosition(position), (position % 2) == 0
            )
            is SPTitleViewHolder -> holder.bindUI(title.orEmpty(), titleSrc)
            is SPFooterViewHolder -> footerView?.let { holder.bindUI(it) }
        }
    }

    private fun getItemByPosition(position: Int) =
        items[if (hasTitle()) position - HEADER_VIEW else position].item

    override fun getItemViewType(position: Int): Int =
        when {
            hasTitle() && position == 0 -> HEADER_VIEW
            (position + HEADER_VIEW == itemCount && hasFooter()) -> FOOTER_VIEW
            else -> NORMAL_VIEW
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

    companion object ViewTypes {
        private const val HEADER_VIEW = 1
        private const val NORMAL_VIEW = 2
        private const val FOOTER_VIEW = 3
    }
}