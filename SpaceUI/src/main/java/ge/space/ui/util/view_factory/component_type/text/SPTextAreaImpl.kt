package ge.space.ui.util.view_factory.component_type.text

import android.content.Context
import android.view.Gravity
import android.widget.EditText
import ge.space.extensions.setTextStyle
import ge.space.ui.components.text_fields.input.base.SPTextAreaView
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPTextAreaImpl(context:Context)  : SPViewImpl<SPViewData.SPTextAreaData>(context) {
    override fun create(type: SPViewData.SPTextAreaData): SPTextAreaView {
        return SPTextAreaView(context).apply {
            background = null

            setPadding(
                type.params?.paddingStart ?: 0,
                type.params?.paddingTop ?: 0,
                type.params?.paddingEnd ?: 0,
                type.params?.paddingBottom ?: 0
            )
        }
    }
}