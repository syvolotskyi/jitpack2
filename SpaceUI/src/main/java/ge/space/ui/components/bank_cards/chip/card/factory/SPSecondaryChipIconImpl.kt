package ge.space.ui.components.bank_cards.chip.card.factory

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPSecondaryChip
import ge.space.ui.components.image.SPIconFactory

class SPSecondaryChipIconImpl(context: Context) :
    SPChipIcon<SPIconFactory.SPIconData.SPSecondaryChip>(context) {

    override fun create(type: SPIconFactory.SPIconData.SPSecondaryChip): SPSecondaryChip {
        return SPSecondaryChip(context).apply {
            bankLogoUrl = type.bankLogoUrl
        }
    }
}