package ge.space.design.ui_components.text_fields.dropdown

import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemTextFieldsDropdownShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsListShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.text_fields.input.SPInputComponent
import ge.space.spaceui.databinding.SpTextFieldDropdownBinding
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import ge.space.ui.components.dialogs.data.SPDialogIcon
import ge.space.ui.components.dialogs.data.SPDialogInfo
import ge.space.ui.components.dialogs.data.SPDialogInfoHolder
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import ge.space.ui.components.dialogs.showMultipleButtonDialog
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnBindDropdownItemModel
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.util.view_factory.SPViewData

class SPDropdownComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.dropdown

    override fun getDescriptionResId(): Int = R.string.dropdown_desc

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTextFieldsListShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )

            val dropdowns = mutableListOf<SPTextFieldBaseView<SpTextFieldDropdownBinding>>()

            SPTextFieldsDropdownStyles.list.onEach { fieldSample ->

                val resId = fieldSample.resId

                val itemBinding = SpItemTextFieldsDropdownShowcaseBinding.inflate(
                    environmentSP.requireThemedLayoutInflater(resId),
                    layoutBinding.fieldsLayout,
                    true
                )

                val context = itemBinding.tfDropdown.context

                val dropdown =
                    SPTextFieldDropdown.SPTextFieldDropdownBuilder<SPDropdownItemModel>()
                        .setStyle(fieldSample.resId)
                        .setDefault(
                            SPDropdownItemModel(
                                0,
                                context.getString(R.string.enter_you_details_here),
                                SPViewData.SPEmptyChip(SPEmptyChipStyle.White)
                            )
                        )
                        .setTitle(context.getString(R.string.enter_you_details_here))
                        .setOnBindItem(SPOnBindDropdownItemModel())
                        .setItems(SPTextFieldsDropdownItems.list)
                        .setOnClickListener {
                            environmentSP.requireFragmentActivity().showMultipleButtonDialog(
                                SPDialogInfo(
                                    layoutBinding.textInput.text.toString(),
                                    "Select icon",
                                    createMultipleButtonsConfigs(
                                        SPTextFieldsDropdownItems.list,
                                        it
                                    )
                                ),
                                SPDialogIcon.Alert(R.attr.accent_magenta)
                            )
                        }
                        .build(environmentSP.requireFragmentActivity())

                dropdowns.add(dropdown)
                itemBinding.tfDropdown.addView(dropdown)


                with(itemBinding.buttonName) {
                    val resName = resources.getResourceEntryName(resId)
                    text = resName.substringAfter(".", resName)
                }

                itemBinding.cbMandatory.setOnCheckedChangeListener { _, isChecked ->
                    dropdown.inputMandatory = isChecked
                }

                itemBinding.cbDisable.setOnCheckedChangeListener { _, isChecked ->
                    dropdown.isEnabled = !isChecked
                }

                itemBinding.cbDescription.setOnCheckedChangeListener { _, isChecked ->
                    dropdown.descriptionText = if (isChecked) {
                        itemBinding.tfDropdown.resources.getString(R.string.description)
                    } else {
                        SPInputComponent.EMPTY_STRING
                    }
                }

                layoutBinding.textInput.doOnTextChanged { text, _, _, _ ->
                    dropdown.labelText = text.toString()
                    dropdown.text = text.toString()
                }

            }

            return layoutBinding.root
        }

        private fun createMultipleButtonsConfigs(
            items: List<SPDropdownItemModel>,
            view: SPTextFieldDropdown<SPDropdownItemModel>
        ) =
            items.map {
                SPDialogInfoHolder(
                    it.value,
                    SPDialogBottomVerticalButton.BottomButtonType.Default
                ) {
                    view.onSelectedItem(it)
                }
            } as ArrayList<SPDialogInfoHolder>
    }

}