package ge.space.design.ui_components.text_fields.dropdown

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentActivity
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsDropdownShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLangItemLayoutBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.text_fields.dropdown.SPTextFieldsDropdownItems.getDefaultLangItem
import ge.space.design.ui_components.text_fields.dropdown.SPTextFieldsDropdownItems.getLanguagesList
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import ge.space.ui.components.controls.radio.list_item.extentions.setData
import ge.space.ui.components.bottomsheet.builder.SPBottomSheetBuilder
import ge.space.ui.components.bottomsheet.core.SPBottomSheetAdapter
import ge.space.ui.components.bottomsheet.strategy.SPFragmentSheetStrategy
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnDropdownBind
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.onClick
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView
import ge.space.ui.util.view_factory.component_type.chip.empty.SPDefaultEmptyChipData

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

            layoutBinding.buttonBottomSheet.onClick {
                showButtonBottomSheet(environment.requireFragmentActivity())
            }

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
            val context = view.context
            val adapter =
                SPBottomSheetAdapter<SpLangItemLayoutBinding, SPDropdownItemModel>()
                    .setup {
                        onCreate {
                            SpLangItemLayoutBinding.inflate(LayoutInflater.from(view.context))
                        }
                        onBind { binding, item, _ ->
                            binding.radio.isChecked = item.isSelected
                            binding.radio.setStartViewSize(ViewGroup.LayoutParams.WRAP_CONTENT, context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.dimen_p_38))
                            binding.radio.setData(
                                item.item.value,
                                item.item.viewData?.createView(view.context)
                            )
                        }
                    }

            return SPTextFieldDropdown.SPTextFieldDropdownBuilder<SPDropdownItemModel>(fragmentActivity)
                .setStyle(R.style.SPTextField_DropdownWithIcon)
                .withView(view)
                .setDefault(
                    SPDropdownItemModel(
                        view.context.getString(R.string.enter_you_details_here),
                        SPDefaultEmptyChipData.getSmallEmptyChipData(
                            view.context,
                            SPEmptyChipStyle.Dark
                        )
                    )
                )
                .setTitle(view.context.getString(R.string.enter_you_details_here))
                .setOnBindDropdownItem(SPOnChipItemModelBind())
                .setItems(SPTextFieldsDropdownItems.getList(view.context))
                .setBottomSheetAdapter(adapter)
                .build()
        }


        private fun createLanguageDropdownFromXml(
            view: SPTextFieldDropdown<*>,
            fragmentActivity: FragmentActivity
        ): SPTextFieldDropdown<*> {

            val adapter =
                SPBottomSheetAdapter<SpLangItemLayoutBinding, SPDropdownItemModel>().setup {
                    onCreate { SpLangItemLayoutBinding.inflate(LayoutInflater.from(view.context)) }
                    onBind { binding, item, _ ->
                        binding.radio.setData(
                            item.item.value,
                            item.item.viewData?.createView(view.context)
                        )
                        binding.radio.isChecked = item.isSelected
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
                .build()
        }

        private fun createDropdownProgrammatically(
            view: FrameLayout,
            fragmentActivity: FragmentActivity
        ): SPTextFieldDropdown<*> {
            val items = SPTextFieldsDropdownItems.getList(view.context).map { it.value }
            return SPTextFieldDropdown.SPTextFieldDropdownBuilder<String>(fragmentActivity)
                .setStyle(R.style.SPTextField_Dropdown)
                .setDefault(view.context.getString(R.string.chooseCard))
                .setTitle(view.context.getString(R.string.chooseCard))
                .setOnBindDropdownItem(object : SPOnDropdownBind<String> {
                    override fun getBindItemModel(): (SPTextFieldDropdown<String>, String) -> Unit =
                        { dropdown, item -> dropdown.text = item }
                })
                .setItems(items)
                .setOnClickListener {
                    getBottomSheetFragment(view)
                        .show(fragmentActivity)
                }
                .build()
        }

        private fun getBottomSheetFragment(
            view: FrameLayout
        ) = SPBottomSheetBuilder<String>()
            .setTitle(view.context.getString(R.string.component_bank_card))
            .setStrategy(
                SPFragmentSheetStrategy(
                    SPExampleFragment()
                )
            )
            .setResultListener {
                Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
            }
            .build()

        private fun showButtonBottomSheet(context: FragmentActivity) {
            SPBottomSheetBuilder<String>()
                .setTitle(context.getString(R.string.title_default_items))
                .setDescription(context.getString(R.string.example_text))
                .setBottomButton(context.getString(R.string.show_btn)) {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                }
                .build()
                .show(context)
        }
    }
}