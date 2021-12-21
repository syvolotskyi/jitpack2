package ge.space.ui.util.view_factory.component_type.text

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import ge.space.extensions.setTextStyle
import ge.space.spaceui.databinding.SpTextFieldPhoneLayoutBinding
import ge.space.ui.components.text_fields.input.utils.masked_helper.SPEditTextMasked
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPMaskedEditTextImpl(context: Context) : SPViewImpl<SPViewData.SPMaskedEditTextData>(context) {
    override fun create(type: SPViewData.SPMaskedEditTextData): SPEditTextMasked {
       val  editText  = SpTextFieldPhoneLayoutBinding.inflate(LayoutInflater.from(context))
        return  editText.etInputField.apply {
            mask = type.mask
            gravity = type.params?.gravity ?: Gravity.LEFT
            type.hint?.let { hint = it }

            background = null
            type.params?.let {
                it.height?.let { this.height = it }
                it.width?.let { this.width = it }
                setPadding(it.paddingStart, it.paddingTop, it.paddingEnd, it.paddingBottom)
            }
            type.style?.let { setTextStyle(it) }
        }
    }
}