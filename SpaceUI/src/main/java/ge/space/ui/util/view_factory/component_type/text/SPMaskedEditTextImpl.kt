package ge.space.ui.util.view_factory.component_type.text

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldPhoneLayoutBinding
import ge.space.ui.components.text_fields.input.utils.masked_helper.SPEditTextMasked
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.setTextStyle
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPMaskedEditTextImpl(context: Context) : SPViewImpl<SPViewData.SPMaskedEditTextData>(context) {
    override fun create(type: SPViewData.SPMaskedEditTextData): SPEditTextMasked {
       val  editText  = SpTextFieldPhoneLayoutBinding.inflate(LayoutInflater.from(context))
        return  editText.etInputField.apply {
            type.textAppearance?.let { setTextStyle(it) }
            setHintTextColor(context.getColorFromAttribute(R.attr.brand_secondary))
            mask = type.mask
            gravity = type.params?.gravity ?: Gravity.LEFT
            type.hint?.let { hint = it }

            background = null
            type.params?.let {
                it.height?.let { this.height = it }
                it.width?.let { this.width = it }
            }
            setPadding(
                type.params?.paddingStart ?: 0,
                type.params?.paddingTop ?: 0,
                type.params?.paddingEnd ?: 0,
                type.params?.paddingBottom ?: 0
            )
        }
    }
}