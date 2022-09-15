package ge.space.design.ui_components.chip.new_credit_card

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemNewCreditCardShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutNewCreditCardShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPNewCreditCardComponent: ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_new_credit_card

    override fun getDescriptionResId(): Int = R.string.component_new_credit_card_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutNewCreditCardShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            SPNewCreditCardStyles.list.forEach { chip ->
                val itemBinding = SpItemNewCreditCardShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.primaryChipLayout,
                    true
                )

                with(itemBinding.newCreditCard) {
                    size = chip.size
                }
            }

            return binding.root
        }
    }
}