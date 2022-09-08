package ge.space.design.ui_components.amount

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutAmountShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPAmountComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.amount_showcase

    override fun getDescriptionResId(): Int = R.string.amount_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutAmountShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            return layoutBinding.root
        }
    }
}