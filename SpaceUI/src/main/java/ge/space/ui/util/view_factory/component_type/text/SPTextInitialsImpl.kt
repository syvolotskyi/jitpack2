package ge.space.ui.util.view_factory.component_type.text

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import ge.space.extensions.setTextStyle
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPTextInitialsImpl(context: Context) : SPViewImpl<SPViewData.SPTextData>(context) {
    override fun create(type: SPViewData.SPTextData): View {
        return TextView(context).apply {
            text = type.initials
            gravity = type.params?.gravity ?: Gravity.CENTER
            type.params?.let {
                it.height?.let { this.height = it }
                it.width?.let { this.width = it }
                setPadding(it.paddingStart, it.paddingTop, it.paddingEnd, it.paddingBottom)
            }
            type.backgroundColor?.let { this.setBackgroundColor(it) }
            type.textStyle?.let { setTextStyle(it) }
        }
    }
}