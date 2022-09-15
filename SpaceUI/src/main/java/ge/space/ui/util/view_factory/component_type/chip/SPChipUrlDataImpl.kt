package ge.space.ui.util.view_factory.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.icon.SPChipIcon
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.component_type.SPViewComponentType

class SPChipUrlDataImpl (context: Context) :
    SPViewComponentType<SPViewData.SPChipUrlData>(context) {

    override fun create(type: SPViewData.SPChipUrlData): SPChipIcon =
        SPChipIcon(context).apply {
            setViewStyle(type.styleRes)
            bigPhotoUrl = type.url
            type.params?.let {
                setPadding(it.paddingStart, it.paddingTop, it.paddingEnd, it.paddingBottom)
            }
        }
}