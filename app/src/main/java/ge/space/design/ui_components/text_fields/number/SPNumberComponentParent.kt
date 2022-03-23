package ge.space.design.ui_components.text_fields.number

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.text_fields.input.SPInputComponent
import ge.space.design.ui_components.text_fields.otp.SPOtpComponent
import ge.space.design.ui_components.text_fields.password.SPPasswordComponent
import ge.space.design.ui_components.text_fields.phone.SPMaskedComponent

class SPNumberComponentParent: ShowCaseComponent {
    override fun getNameResId(): Int = R.string.number_input

    override fun getDescriptionResId(): Int = R.string.number_input_desc

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            SPNumberComponent(),
            SPNumberXMLComponent()
        )
    }
}