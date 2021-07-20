package ge.space.ui.components.view.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.empty.SPEmptyChip
import ge.space.ui.components.view.SPViewData

class SPEmptyChipIconImpl(context: Context) :
    SPChipIcon<SPViewData.SPEmptyChip>(context) {

    override fun create(type: SPViewData.SPEmptyChip): SPEmptyChip {
        return SPEmptyChip(context).apply {
            style = type.chipStyle
        }
    }
}