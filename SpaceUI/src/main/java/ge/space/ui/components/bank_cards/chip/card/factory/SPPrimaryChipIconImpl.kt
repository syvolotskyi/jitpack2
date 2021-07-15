package ge.space.ui.components.bank_cards.chip.card.factory

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPPrimaryChip
import ge.space.ui.components.image.SPIconFactory

class SPPrimaryChipIconImpl(context: Context) :
    SPChipIcon<SPIconFactory.SPIconData.SPrimaryChip>(context) {

    override fun create(type: SPIconFactory.SPIconData.SPrimaryChip): SPPrimaryChip {
        return SPPrimaryChip(context).apply {
            size = type.chipSize
        }
    }
}