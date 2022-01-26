package ge.space.design.ui_components.text_fields.number

import android.widget.RadioButton
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldNumberShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldNumberXmlShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.input.base.SPEndViewType
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.base.setupEndViewByType
import ge.space.ui.components.text_fields.input.base.setupNumberInput
import ge.space.ui.components.text_fields.input.utils.extension.doOnTextChanged
import java.math.BigDecimal

class SPNumberXMLComponent: ShowCaseComponent {

    override fun getNameResId(): Int = R.string.number_input

    override fun getDescriptionResId(): Int = R.string.number_input_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {

        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutTextFieldNumberXmlShowcaseBinding.inflate(environment.requireLayoutInflater())
            return binding.root
        }

    }

}