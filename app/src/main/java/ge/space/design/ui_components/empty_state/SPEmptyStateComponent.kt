package ge.space.design.ui_components.empty_state

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutEmptyStateShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutMarksShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.marks.SPMarkStyles
import ge.space.design.ui_components.marks.SPMarksComponent
import ge.space.ui.util.view_factory.SPViewData

class SPEmptyStateComponent  : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.empty_state

    override fun getDescriptionResId(): Int = R.string.empty_state_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutEmptyStateShowcaseBinding.inflate(environment.requireLayoutInflater())

            return binding.root
        }
        }
}