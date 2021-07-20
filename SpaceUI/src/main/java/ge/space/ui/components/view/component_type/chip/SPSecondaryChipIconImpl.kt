package ge.space.ui.components.view.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPSecondaryChip
import ge.space.ui.components.view.SPViewData

class SPSecondaryChipIconImpl(context: Context) :
    SPChipIcon<SPViewData.SPSecondaryChip>(context) {

    override fun create(type: SPViewData.SPSecondaryChip): SPSecondaryChip {
        return SPSecondaryChip(context).apply {
            bankLogoUrl = type.bankLogoUrl
        }
    }
}