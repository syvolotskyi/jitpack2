package ge.space.design.ui_components.text_fields.dropdown

import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentActivity
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsDropdownShowcaseBinding
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
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnBindInterface
import ge.space.ui.util.extension.EMPTY_STRING
import ge.space.ui.util.view_factory.SPViewData
import java.util.*

class SPDropdownComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.dropdown

    override fun getDescriptionResId(): Int = R.string.dropdown_desc

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTextFieldsDropdownShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )

            val dropdowns = mutableListOf<SPTextFieldBaseView<SpTextFieldDropdownBinding>>()
            val simpleDropdown = createDropdownProgrammatically(
                layoutBinding.tfDropdownFrame,
                environmentSP.requireFragmentActivity()
            )
            layoutBinding.tfDropdownFrame.addView(simpleDropdown)

            dropdowns.add(
                createDropdownFromXml(
                    layoutBinding.textFieldDropdown,
                    environmentSP.requireFragmentActivity()
                )
            )

            dropdowns.add(simpleDropdown)

            layoutBinding.cbMandatory.setOnCheckedChangeListener { _, isChecked ->
                dropdowns.forEach { it.inputMandatory = isChecked }
            }

            layoutBinding.cbDisable.setOnCheckedChangeListener { _, isChecked ->
                dropdowns.forEach { it.isEnabled = !isChecked }
            }

            layoutBinding.cbEnableDescription.setOnCheckedChangeListener { _, isChecked ->
                dropdowns.forEach {
                    it.descriptionText = if (isChecked) {
                        layoutBinding.textFieldDropdown.resources.getString(R.string.description)
                    } else {
                        EMPTY_STRING
                    }
                }
            }

            layoutBinding.textInput.doOnTextChanged { text, _, _, _ ->
                dropdowns.forEach {
                    it.labelText = text.toString()
                    it.text = text.toString()
                }
            }


            return layoutBinding.root
        }

        private fun createDropdownFromXml(
            view: SPTextFieldDropdown<*>,
            fragmentActivity: FragmentActivity
        ): SPTextFieldDropdown<*> {
            return SPTextFieldDropdown.SPTextFieldDropdownBuilder<SPDropdownItemModel>()
                .setStyle(R.style.SPTextField_DropdownWithIcon)
                .withView(view)
                .setDefault(
                    SPDropdownItemModel(
                        0,
                        view.context.getString(R.string.enter_you_details_here),
                        SPViewData.SPEmptyChip(SPEmptyChipStyle.White)
                    )
                )
                .setTitle(view.context.getString(R.string.enter_you_details_here))
                .setOnBindItem(SPOnBindDropdownItemModel())
                .setItems(SPTextFieldsDropdownItems.list)
                .setOnClickListener {
                    fragmentActivity.showMultipleButtonDialog(
                        SPDialogInfo(
                            view.resources.getString(R.string.selectIcon),
                            EMPTY_STRING,
                            createMultipleButtonsConfigs(
                                SPTextFieldsDropdownItems.list,
                                it
                            )
                        ),
                        SPDialogIcon.Alert(R.attr.accent_magenta)
                    )
                }
                .build(fragmentActivity)
        }


        private fun createDropdownProgrammatically(
            view: FrameLayout,
            fragmentActivity: FragmentActivity
        ): SPTextFieldDropdown<*> {
            val items = SPTextFieldsDropdownItems.list.map { it.value }
            return SPTextFieldDropdown.SPTextFieldDropdownBuilder<String>()
                .setStyle(R.style.SPTextField_Dropdown)
                .setDefault(view.context.getString(R.string.enter_you_details_here))
                .setTitle(view.context.getString(R.string.enter_you_details_here))
                .setOnBindItem(object : SPOnBindInterface<String> {
                    override fun getBindItemModel(): (SPTextFieldDropdown<String>, String) -> Unit =
                        { dropdown, item ->
                            dropdown.text = item
                        }
                })
                .setItems(items)
                .setOnClickListener {
                    fragmentActivity.showMultipleButtonDialog(
                        SPDialogInfo(
                            "Select icon",
                            "",
                            createMultipleStringsButtonsConfigs(
                                items,
                                it
                            )
                        ),
                        SPDialogIcon.Alert(R.attr.accent_magenta)
                    )
                }
                .build(fragmentActivity)
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

        private fun createMultipleStringsButtonsConfigs(
            items: List<String>,
            view: SPTextFieldDropdown<String>
        ) =
            items.map {
                SPDialogInfoHolder(
                    it,
                    SPDialogBottomVerticalButton.BottomButtonType.Default
                ) {
                    view.onSelectedItem(it)
                }
            } as ArrayList<SPDialogInfoHolder>
    }
}