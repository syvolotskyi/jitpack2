package ge.space.design.ui_components.feature_list

import android.content.Context
import android.view.LayoutInflater
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpFooterExampleLayoutBinding
import ge.space.ui.components.feature_list.SPFeatureData
import ge.space.ui.components.feature_list.SPFeatureItem


class SPFeatureListStyles(context: Context) {
    val title = context.getString(R.string.feature_list_items)
    val footerView =  SpFooterExampleLayoutBinding.inflate(LayoutInflater.from(context),).root
    val list = listOf(
        SPFeatureData(
            context.getString(R.string.small_example_text),
            context.getString(R.string.small_text_sample)
        ),
        SPFeatureData(
            context.getString(R.string.small_example_text),
            context.getString(R.string.small_text_sample),
            style = R.style.SPFeatureItem_Success
        ),
        SPFeatureData(
            context.getString(R.string.small_example_text),
            context.getString(R.string.small_text_sample),
            SPFeatureItem.Orientation.Vertical,
            style = R.style.SPFeatureItem_Success
        ),
        SPFeatureData(
            context.getString(R.string.small_example_text),
            context.getString(R.string.small_text_sample)
        ),
        SPFeatureData(
            context.getString(R.string.small_example_text),
            context.getString(R.string.small_text_sample),
            style = R.style.SPFeatureItem_Success
        ),
        SPFeatureData(
            context.getString(R.string.small_example_text),
            context.getString(R.string.small_text_sample),
            SPFeatureItem.Orientation.Vertical,
            style = R.style.SPFeatureItem_Success
        ),

    )
}