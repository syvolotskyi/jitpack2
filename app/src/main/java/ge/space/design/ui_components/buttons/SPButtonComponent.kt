package ge.space.design.ui_components.buttons

import com.example.spacedesignsystem.R
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.ui_components.buttons.bank_card_button.SPBankCardButtonsComponent
import ge.space.design.ui_components.buttons.default_button.SPDefaultButtonsComponent
import ge.space.design.ui_components.buttons.horizontal_button.SPHorizontalButtonsComponent
import ge.space.design.ui_components.buttons.vertical_button.SPVerticalButtonsComponent

class SPButtonComponent: SPShowCaseComponent{

    override fun getNameResId(): Int = R.string.buttons

    override fun getDescriptionResId(): Int = R.string.button_description

    override fun getSubComponents(): List<SPShowCaseComponent> {
        return listOf(
            SPDefaultButtonsComponent(),
            SPVerticalButtonsComponent(),
            SPHorizontalButtonsComponent(),
            SPBankCardButtonsComponent()
        )
    }
}