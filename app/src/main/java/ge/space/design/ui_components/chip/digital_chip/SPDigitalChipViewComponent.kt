package ge.space.design.ui_components.chip.digital_chip

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemDigitalChipShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutDigitalChipShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.ui.components.bank_cards.chip.card.SPDigitalChip
import ge.space.ui.components.bank_cards.data.SPBankCardGradient

class SPDigitalChipViewComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_digital_chip

    override fun getDescriptionResId(): Int = R.string.component_digital_chip_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutDigitalChipShowCaseBinding.inflate(
                environment.requireThemedLayoutInflater(R.style.SPBankCardView_ChipDigital)
            )
            val chips = mutableListOf<SPDigitalChip>()
            with(binding.testChip) {
                setViewStyle(R.style.SPBankCardView_ChipDigital_Medium)
                cardBackground = SPBankCardGradient.SPRadial(
                    colors = arrayListOf(
                        SPButtonStyles.GRADIENT_BLUE_1,
                        SPButtonStyles.GRADIENT_BLUE_2
                    )
                )
            }

            chips.add(binding.testChip)

            SPDigitalChipStyles.list.forEach { chip ->
                val itemBinding = SpItemDigitalChipShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_ChipDigital),
                    binding.digitalChipLayout,
                    true
                )

                with(itemBinding.digitalChip) {
                    setViewStyle(chip.resId)
                    cardBackground = chip.background
                }

                chips.add(itemBinding.digitalChip)
            }

            return binding.root
        }
    }
}