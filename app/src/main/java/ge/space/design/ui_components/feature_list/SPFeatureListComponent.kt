package ge.space.design.ui_components.feature_list

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutFeatureListShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.feature_list.SPFeatureListItemData
import ge.space.ui.components.feature_list.setup
import ge.space.ui.components.list_adapter.SPAdapterListener

class SPFeatureListComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.feature_list

    override fun getDescriptionResId(): Int = R.string.feature_list_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            return SpLayoutFeatureListShowcaseBinding.inflate(environment.requireLayoutInflater()).apply {

                SPFeatureListStyles(environment.context).also {
                    featureListContainer.setup(it.list, it.title, footer = it.footerView)
                    featureListContainer.setOnSelectListener(object : SPAdapterListener<SPFeatureListItemData> {
                        override fun onItemClick(position: Int, data: SPFeatureListItemData?) {
                            Toast.makeText(environment.context, data?.title, Toast.LENGTH_SHORT).show()
                        }
                    })
                }

            }.root
        }
    }
}