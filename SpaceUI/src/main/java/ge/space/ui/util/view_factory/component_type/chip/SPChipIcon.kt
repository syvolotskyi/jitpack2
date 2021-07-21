package ge.space.ui.util.view_factory.component_type.chip

import android.content.Context
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.util.view_factory.SPViewData

abstract class SPChipIcon<T : SPViewData>(val context: Context) {
    abstract fun create(type: T): SPBaseChip
}