package ge.space.design.ui_components.marks

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutMarksShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPMarksComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.marks

    override fun getDescriptionResId(): Int = R.string.marks_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutMarksShowcaseBinding.inflate(environment.requireLayoutInflater())


            return binding.root
        }

    }

}