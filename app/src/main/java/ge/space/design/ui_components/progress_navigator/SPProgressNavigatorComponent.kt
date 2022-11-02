package ge.space.design.ui_components.progress_navigator

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemProgressNavigatorShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutProgressNavigatorShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.progress_navigator.SPProgressNavigatorData
import ge.space.ui.components.progress_navigator.SPProgressNavigatorView
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
                step1.state = SPProgressNavigatorView.ProgressState.SUCCESS_STATE
                step2.state = SPProgressNavigatorView.ProgressState.SUCCESS_STATE

                SPProgressNavigationStyles.list.forEach {
                    val itemBinding = SpItemProgressNavigatorShowcaseBinding.inflate(
                        environment.requireLayoutInflater(),
                        this.layoutProgram,
                        true
                    )
                    itemBinding.snStep.setupNavigationView(it)
                    itemBinding.successButton.onClick {
                        itemBinding.snStep.state = SPProgressNavigatorView.ProgressState.SUCCESS_STATE
                        Toast.makeText(
                            environment.context,
                            "შენ ხარ კარგი ბიჭი!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            return layoutBinding.root
        }
    }
}