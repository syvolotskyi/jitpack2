package ge.space.design.ui_components.text_fields.area

import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentActivity
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextAreaShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsDropdownShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.text_fields.dropdown.SPTextFieldsDropdownItems
import ge.space.extensions.EMPTY_TEXT
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import ge.space.ui.components.dialogs.data.SPDialogIcon
import ge.space.ui.components.dialogs.data.SPDialogInfo
import ge.space.ui.components.dialogs.data.SPDialogInfoHolder
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import ge.space.ui.components.dialogs.showMultipleButtonDialog
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnBindDropdownItemModel
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnBindInterface
import ge.space.ui.util.view_factory.component_type.chip.empty.SPDefaultEmptyChipData
import java.util.ArrayList

class SPTextAreaComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.textArea

    override fun getDescriptionResId(): Int = R.string.textArea_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTextAreaShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            val area = mutableListOf<SPTextFieldInput>()

            layoutBinding.cbMandatory.setOnCheckedChangeListener { _, isChecked ->
                area.forEach { it.inputMandatory = isChecked }
            }

            layoutBinding.cbDisable.setOnCheckedChangeListener { _, isChecked ->
                area.forEach { it.isEnabled = !isChecked }
            }

            layoutBinding.cbEnableDescription.setOnCheckedChangeListener { _, isChecked ->
                area.forEach {
                    it.descriptionText = if (isChecked) {
                        layoutBinding.textArea.resources.getString(R.string.description)
                    } else {
                        EMPTY_TEXT
                    }
                }
            }

            layoutBinding.textInput.doOnTextChanged { text, _, _, _ ->
                area.forEach {
                    it.labelText = text.toString()
                    it.text = text.toString()
                }
            }


            return layoutBinding.root
        }

    }
}