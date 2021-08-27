package ge.space.ui.util.view_factory.component_type.chip.primary

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPPrimaryChip
import ge.space.ui.util.view_factory.component_type.SPViewComponentType
import ge.space.ui.util.view_factory.SPViewData

class SPPrimaryChipIconImpl(context: Context) :
    SPViewComponentType<SPViewData.SPrimaryChipData>(context) {

    override fun create(type: SPViewData.SPrimaryChipData): SPPrimaryChip {
        return SPPrimaryChip(context).apply {
            style(type.styleRes)
            chipHeight = type.chipHeight
            chipWidth = type.chipWidth
        }
    }
}