package ge.space.design.ui_components.chip.country

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemDigitalChipShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutCountryChipShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.design.ui_components.chip.digital_chip.SPDigitalChipStyles
import ge.space.ui.components.bank_cards.chip.card.SPDigitalChip
import ge.space.ui.components.bank_cards.chip.icon.SPChipIcon
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.util.view_factory.SPViewData

class SPCountryChipComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_contry_chip

    override fun getDescriptionResId(): Int = R.string.component_country_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutCountryChipShowCaseBinding.inflate(
                environment.requireThemedLayoutInflater(R.style.SPBankCardView_ChipDigital)
            )
          binding.testChip.bigPhotoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Flag_of_Georgia.svg/2560px-Flag_of_Georgia.svg.png"

          binding.testChipWithBorders.bigPhotoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Flag_of_Georgia.svg/2560px-Flag_of_Georgia.svg.png"



            SPCountryChipStyles.list.forEach { chip ->
           SPViewData.SPChipData(url = chip.styleRes = chip.resId,)

                with(itemBinding.digitalChip) {
                    setViewStyle(chip.resId)
                    cardBackground = chip.background
                }

                chips.add(itemBinding.digitalChip)
            }

            return binding.root
        }


            return binding.root
        }
    }
}