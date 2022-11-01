package ge.space.ui.components.feature_list.adapter

import android.view.View
import ge.space.spaceui.databinding.SpFeatureItemFooterLayoutBinding
import ge.space.ui.components.list_adapter.SPBaseViewHolder

/**
 * [SPFooterViewHolder] is a child of [SPBaseViewHolder], contains binding of footer layout
 */
class SPFooterViewHolder(val binding: SpFeatureItemFooterLayoutBinding) :
    SPBaseViewHolder(binding.vRoot) {
    fun bindUI(footerView: View) {
        binding.vRoot.removeAllViews()
        binding.vRoot.addView(footerView)
    }
}