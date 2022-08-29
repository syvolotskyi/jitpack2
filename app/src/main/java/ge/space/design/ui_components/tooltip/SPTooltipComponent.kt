package ge.space.design.ui_components.tooltip

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTooltipShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.input.search.SPSearchView

class SPTooltipComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.tooltips

    override fun getDescriptionResId(): Int = R.string.tooltips_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTooltipShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )




            return layoutBinding.root
        }
    }
}