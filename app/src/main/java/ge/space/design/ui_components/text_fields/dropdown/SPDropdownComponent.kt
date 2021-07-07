package ge.space.design.ui_components.text_fields.dropdown

import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemTextFieldsDropdownShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsListShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.spaceui.databinding.SpTextFieldDropdownBinding
import ge.space.design.ui_components.text_fields.input.SPInputComponent
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView

class SPDropdownComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.dropdown

    override fun getDescriptionResId(): Int = R.string.dropdown_desc

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTextFieldsListShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )

            val dropdowns = mutableListOf<SPTextFieldBaseView<ge.space.spaceui.databinding.SpTextFieldDropdownBinding>>()

            SPTextFieldsDropdownStyles.list.onEach { fieldSample ->

                val resId = fieldSample.resId

                val itemBinding = SpItemTextFieldsDropdownShowcaseBinding.inflate(
                    environmentSP.requireThemedLayoutInflater(resId),
                    layoutBinding.fieldsLayout,
                    true
                )

                with(itemBinding.tfDropdown) {
                    style(fieldSample.resId)
                    dropdowns.add(this)
                    defaultIcon = R.drawable.ic_country_georgia_24_regular
                    defaultText = resources.getString(R.string.enter_you_details_here)

                    buildWithItemModel()
                }

                with(itemBinding.buttonName) {
                    val resName = resources.getResourceEntryName(resId)
                    text = resName.substringAfter(".", resName)
                }

                itemBinding.cbMandatory.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.tfDropdown.inputMandatory = isChecked
                }

                itemBinding.cbDisable.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.tfDropdown.isEnabled = !isChecked
                }

                itemBinding.cbDescription.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.tfDropdown.descriptionText = if (isChecked) {
                        itemBinding.tfDropdown.resources.getString(R.string.description)
                    } else {
                        SPInputComponent.EMPTY_STRING
                    }
                }

                layoutBinding.textInput.doOnTextChanged { text, _, _, _ ->
                    itemBinding.tfDropdown.labelText = text.toString()
                    itemBinding.tfDropdown.text = text.toString()
                }
            }

            return layoutBinding.root
        }

    }

}