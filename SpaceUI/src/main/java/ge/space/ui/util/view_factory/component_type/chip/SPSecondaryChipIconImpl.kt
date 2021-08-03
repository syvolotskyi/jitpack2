package ge.space.ui.util.view_factory.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPSecondaryChip
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPSecondaryChipIconImpl(context: Context) :
    SPViewImpl<SPViewData.SPSecondaryChip>(context) {

    override fun create(type: SPViewData.SPSecondaryChip): SPSecondaryChip {
        return SPSecondaryChip(context).apply {
            bankLogoUrl = type.bankLogoUrl
        }
    }
}