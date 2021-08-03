package ge.space.ui.util.view_factory.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.empty.SPEmptyChip
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPEmptyChipIconImpl(context: Context) :
    SPViewImpl<SPViewData.SPEmptyChip>(context) {

    override fun create(type: SPViewData.SPEmptyChip): SPEmptyChip {
        return SPEmptyChip(context).apply {
            style = type.chipStyle
        }
    }
}