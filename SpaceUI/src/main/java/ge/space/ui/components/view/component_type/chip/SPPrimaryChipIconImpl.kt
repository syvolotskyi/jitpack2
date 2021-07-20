package ge.space.ui.components.view.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPPrimaryChip
import ge.space.ui.components.view.SPViewData

class SPPrimaryChipIconImpl(context: Context) :
    SPChipIcon<SPViewData.SPrimaryChip>(context) {

    override fun create(type: SPViewData.SPrimaryChip): SPPrimaryChip {
        return SPPrimaryChip(context).apply {
            size = type.chipSize
        }
    }
}