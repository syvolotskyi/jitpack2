package ge.space.ui.components.bank_cards.chip.card.factory

import android.content.Context
import ge.space.ui.components.bank_cards.chip.empty.SPEmptyChip
import ge.space.ui.components.image.SPIconFactory

class SPEmptyChipIconImpl(context: Context) :
    SPChipIcon<SPIconFactory.SPIconData.SPEmptyChip>(context) {

    override fun create(type: SPIconFactory.SPIconData.SPEmptyChip): SPEmptyChip {
        return SPEmptyChip(context).apply {
            style = type.chipStyle
        }
    }
}