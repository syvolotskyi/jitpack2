package ge.space.design.ui_components.feature_list

import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpFooterExampleLayoutBinding
import com.example.spacedesignsystem.databinding.SpLayoutFeatureListShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.spaceui.databinding.SpFeatureListItemLayoutBinding
import ge.space.ui.components.feature_list.SPFeatureAdapter
import ge.space.ui.components.feature_list.SPFeatureData
import ge.space.ui.components.feature_list.setup
import ge.space.ui.components.list_adapter.SPMenuAdapterListener
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

class SPFeatureListComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.feature_list

    override fun getDescriptionResId(): Int = R.string.feature_list_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            return SpLayoutFeatureListShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            ).apply {
                SPFeatureListStyles(environment.context).also {
                    recyclerView.setup(it.list, it.title, footer = it.footerView)
                    recyclerView.setOnSelectListener(object : SPMenuAdapterListener<SPFeatureData> {
                        override fun onItemClickListener(position: Int, data: SPFeatureData?) {
                            Toast.makeText(environment.context, data?.title, Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }.root
        }
    }
}