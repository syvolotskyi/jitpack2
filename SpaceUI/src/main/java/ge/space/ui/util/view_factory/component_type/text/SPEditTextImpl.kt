package ge.space.ui.util.view_factory.component_type.text

import android.content.Context
import android.view.Gravity
import android.widget.EditText
import ge.space.extensions.setTextStyle
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPEditTextImpl(context: Context) : SPViewImpl<SPViewData.SPEditTextData>(context) {
    override fun create(type: SPViewData.SPEditTextData): EditText {
        return EditText(context).apply {
            gravity = type.params?.gravity ?: Gravity.LEFT
            inputType = type.inputType
            background = null
            type.params?.let {
                it.height?.let { this.height = it }
                it.width?.let { this.width = it }
                setPadding(it.paddingStart, it.paddingTop, it.paddingEnd, it.paddingBottom)
            }
            type.style?.let { setTextStyle(it) }
            type.lines?.let { maxLines = it }
            type.hint?.let { hint = it }
        }
    }
}