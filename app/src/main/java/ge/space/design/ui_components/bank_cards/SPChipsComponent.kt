package ge.space.design.ui_components.bank_cards

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.bank_cards.card.SPBankCardViewComponent
import ge.space.design.ui_components.chip.chip_icon.SPChipIconViewComponent
import ge.space.design.ui_components.chip.country.SPCountryChipComponent
import ge.space.design.ui_components.chip.digital_chip.SPDigitalChipViewComponent
import ge.space.design.ui_components.chip.list_digital_chip.SPChipListViewComponent
import ge.space.design.ui_components.chip.new_credit_card.SPNewCreditCardComponent
import ge.space.design.ui_components.chip.primary_chip.SPPrimaryChipViewComponent
import ge.space.design.ui_components.chip.secondary_chip.SPSecondaryChipViewComponent
import ge.space.design.ui_components.chip.small_empty.SPEmptyChipViewComponent

class SPChipsComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.chips_card

    override fun getDescriptionResId(): Int = R.string.chips_description

    override fun getSubComponents(): List<ShowCaseComponent> =
        listOf(
            SPEmptyChipViewComponent(),
            SPCountryChipComponent(),
            SPChipIconViewComponent(),
            SPPrimaryChipViewComponent(),
            SPDigitalChipViewComponent(),
            SPSecondaryChipViewComponent(),
            SPChipListViewComponent(),
            SPNewCreditCardComponent()
        )
}