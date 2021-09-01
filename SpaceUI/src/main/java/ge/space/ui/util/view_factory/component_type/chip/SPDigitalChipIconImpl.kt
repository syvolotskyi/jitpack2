package ge.space.ui.util.view_factory.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPDigitalChip
import ge.space.ui.util.view_factory.component_type.SPViewComponentType
import ge.space.ui.util.view_factory.SPViewData

class SPDigitalChipIconImpl(context: Context) :
    SPViewComponentType<SPViewData.SPDigitalChipData>(context) {

    override fun create(type: SPViewData.SPDigitalChipData): SPDigitalChip {
        return SPDigitalChip(context).apply {
            chipHeight = type.chipHeight
            chipWidth = type.chipWidth
            cardBackground = type.gradient
        }
    }
}