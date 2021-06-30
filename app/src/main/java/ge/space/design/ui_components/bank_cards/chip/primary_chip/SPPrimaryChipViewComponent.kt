package ge.space.design.ui_components.bank_cards.chip.primary_chip

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemPrimaryChipShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutPrimaryChipShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.bank_cards.chip.SPPrimaryChip

class SPPrimaryChipViewComponent : SPShowCaseComponent {

    override fun getNameResId(): Int =
        R.string.component_primary_chip

    override fun getDescriptionResId(): Int =
        R.string.component_primary_chip_description

    override fun getComponentClass(): Class<*>? = Factory::class.java

    class Factory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutPrimaryChipShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val chips = mutableListOf<SPPrimaryChip>()

            SPPrimaryChipStyles.list.forEach { chip ->
                val itemBinding = SpItemPrimaryChipShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.primaryChipLayout,
                    true
                )

                with(itemBinding.primaryChip) {
                    size = chip.size
                }

                chips.add(itemBinding.primaryChip)
            }

            return binding.root
        }
    }
}