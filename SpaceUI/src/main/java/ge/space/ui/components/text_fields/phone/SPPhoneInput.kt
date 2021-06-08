package ge.space.ui.components.text_fields.phone

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.base.SPBaseTextField
import ge.space.ui.util.extension.handleAttributeAction
/**
 * Field view extended from [SPBaseTextField] that allows
 * to change EditorAction and sets the masl.
 *
 */
class SPPhoneInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseTextField(context, attrs, defStyleAttr) {

    init {
        binding.etInputField.mask = resources.getString(R.string.phone_mask)
    }

    /** @param listener - its onFocusChange() method will be called before performing MaskedEditText operations,
     * related to this event.
     */
    override fun setOnFocusChangeListener(listener: OnFocusChangeListener) {
        binding.etInputField.onFocusChangeListener = listener
    }

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        with(binding.etInputField) {
            setImeActionEnabled(true)
            onActionListener = listener
        }
    }


}