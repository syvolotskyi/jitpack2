package ge.space.ui.util.view_factory.component_type.tooltip

import android.content.Context
import ge.space.ui.components.tooltips.SPTooltipView
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPTooltipViewDataImpl (context: Context) : SPViewImpl<SPViewData.SPTooltipData>(context) {
    override fun create(type: SPViewData.SPTooltipData): SPTooltipView {
        return SPTooltipView(context).apply {
            text = type.text
            arrowDirection = type.arrowDirection
        }
    }
}