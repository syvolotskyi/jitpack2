package ge.space.design.ui_components.bank_cards.chip.digital_chip

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemDigitalChipShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutDigitalChipShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.bank_cards.chip.card.SPDigitalChip

class SPDigitalChipViewComponent : SPShowCaseComponent {

    override fun getNameResId(): Int =
        R.string.component_digital_chip

    override fun getDescriptionResId(): Int =
        R.string.component_digital_chip_description

    override fun getComponentClass(): Class<*>? = Factory::class.java

    class Factory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutDigitalChipShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val chips = mutableListOf<SPDigitalChip>()

            SPDigitalChipStyles.list.forEach { chip ->
                val itemBinding = SpItemDigitalChipShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.digitalChipLayout,
                    true
                )

                with(itemBinding.digitalChip) {
                    size = chip.size
                    cardBackground = chip.background
                }

                chips.add(itemBinding.digitalChip)
            }

            return binding.root
        }
    }
}