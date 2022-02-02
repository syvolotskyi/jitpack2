package ge.space.design.ui_components.text_fields.number

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldNumberShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.input.base.SPEndViewType
import ge.space.ui.components.text_fields.input.base.setupAmountDecimalInput
import ge.space.ui.components.text_fields.input.base.setupEndViewByType
import ge.space.ui.components.text_fields.input.base.setupNumberInput
import ge.space.ui.components.text_fields.input.utils.extension.doOnTextChanged
import java.math.BigDecimal

class SPNumberComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.number_input

    override fun getDescriptionResId(): Int = R.string.number_input_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {

        private val distractiveSum: BigDecimal = BigDecimal(BIG_DECIMAL_VALUE)

        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding =
                SpLayoutTextFieldNumberShowcaseBinding.inflate(environment.requireLayoutInflater())
            with(binding) {
                radioGroup.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.eur -> selectCurrency(EURO)
                        R.id.gel -> selectCurrency(GEL)
                        R.id.uzs -> selectCurrency(UZS)
                    }
                }

                tfNumber.setupNumberInput(EURO)
                tfNumberSecond.setupAmountDecimalInput(EURO)
                tfNumberThird.setupNumberInput(EURO)

                cbDisable.setOnCheckedChangeListener { _, isChecked ->
                   tfNumber.isEnabled = !isChecked
                    tfNumberSecond.isEnabled = !isChecked
                    tfNumberThird.isEnabled = !isChecked
                }

                tfNumber.doOnTextChanged { text, _, _, _ ->
                    with(text.toString()) {
                        try {
                            tfNumber.isDistractive =
                                isNotEmpty() && toBigDecimal() >= distractiveSum

                        } catch (e: NumberFormatException) {
                            Toast.makeText(
                                tfNumber.context,
                                R.string.pleaseEnterTheRightValue,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
            return binding.root
        }

        private fun SpLayoutTextFieldNumberShowcaseBinding.selectCurrency(currency: String) {
            tfNumber.setupEndViewByType(SPEndViewType.SPCurrencyViewType(currency))
            tfNumberSecond.setupEndViewByType(SPEndViewType.SPCurrencyViewType(currency))
        }
    }

    companion object {
        private const val EURO = "€"
        private const val GEL  = "₾"
        private const val UZS  = "UZS"
        private const val BIG_DECIMAL_VALUE  = "120.0"
    }
}
