package ge.space.design.ui_components.text_fields.number

import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldNumberShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.input.utils.extension.doOnTextChanged

class SPNumberComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.number_input

    override fun getDescriptionResId(): Int = R.string.number_input_desc

    override fun getComponentClass(): Class<*> = FactorySP::class.java
    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val binding =
                SpLayoutTextFieldNumberShowcaseBinding.inflate(environmentSP.requireLayoutInflater())
            with(binding) {
                radioGroup.setOnCheckedChangeListener { group, checkedId ->
                    when (checkedId) {
                        R.id.eur -> tfNumber.currency = "€"
                        R.id.gel -> tfNumber.currency = "₾"
                        R.id.uzs -> tfNumber.currency = "UZS"
                    }
                }
                radioGroup.check(R.id.gel)

                cbDisable.setOnCheckedChangeListener { _, isChecked ->
                    tfNumber.isEnabled = !isChecked
                }

                tfNumber.doOnTextChanged { text, start, before, count ->
                   with(text.toString()) {
                       tfNumber.isDistractive =
                           isNotEmpty() && toDouble() >= DISTRACTIVE_SUM
                   }
                }
            }
            return binding.root
        }

    }

    companion object {
        const val DISTRACTIVE_SUM: Double = 120.0
    }
}