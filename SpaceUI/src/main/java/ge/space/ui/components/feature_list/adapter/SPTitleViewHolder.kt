package ge.space.ui.components.feature_list.adapter

import ge.space.spaceui.databinding.SpFeatureListItemLayoutBinding
import ge.space.ui.components.list_adapter.SPBaseViewHolder

/**
 * Child of SPMenuViewHolder, contains binding of title  layout
 */
class SPTitleViewHolder(val binding: SpFeatureListItemLayoutBinding) :
    SPBaseViewHolder(binding.root) {
    fun bindUI(title: String, titleSrc: Int?) {
        binding.featureItem.apply {
            text = title
            titleSrc?.let { titleImage = it }
            hasZebraEffect = true
        }
    }
}