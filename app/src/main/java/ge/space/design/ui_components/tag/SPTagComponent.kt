package ge.space.design.ui_components.tag

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTagShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPTagComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.tag_showcase

    override fun getDescriptionResId(): Int = R.string.tag_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTagShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            return layoutBinding.root
        }
    }
}