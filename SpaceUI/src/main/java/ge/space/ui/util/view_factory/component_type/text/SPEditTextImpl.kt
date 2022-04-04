package ge.space.ui.util.view_factory.component_type.text

import android.content.Context
import android.view.Gravity
import android.widget.EditText
import ge.space.ui.util.extension.setTextStyle
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
            }
            setPadding(
                type.params?.paddingStart ?: 0,
                type.params?.paddingTop ?: 0,
                type.params?.paddingEnd ?: 0,
                type.params?.paddingBottom ?: 0
            )
            type.style?.let { setTextStyle(it) }
            type.lines?.let { setLines(it)}
            type.hint?.let { hint = it }
        }
    }
}