package ge.space.design.ui_components.text_fields.number

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldNumberXmlShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPNumberXMLComponent: ShowCaseComponent {

    override fun getNameResId(): Int = R.string.number_input_xml

    override fun getDescriptionResId(): Int = R.string.number_input_xml_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {

        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutTextFieldNumberXmlShowcaseBinding.inflate(environment.requireLayoutInflater())
            return binding.root
        }

    }

}