package ge.space.design.ui_components.text_fields.search

import android.content.Context
import android.text.InputType
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsSearchShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.input.base.*
import ge.space.ui.components.text_fields.input.utils.extension.doOnTextChanged
import ge.space.ui.util.extension.EMPTY_TEXT

class SPSearchComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.search_text_input

    override fun getDescriptionResId(): Int = R.string.search_text_input_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTextFieldsSearchShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            val context = layoutBinding.simpleInput.context


            return layoutBinding.root
        }
    }
}