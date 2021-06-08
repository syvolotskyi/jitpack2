package ge.space.ui.components.text_fields.phone

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.base.SPBaseTextField
import ge.space.ui.util.extension.handleAttributeAction

class SPPhoneInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseTextField(context, attrs, defStyleAttr) {

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextInputPassword,
            defStyleAttr
        ) {
            getString(R.styleable.SPTextInputPassword_sp_mask).orEmpty().handleAttributeAction(
                SPBaseView.EMPTY_TEXT
            ) {
                binding.etInputField.mask = it
            }

            getString(R.styleable.SPBaseTextView_sp_descText).orEmpty().handleAttributeAction(
                SPBaseView.EMPTY_TEXT
            ) {
                descText = it
            }
        }
    }
}