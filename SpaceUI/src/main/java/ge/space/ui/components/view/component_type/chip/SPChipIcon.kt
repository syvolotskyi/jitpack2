package ge.space.ui.components.view.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.view.SPViewData

abstract class SPChipIcon<T : SPViewData>(val context: Context) {
    abstract fun create(type: T): SPBaseChip
}