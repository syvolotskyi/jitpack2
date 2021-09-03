package ge.space.ui.util.view_factory.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPSecondaryChip
import ge.space.ui.util.view_factory.component_type.SPViewComponentType
import ge.space.ui.util.view_factory.SPViewData

class SPSecondaryChipIconImpl(context: Context) :
    SPViewComponentType<SPViewData.SPSecondaryChipData>(context) {

    override fun create(type: SPViewData.SPSecondaryChipData): SPSecondaryChip {
        return SPSecondaryChip(context).apply {
            chipHeight = type.chipHeight
            chipWidth = type.chipWidth
            bankLogoUrl = type.bankLogoUrl
            paymentSystemUrl = type.paymentSystemUrl
            hasBorder = type.hasBorder
        }
    }
}