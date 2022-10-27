package ge.space.design.ui_components.feature_list

import android.view.LayoutInflater
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
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

class SPFeatureListComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.feature_list

    override fun getDescriptionResId(): Int = R.string.feature_list_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutFeatureListShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            ).apply {
                val context = environment.context
                recyclerView.apply {
                    setTitle(context.getString(R.string.feature_list_items))
                    setFooterView(
                        SpFooterExampleLayoutBinding.inflate(
                            LayoutInflater.from(environment.context),
                        ).root
                    )
                    setItems(
                        listOf(
                            SPFeatureData("Item 1", "Desc 1"),
                            SPFeatureData("Item 2", "Desc 2"),
                            SPFeatureData("Item 3", "Desc 3"),
                            SPFeatureData("Item 4", "Desc 4"),
                        )
                    )
                }
            }


            return layoutBinding.root
        }
    }
}