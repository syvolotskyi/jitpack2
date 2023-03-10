package ge.space.ui.util.view_factory.component_type.statusmessage

import android.content.Context
import ge.space.ui.components.text_view.SPTextView
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPInfoTextDataImpl(context: Context) : SPViewImpl<SPViewData.SPInfoTextData>(context) {
    override fun create(type: SPViewData.SPInfoTextData): SPTextView {
        return SPTextView(context).apply {
            text = type.text
            type.textAppearance?.let { updateTextAppearance(it) }
            setPadding(
                type.params?.paddingStart ?: 0,
                type.params?.paddingTop ?: 0,
                type.params?.paddingEnd ?: 0,
                type.params?.paddingBottom ?: 0
            )
        }
    }
}