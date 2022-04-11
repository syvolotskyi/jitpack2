package ge.space.design.ui_components.controls.radio_buttons

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.controls.radio_buttons.list_icon.SPListItemButtonComponent
import ge.space.design.ui_components.controls.radio_buttons.standart.SPRadioButtonStandartComponent

class SPRadioButtonComponents : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.radio_buttons_component

    override fun getDescriptionResId(): Int = R.string.radio_buttons_component_description

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            SPRadioButtonStandartComponent(),
            SPListItemButtonComponent()
        )
    }
}