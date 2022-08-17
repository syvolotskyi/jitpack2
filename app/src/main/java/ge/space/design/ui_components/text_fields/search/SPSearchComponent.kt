package ge.space.design.ui_components.text_fields.search

import android.content.Context
import android.text.InputType
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsSearchShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_fields.input.base.*
import ge.space.ui.components.text_fields.input.search.SPSearchView
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


            layoutBinding.simpleInput.setupSearchView("first input")
            layoutBinding.simpleInputElevated.setupSearchView("second input")
            layoutBinding.simpleInputFiltered.setupSearchView("third input")


            return layoutBinding.root
        }

        private fun SPSearchView.setupSearchView(key: String) {
            focusChangeListener = {
                makeText(context, "Focused $key", LENGTH_SHORT).show()
            }
            cancelButtonClickListener = {
                makeText(context, "Canceled $key", LENGTH_SHORT).show()
            }
            onSearchClickListener = {
                if (it == "8888")
                    makeText(context, "Entered 8888  $key", LENGTH_SHORT).show()
            }
            settingClickListener = { isChecked ->
                makeText(context, "Settings clicked $isChecked $key", LENGTH_SHORT).show()
            }
        }
    }
}