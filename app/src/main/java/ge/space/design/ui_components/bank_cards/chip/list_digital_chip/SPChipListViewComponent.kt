package ge.space.design.ui_components.bank_cards.chip.list_digital_chip

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemDefaultChipItemShowcaseBinding
import com.example.spacedesignsystem.databinding.SpItemDigitalChipItemShowcaseBinding
import com.example.spacedesignsystem.databinding.SpItemSelectableChipItemTitleShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutListDigitalChipShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.bank_cards.chip.selectable_chip_item.SPDigitalChipItem

class SPChipListViewComponent : SPShowCaseComponent {

    override fun getNameResId(): Int =
        R.string.component_chip_list

    override fun getDescriptionResId(): Int =
        R.string.component_chip_list_description

    override fun getComponentClass(): Class<*>? = Factory::class.java

    class Factory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutListDigitalChipShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            var selectedUsdItem: SPDigitalChipItem? = null
            var selectedGeItem: SPDigitalChipItem? = null
            var selectedEurItem: SPDigitalChipItem? = null

            val titleGeBinding = SpItemSelectableChipItemTitleShowcaseBinding.inflate(
                environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                binding.digitalChipLayout,
                true
            )
            titleGeBinding.itemTitle.text = environment.context.getString(
                R.string.title_ge
            )

            SPChipListStyles.geList.forEach { chip ->
                val itemBinding = SpItemDigitalChipItemShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.digitalChipLayout,
                    true
                )

                with(itemBinding.digitalChipItem) {
                    chipBackground = chip.chipBackground
                    text = chip.text
                    currency = chip.currency
                    itemEnabled = chip.enabled

                    setOnSelect { selected ->
                        selectedGeItem = if (selected) {
                            selectedGeItem?.toggle()
                            this
                        } else {
                            null
                        }
                    }
                }
            }

            val titleUsdBinding = SpItemSelectableChipItemTitleShowcaseBinding.inflate(
                environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                binding.digitalChipLayout,
                true
            )
            titleUsdBinding.itemTitle.text = environment.context.getString(
                R.string.title_usd
            )

            SPChipListStyles.usdList.forEach { chip ->
                val itemBinding = SpItemDigitalChipItemShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.digitalChipLayout,
                    true
                )

                with(itemBinding.digitalChipItem) {
                    chipBackground = chip.chipBackground
                    text = chip.text
                    currency = chip.currency
                    itemEnabled = chip.enabled

                    setOnSelect { selected ->
                        selectedUsdItem = if (selected) {
                            selectedUsdItem?.toggle()
                            this
                        } else {
                            null
                        }
                    }
                }
            }

            val titleEurBinding = SpItemSelectableChipItemTitleShowcaseBinding.inflate(
                environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                binding.digitalChipLayout,
                true
            )
            titleEurBinding.itemTitle.text = environment.context.getString(
                R.string.title_eur
            )

            SPChipListStyles.eurList.forEach { chip ->
                val itemBinding = SpItemDigitalChipItemShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.digitalChipLayout,
                    true
                )

                with(itemBinding.digitalChipItem) {
                    chipBackground = chip.chipBackground
                    text = chip.text
                    currency = chip.currency
                    itemEnabled = chip.enabled

                    setOnSelect { selected ->
                        selectedEurItem = if (selected) {
                            selectedEurItem?.toggle()
                            this
                        } else {
                            null
                        }
                    }
                }
            }

            val titleDefaultBinding = SpItemSelectableChipItemTitleShowcaseBinding.inflate(
                environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                binding.digitalChipLayout,
                true
            )
            titleDefaultBinding.itemTitle.text = environment.context.getString(
                R.string.title_default_items
            )

            SPChipListStyles.defaultList.forEach { chip ->
                val itemBinding = SpItemDefaultChipItemShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.digitalChipLayout,
                    true
                )

                with(itemBinding.chipItem) {
                    chipData = chip.defaultChipData
                }
            }

            return binding.root
        }
    }
}