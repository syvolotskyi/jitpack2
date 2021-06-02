package ge.space.design.ui_components.buttons

import com.example.spacedesignsystem.R
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.ui_components.buttons.default_button.SPDefaultButtonsComponentSP
import ge.space.design.ui_components.buttons.horizontal_button.SPHorizontalButtonsComponentSP
import ge.space.design.ui_components.buttons.vertical_button.SPVerticalButtonsComponentSP

class SPButtonComponent: SPShowCaseComponent{

    override fun getNameResId(): Int = R.string.buttons

    override fun getDescriptionResId(): Int = R.string.button_description

    override fun getSubComponents(): List<SPShowCaseComponent> {
        return listOf(
            SPDefaultButtonsComponentSP(),
            SPVerticalButtonsComponentSP(),
            SPHorizontalButtonsComponentSP()
        )
    }
}