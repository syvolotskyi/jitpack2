package ge.space.design.ui_components.marks

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemMarkLayoutBinding
import com.example.spacedesignsystem.databinding.SpLayoutMarksShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.marks.SPMarkStyles.bankURL
import ge.space.design.ui_components.marks.SPMarkStyles.brandBankURL
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.view_factory.SPViewData

class SPMarksComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.marks

    override fun getDescriptionResId(): Int = R.string.marks_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutMarksShowcaseBinding.inflate(environment.requireLayoutInflater())

            SPMarkStyles.list.onEach { markSample ->
                val itemBinding = SpItemMarkLayoutBinding.inflate(
                    environment.requireLayoutInflater(),
                    binding.fieldsLayout,
                    true
                )
                itemBinding.apply {
                    setOf(
                        mark1,
                        mark2,
                        mark3,
                        mark4,
                    ).forEach { it.setViewStyle(markSample) }
                    mark1.setViewData(
                        SPViewData.SPImageUrlData(bankURL)
                    )
                    mark2.setViewData(
                        SPViewData.SPTextData("VS")
                    )
                    mark3.setViewData(
                        SPViewData.SPImageUrlData(brandBankURL)
                    )
                    mark4.setViewData(SPViewData.SPImageResourcesData(R.drawable.ic_plus_16_regular,
                        tintColor =  environment.context.getColorFromAttribute(
                        ge.space.spaceui.R.attr.brand_primary)))
                }

            }
            return binding.root
        }

    }

}