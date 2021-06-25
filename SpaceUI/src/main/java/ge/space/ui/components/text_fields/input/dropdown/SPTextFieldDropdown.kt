package ge.space.ui.components.text_fields.input.dropdown

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class SPTextFieldDropdown @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SPBaseTextFieldDropdown<SPDropdownItemModel>(context, attrs, defStyleAttr) {

    override fun bindViewValue(view: TextView, item: SPDropdownItemModel) {
        view.text = item.value
    }
}