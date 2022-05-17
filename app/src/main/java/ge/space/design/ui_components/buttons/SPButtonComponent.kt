package ge.space.design.ui_components.buttons

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.buttons.default_button.SPDefaultButtonsComponent
import ge.space.design.ui_components.buttons.fullwidth.SPFullWidthButtonsComponent
import ge.space.design.ui_components.buttons.horizontal_button.SPHorizontalButtonsComponent
import ge.space.design.ui_components.buttons.iconic_button.SPIconicButtonsComponent
import ge.space.design.ui_components.buttons.inline.SPInlineButtonComponent
import ge.space.design.ui_components.buttons.pill.SPButtonPillComponent
import ge.space.design.ui_components.buttons.vertical_button.SPVerticalButtonsComponent

class SPButtonComponent: ShowCaseComponent{

    override fun getNameResId(): Int = R.string.buttons

    override fun getDescriptionResId(): Int = R.string.button_description

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            SPDefaultButtonsComponent(),
            SPVerticalButtonsComponent(),
            SPIconicButtonsComponent(),
            SPHorizontalButtonsComponent(),
            SPFullWidthButtonsComponent(),
            SPInlineButtonComponent(),
            SPButtonPillComponent()
        )
    }
}