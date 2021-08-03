package ge.space.ui.util.view_factory.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPPrimaryChip
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPPrimaryChipIconImpl(context: Context) :
    SPViewImpl<SPViewData.SPrimaryChip>(context) {

    override fun create(type: SPViewData.SPrimaryChip): SPPrimaryChip {
        return SPPrimaryChip(context).apply {
            size = type.chipSize
        }
    }
}