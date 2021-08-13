package ge.space.design.ui_components.bank_cards.chip.small_empty

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemChipBankCardShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutBankCardShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.bank_cards.chip.empty.SPEmptyChip

class SPEmptyChipViewComponent : SPShowCaseComponent {
    override fun getNameResId(): Int =
        R.string.component_small_empty_chip

    override fun getDescriptionResId(): Int =
        R.string.component_small_empty_chip_description

    override fun getComponentClass(): Class<*>? = Factory::class.java

    class Factory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutBankCardShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val cards = mutableListOf<SPEmptyChip>()

            SPEmptyChipStyles.list.forEach { bankCardSample ->
                val itemBinding = SpItemChipBankCardShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.bankCardsLayout,
                    true
                )

                with(itemBinding.emptyChipBankCard) { style(bankCardSample.resId) }

                cards.add(itemBinding.emptyChipBankCard)
            }

            return binding.root
        }
    }
}