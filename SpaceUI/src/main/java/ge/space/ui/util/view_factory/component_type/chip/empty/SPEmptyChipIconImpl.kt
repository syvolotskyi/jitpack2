package ge.space.ui.util.view_factory.component_type.chip.empty

import android.content.Context
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.ui.components.bank_cards.chip.empty.SPEmptyChip
import ge.space.ui.util.view_factory.component_type.SPViewComponentType
import ge.space.ui.util.view_factory.SPViewData

class SPEmptyChipIconImpl(context: Context) :
    SPViewComponentType<SPViewData.SPEmptyChipData>(context) {

    override fun create(type: SPViewData.SPEmptyChipData): SPEmptyChip {
        return SPEmptyChip(context).apply {
            setViewStyle(type.styleRes)
            chipHeight = type.chipHeight
            chipWidth = type.chipWidth
            emptyViewStyle = type.chipStyle
            type.params?.let {
                it.height?.let { this.setHeight(it) }
                it.width?.let { this.setWidth(it) }
            }
            setPadding(
                type.params?.paddingStart ?: 0,
                type.params?.paddingTop ?: 0,
                type.params?.paddingEnd ?: 0,
                type.params?.paddingBottom ?: 0
            )
        }
    }
}