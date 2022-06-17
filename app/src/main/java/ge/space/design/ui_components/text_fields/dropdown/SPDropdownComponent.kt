package ge.space.design.ui_components.text_fields.dropdown

import android.graphics.Color
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentActivity
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.ItemColorBinding
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsDropdownShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.ui.SimpleListAdapter
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.colors.Colors
import ge.space.spaceui.databinding.SpLangItemLayoutBinding
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import ge.space.ui.components.controls.radio.base.SpBaseRadioButton
import ge.space.ui.components.controls.radio.list_item.extentions.setData
import ge.space.ui.components.controls.radio.standart.SPRadioButton
import ge.space.ui.components.dialogs.data.SPDialogIcon
import ge.space.ui.components.dialogs.data.SPDialogInfo
import ge.space.ui.components.dialogs.data.SPDialogInfoHolder
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import ge.space.ui.components.dialogs.showMultipleButtonDialog
import ge.space.ui.components.dropdowns.SpBottomSheetFragment.Companion.DIALOG_FRAGMENT_TAG
import ge.space.ui.components.dropdowns.strategy.SpListSheetStrategy
import ge.space.ui.components.dropdowns.builder.SPBottomSheetBuilder
import ge.space.ui.components.dropdowns.data.SPOnBottomSheetAdapter
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnDropdownItemModelBind
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnDropdownBind
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView
import ge.space.ui.util.view_factory.component_type.chip.empty.SPDefaultEmptyChipData
import java.util.*
import kotlin.math.roundToInt

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
                    layoutBinding,
                    layoutBinding.textFieldDropdown,
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
            layoutBinding : SpLayoutTextFieldsDropdownShowcaseBinding,
            view: SPTextFieldDropdown<*>,
            fragmentActivity: FragmentActivity
        ): SPTextFieldDropdown<*> {
            return SPTextFieldDropdown.SPTextFieldDropdownBuilder<SPDropdownItemModel>()
                .setStyle(R.style.SPTextField_Dropdown)
                .withView(view)
                .setDefault(
                    SPDropdownItemModel(
                        0,
                        view.context.getString(R.string.georgian),
                        SPViewData.SPCircleImageUrlData(
                            view.context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.dimen_p_38),
                            view.context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.dimen_p_38),
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Flag_of_England.svg/2560px-Flag_of_England.svg.png",
                            view.context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.dimen_p_0_5),
                            view.context.getColorFromAttribute(ge.space.spaceui.R.attr.separator_opaque_ui)
                        )
                    )
                )
                .setTitle(view.context.getString(R.string.selectLanguage))
                .setOnBindItem(SPOnDropdownItemModelBind())
                .setItems(SPTextFieldsDropdownItems.getList(view.context))
                .setOnClickListener {
                    val list = SPTextFieldsDropdownItems.getLanguagesList(view.context)
                    val adapter =
                        SPOnBottomSheetAdapter<SpLangItemLayoutBinding, SPDropdownItemModel>(
                            list
                        ).setup {
                            onCreate { parent ->
                                SpLangItemLayoutBinding.inflate(LayoutInflater.from(view.context))
                            }
                            onBind { binding, resId, position ->
                                binding.radio2.setData(
                                    list[position].value,
                                    list[position].iconData?.createView(view.context)
                                )
                            }
                            onClick { binding, item, position ->
                                binding.radio2.isChecked = true
                                it.onSelectedItem(list[position])
                                list[position].iconData?.createView(view.context)?.let{ image ->
                                    it.startView  = image
                                }
                                layoutBinding.fl.addView(list[position].iconData?.createView(view.context))
                            }
                        }

                    SPBottomSheetBuilder(fragmentActivity)
                        .setDismissOnItemClicked(true)
                        .setTitle(view.resources.getString(R.string.selectLanguage))
                        .setIcon(R.drawable.ic_cake_24_regular)
                        .setStrategy(SpListSheetStrategy(adapter))
                        .show(DIALOG_FRAGMENT_TAG)
                }
                .build(fragmentActivity)
        }

        private fun createDropdownProgrammatically(
            view: FrameLayout,
            fragmentActivity: FragmentActivity
        ): SPTextFieldDropdown<*> {
            val items = SPTextFieldsDropdownItems.getList(view.context).map { it.value }
            return SPTextFieldDropdown.SPTextFieldDropdownBuilder<String>()
                .setStyle(R.style.SPTextField_Dropdown)
                .setDefault(view.context.getString(R.string.enter_you_details_here))
                .setTitle(view.context.getString(R.string.enter_you_details_here))
                .setOnBindItem(object : SPOnDropdownBind<String> {
                    override fun getBindItemModel(): (SPTextFieldDropdown<String>, String) -> Unit =
                        { dropdown, item -> dropdown.text = item }
                })
                .setItems(items)
                .setOnClickListener {
                    fragmentActivity.showMultipleButtonDialog(
                        SPDialogInfo(
                            view.resources.getString(R.string.selectIcon),
                            EMPTY_TEXT,
                            createMultipleStringsButtonsConfigs(
                                items,
                                it
                            )
                        ),
                        SPDialogIcon.Alert(R.attr.accent_primary_magenta)
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