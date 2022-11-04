package ge.space.design.ui_components.progress_navigator

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemProgressNavigatorShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutProgressNavigatorShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.progress_navigator.SPProgressNavigatorItem
import ge.space.ui.components.progress_navigator.SPProgressNavigatorItem.ProgressState
import ge.space.ui.util.extension.onClick

class SPProgressNavigatorComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.progress_navigator_showcase

    override fun getDescriptionResId(): Int = R.string.progress_navigator_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutProgressNavigatorShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            ).apply {
                // set list for drawing progress navigator component list
                progressNavigatorContainer.setItems(SPProgressNavigationStyles.list)

                SPProgressNavigationStyles.list.forEach {
                    SpItemProgressNavigatorShowcaseBinding.inflate(environment.requireLayoutInflater(), this.linearLay, true).apply {
                        snStep.setupNavigationView(it)
                        successButton.onClick {
                            snStep.state = if (snStep.state == ProgressState.SUCCESS_STATE) ProgressState.NORMAL_STATE else ProgressState.SUCCESS_STATE
                            Toast.makeText(environment.context, "შენ ხარ კარგი ბიჭი!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            return layoutBinding.root
        }
    }
}