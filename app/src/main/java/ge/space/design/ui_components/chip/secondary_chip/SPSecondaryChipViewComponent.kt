package ge.space.design.ui_components.chip.secondary_chip

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemSecondaryChipShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutSecondaryChipShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.bank_cards.chip.card.SPSecondaryChip

class SPSecondaryChipViewComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_secondary_chip

    override fun getDescriptionResId(): Int = R.string.component_secondary_chip_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutSecondaryChipShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val chips = mutableListOf<SPSecondaryChip>()

            SPSecondaryChipStyles.list.forEach { chip ->
                val itemBinding = SpItemSecondaryChipShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_ChipSecondary),
                    binding.digitalChipLayout,
                    true
                )

                with(itemBinding.secondaryChip) {
                    setViewStyle(chip.resId)
                    bankLogoUrl = chip.bankLogoUrl
                    paymentSystemUrl = chip.paymentSystemUrl
                }

                chips.add(itemBinding.secondaryChip)
            }

            return binding.root
        }
    }
}