package ge.space.ui.components.bank_cards.chip.card.factory

import android.content.Context
import ge.space.ui.components.bank_cards.chip.card.SPDigitalChip
import ge.space.ui.components.image.SPIconFactory

class SPDigitalChipIconImpl(context: Context) :
    SPChipIcon<SPIconFactory.SPIconData.SPDigitalChip>(context) {

    override fun create(type: SPIconFactory.SPIconData.SPDigitalChip): SPDigitalChip {
        return SPDigitalChip(context).apply {
            cardBackground = type.gradient
        }
    }
}