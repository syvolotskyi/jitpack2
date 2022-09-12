package ge.space.design.ui_components.amount

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemTextViewShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutAmountShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.status_messages.SPTextViewStyles
import ge.space.ui.components.amount.SPAmountView
import ge.space.ui.util.extension.onTextChanged

class SPAmountComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.amount_showcase

    override fun getDescriptionResId(): Int = R.string.amount_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutAmountShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            SPAmountStyles(environment.context).list.onEach { sample ->
                SPAmountView(environment.context).apply {
                    setViewStyle(sample.styleId)
                    titleText = resources.getResourceEntryName(sample.styleId)
                    descText = sample.description
                    amount = sample.amount
                    currency = sample.currency
                    setAddOnView(sample.addOnType, sample.addOnText)
                    setPadding(0,0,0, context.resources.getDimensionPixelSize(R.dimen.dimen_p_16))
                }.also { layoutBinding.programLL.addView(it) }
            }
            return layoutBinding.root
        }
    }
}