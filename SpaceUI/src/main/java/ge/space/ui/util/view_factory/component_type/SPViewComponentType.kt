package ge.space.ui.util.view_factory.component_type

import android.content.Context
import android.view.View
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.util.view_factory.SPViewData

abstract class SPViewComponentType<T : SPViewData>(val context: Context) {
    abstract fun create(type: T): View
}