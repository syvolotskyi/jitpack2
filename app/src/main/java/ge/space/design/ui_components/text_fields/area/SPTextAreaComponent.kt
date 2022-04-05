package ge.space.design.ui_components.text_fields.area

import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextAreaShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.util.extension.EMPTY_TEXT

class SPTextAreaComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.textArea

    override fun getDescriptionResId(): Int = R.string.textArea_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTextAreaShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            layoutBinding.cbMandatory.setOnCheckedChangeListener { _, isChecked ->
                layoutBinding.textArea.inputMandatory = isChecked
            }

            layoutBinding.cbDisable.setOnCheckedChangeListener { _, isChecked ->
                layoutBinding.textArea.isEnabled = !isChecked
            }

            layoutBinding.cbEnableDescription.setOnCheckedChangeListener { _, isChecked ->
                layoutBinding.textArea.descriptionText = if (isChecked) {
                    layoutBinding.textArea.resources.getString(R.string.description)
                } else {
                    EMPTY_TEXT
                }
            }

            layoutBinding.cbInfoButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked)
                layoutBinding.textArea.setInfoListener {
                    Toast.makeText(
                        layoutBinding.textArea.context,
                        "Info icon was clicked", Toast.LENGTH_SHORT
                    ).show()
                } else layoutBinding.textArea.showInfoButton = false
            }

            layoutBinding.textInput.doOnTextChanged { text, _, _, _ ->
                layoutBinding.textArea.labelText = text.toString()
            }

            return layoutBinding.root
        }

    }
}