package ge.space.ui.components.feature_list.adapter

import ge.space.spaceui.databinding.SpFeatureListItemLayoutBinding
import ge.space.ui.components.feature_list.SPFeatureData
import ge.space.ui.components.list_adapter.SPBaseViewHolder

/**
 * Child of SPMenuViewHolder, contains binding of Normal layout`
 */
class SPListViewHolder(var binding: SpFeatureListItemLayoutBinding) :
    SPBaseViewHolder(binding.featureItem) {
    fun bindUI(item: SPFeatureData) {
        binding.featureItem.apply {
            setViewStyle(item.style)
            text = item.title
            orientation = item.orientation
            description = item.desc
        }
    }
}