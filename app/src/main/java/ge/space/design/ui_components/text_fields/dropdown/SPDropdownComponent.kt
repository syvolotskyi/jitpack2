package ge.space.design.ui_components.text_fields.dropdown

import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpDropdownShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.text_fields.input.SPInputComponent

class SPDropdownComponent : SPShowCaseComponent {
    override fun getNameResId(): Int = R.string.dropdown

    override fun getDescriptionResId(): Int = R.string.dropdown_desc

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding = SpDropdownShowcaseBinding.inflate(environmentSP.requireLayoutInflater())

            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.withIcon -> {
                        binding.tfDropdown.isIconVisible = true
                        binding.tfDropdown.src = R.drawable.ic_card_example
                    }
                    R.id.noIcon ->
                        binding.tfDropdown.isIconVisible = false
                }
            }

            binding.cbMandatory.setOnCheckedChangeListener { _, isChecked ->
                binding.tfDropdown.inputMandatory = isChecked
            }

            binding.cbDisable.setOnCheckedChangeListener { _, isChecked ->
                binding.tfDropdown.isEnabled = !isChecked
            }

            binding.cbDescription.setOnCheckedChangeListener { _, isChecked ->
                binding.tfDropdown.descriptionText = if (isChecked) {
                    binding.tfDropdown.resources.getString(R.string.description)
                } else {
                    SPInputComponent.EMPTY_STRING
                }
            }

            binding.labelTextInput.doOnTextChanged { text, _, _, _ ->
                binding.tfDropdown.labelText = text.toString()
                binding.tfDropdown.text = text.toString()

            }
            return binding.root
        }

    }

}