package ge.space.design.ui_components.text_fields

import com.example.spacedesignsystem.R
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.ui_components.text_fields.otp.SPOtpComponent
import ge.space.design.ui_components.text_fields.password.SPPasswordComponent

class SPTextFieldsComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.text_fields

    override fun getDescriptionResId(): Int = R.string.text_fields_desc

    override fun getSubComponents(): List<SPShowCaseComponent> {
        return listOf(
            SPPasswordComponent(),
            SPOtpComponent()
        )
    }
}