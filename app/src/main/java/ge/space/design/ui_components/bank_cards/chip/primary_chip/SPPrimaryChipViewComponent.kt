package ge.space.design.ui_components.bank_cards.chip.primary_chip

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemPrimaryChipShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutPrimaryChipShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

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

            SPPrimaryChipStyles.list.forEach { chip ->
                val itemBinding = SpItemPrimaryChipShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_ChipPrimary),
                    binding.primaryChipLayout,
                    true
                )

                with(itemBinding.primaryChip) {
                   style(chip.resId)
                }
            }

            return binding.root
        }
    }
}