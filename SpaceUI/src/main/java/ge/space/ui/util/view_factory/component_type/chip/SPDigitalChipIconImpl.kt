package ge.space.ui.util.view_factory.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPDigitalChip
import ge.space.ui.util.view_factory.SPViewData

class SPDigitalChipIconImpl(context: Context) :
    SPChipIcon<SPViewData.SPDigitalChip>(context) {

    override fun create(type: SPViewData.SPDigitalChip): SPDigitalChip {
        return SPDigitalChip(context).apply {
            cardBackground = type.gradient
        }
    }
}