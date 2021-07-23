package ge.space.ui.util.view_factory.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.empty.SPEmptyChip
import ge.space.ui.util.view_factory.component_type.SPViewComponentType
import ge.space.ui.util.view_factory.SPViewData

class SPEmptyChipIconImpl(context: Context) :
    SPViewComponentType<SPViewData.SPEmptyChipData>(context) {

    override fun create(type: SPViewData.SPEmptyChipData): SPEmptyChip {
        return SPEmptyChip(context).apply {
            size = type.chipSize
            style = type.chipStyle
        }
    }
}