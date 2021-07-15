package ge.space.ui.components.bank_cards.chip.card.factory

import android.content.Context
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.image.SPIconFactory

abstract class SPChipIcon<T : SPIconFactory.SPIconData>(val context: Context) {
    abstract fun create(type: T): SPBaseChip
}