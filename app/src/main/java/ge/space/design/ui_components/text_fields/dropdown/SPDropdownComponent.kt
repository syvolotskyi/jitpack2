package ge.space.design.ui_components.text_fields.dropdown

import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentActivity
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsDropdownShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.text_fields.dropdown.SPTextFieldsDropdownItems.getDefaultLangItem
import ge.space.design.ui_components.text_fields.dropdown.SPTextFieldsDropdownItems.getLanguagesList
import ge.space.spaceui.databinding.SpLangItemLayoutBinding
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import ge.space.ui.components.controls.radio.list_item.extentions.setData
import ge.space.ui.components.dialogs.data.SPDialogIcon
import ge.space.ui.components.dialogs.data.SPDialogInfo
import ge.space.ui.components.dialogs.data.SPDialogInfoHolder
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import ge.space.ui.components.dialogs.showMultipleButtonDialog
import ge.space.ui.components.dropdowns.SPBottomSheetFragment
import ge.space.ui.components.dropdowns.builder.SPBottomSheetBuilder
import ge.space.ui.components.dropdowns.data.SPOnBottomSheetAdapter
import ge.space.ui.components.dropdowns.strategy.SPFragmentSheetStrategy
import ge.space.ui.components.dropdowns.strategy.SPListSheetStrategy
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnDropdownBind
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnDropdownItemModelBind
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView
import ge.space.ui.util.view_factory.component_type.chip.empty.SPDefaultEmptyChipData
import java.util.*

class SPDropdownComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.dropdown

    override fun getDescriptionResId(): Int = R.string.dropdown_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTextFieldsDropdownShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            val dropdowns = mutableListOf<SPTextFieldInput>()
            val simpleDropdown = createDropdownProgrammatically(
                layoutBinding.tfDropdownFrame,
                environment.requireFragmentActivity()
            )
            layoutBinding.tfDropdownFrame.addView(simpleDropdown)

            dropdowns.add(
                createDropdownFromXml(
                    layoutBinding.textFieldDropdown,
                    environment.requireFragmentActivity()
                )
            )

            dropdowns.add(
                createLanguageDropdownFromXml(
                    layoutBinding.textFieldDropdownLanguage,
                    environment.requireFragmentActivity()
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
                        EMPTY_TEXT
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

            val adapter =
                SPOnBottomSheetAdapter<SpLangItemLayoutBinding, SPDropdownItemModel>()
                    .setup {
                        onCreate { _ ->
                            SpLangItemLayoutBinding.inflate(LayoutInflater.from(view.context))
                        }
                        onBind { binding, item, _ ->
                            binding.radio.isChecked = item.isSelected
                            binding.radio.setData(
                                item.item.value,
                                item.item.iconData?.createView(view.context)
                            )
                        }
                    }

            return SPTextFieldDropdown.SPTextFieldDropdownBuilder<SPDropdownItemModel>(
                fragmentActivity
            )
                .setStyle(R.style.SPTextField_DropdownWithIcon)
                .withView(view)
                .setDefault(
                    SPDropdownItemModel(
                        0,
                        view.context.getString(R.string.enter_you_details_here),
                        SPDefaultEmptyChipData.getSmallEmptyChipData(
                            view.context,
                            SPEmptyChipStyle.Dark
                        )
                    )
                )
                .setTitle(view.context.getString(R.string.enter_you_details_here))
                .setOnBindDropdownItem(SPOnDropdownItemModelBind())
                .setItems(SPTextFieldsDropdownItems.getList(view.context))
                .setBottomSheetAdapter(adapter)
                .build(fragmentActivity)
        }


        private fun createLanguageDropdownFromXml(
            view: SPTextFieldDropdown<*>,
            fragmentActivity: FragmentActivity
        ): SPTextFieldDropdown<*> {

            val adapter =
                SPOnBottomSheetAdapter<SpLangItemLayoutBinding, SPDropdownItemModel>().setup {
                    onCreate { _ ->
                        SpLangItemLayoutBinding.inflate(LayoutInflater.from(view.context))
                    }
                    onBind { binding, item, _ ->
                        binding.radio.isChecked = item.isSelected
                        binding.radio.setData(
                            item.item.value,
                            item.item.iconData?.createView(view.context)
                        )
                    }
                }

            return SPTextFieldDropdown.SPTextFieldDropdownBuilder<SPDropdownItemModel>(fragmentActivity)
                .setStyle(R.style.SPTextField_DropdownWithIcon)
                .withView(view)
                .setDefault(getDefaultLangItem(view.context))
                .setTitle(view.context.getString(R.string.selectLanguage))
                .setOnBindDropdownItem(SPOnLangItemModelBind())
                .setItems(getLanguagesList(view.context))
                .setBottomSheetAdapter(adapter)
                .build(fragmentActivity)
        }

        private fun createDropdownProgrammatically(
            view: FrameLayout,
            fragmentActivity: FragmentActivity
        ): SPTextFieldDropdown<*> {
            val items = SPTextFieldsDropdownItems.getList(view.context).map { it.value }
            return SPTextFieldDropdown.SPTextFieldDropdownBuilder<String>(fragmentActivity)
                .setStyle(R.style.SPTextField_Dropdown)
                .setDefault(view.context.getString(R.string.enter_you_details_here))
                .setTitle(view.context.getString(R.string.enter_you_details_here))
                .setOnBindDropdownItem(object : SPOnDropdownBind<String> {
                    override fun getBindItemModel(): (SPTextFieldDropdown<String>, String) -> Unit =
                        { dropdown, item -> dropdown.text = item }
                })
                .setItems(items)
                .setOnClickListener {
                    SPBottomSheetBuilder<String>(fragmentActivity)
                        .setTitle(view.context.getString(R.string.enter_you_details_here))
                        .setStrategy(
                            SPFragmentSheetStrategy(
                                SPExampleFragment()
                            )
                        )
                        .setResultListener {
                            Toast.makeText(view.context, "result", Toast.LENGTH_SHORT).show()
                        }
                        .build()
                        .apply {
                            show(
                                fragmentActivity.supportFragmentManager,
                                SPBottomSheetFragment.DIALOG_FRAGMENT_TAG
                            )
                        }
                }
                .build(fragmentActivity)
        }
    }
}