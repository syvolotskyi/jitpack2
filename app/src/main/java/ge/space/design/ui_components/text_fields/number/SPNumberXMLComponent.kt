package ge.space.design.ui_components.text_fields.number

import android.util.Log
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldNumberXmlShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.util.extension.onChange

class SPNumberXMLComponent: ShowCaseComponent {

    override fun getNameResId(): Int = R.string.number_input_xml

    override fun getDescriptionResId(): Int = R.string.number_input_xml_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {

        override fun create(environment: SPShowCaseEnvironment): Any {
            SpLayoutTextFieldNumberXmlShowcaseBinding.inflate(environment.requireLayoutInflater()).apply {

                // You can set Formatter
                // tfNumber2.setFormatter(SPDefaultFormatterFactory.produceInputAmountFormatter(tfNumber2.maxLength))

                tfNumber.contentInputView.onChange {
                    Log.d("Input:",it)
                }

                tfNumber2.contentInputView.onChange {
                    Log.d("Input:",it)
                }

                tfNumber3.contentInputView.onChange {
                    Log.d("Input:",it)
                }

                return root
            }
        }

    }

}