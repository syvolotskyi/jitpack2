package ge.space.ui.util.view_factory.component_type.text

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import ge.space.extensions.setTextStyle
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPTextInitialsImpl(context: Context) : SPViewImpl<SPViewData.SPTextInitialsData>(context) {
    override fun create(type: SPViewData.SPTextInitialsData): View {
        return TextView(context).apply {
            text = type.initials
            gravity = Gravity.CENTER
            type.textStyle?.let { setTextStyle(it) }
        }
    }
}