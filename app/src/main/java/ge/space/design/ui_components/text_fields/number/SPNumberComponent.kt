package ge.space.design.ui_components.text_fields.number

import android.widget.RadioButton
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldNumberShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.input.currency.SPTextFieldNumber
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

                cbDisable.setOnCheckedChangeListener { _, isChecked ->
                    tfNumber.isEnabled = !isChecked
                    tfNumberSecond.isEnabled = !isChecked
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

                tfNumber.onFocusChangeListener = { checked ->
                    if (checked) {
                        onFocusOnTextFields(tfNumber, tfNumberSecond, rbFirst, rbSecond)
                    }
                }

                tfNumberSecond.onFocusChangeListener = { checked ->
                    if (checked) {
                        onFocusOnTextFields(tfNumberSecond, tfNumber, rbSecond, rbFirst)
                    }
                }

                rbFirst.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        rbSecond.isChecked = false
                        tfNumber.focus()
                    } else {
                        tfNumber.isActive = false
                    }
                }

                rbSecond.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        rbFirst.isChecked = false
                        tfNumberSecond.focus()
                    } else {
                        tfNumberSecond.isActive = false
                    }
                }

                rbFirst.isChecked = true
                radioGroup.check(R.id.gel)
            }
            return binding.root
        }

        private fun onFocusOnTextFields(
            tfFocused: SPTextFieldNumber,
            tfUnfocused: SPTextFieldNumber,
            rbFocused: RadioButton,
            rbUnfocused: RadioButton
        ) {
            tfFocused.focus()
            tfUnfocused.isActive = false
            rbUnfocused.isChecked = false
            rbFocused.isChecked = true
        }

        private fun SpLayoutTextFieldNumberShowcaseBinding.selectCurrency(currency: String) {
            tfNumber.currency = currency
            tfNumberSecond.currency = currency
        }
    }

    companion object {
        private const val EURO = "€"
        private const val GEL  = "₾"
        private const val UZS  = "UZS"
        private const val BIG_DECIMAL_VALUE  = "120.0"
    }
}
