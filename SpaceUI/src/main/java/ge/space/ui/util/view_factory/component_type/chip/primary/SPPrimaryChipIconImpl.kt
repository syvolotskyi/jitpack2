package ge.space.ui.util.view_factory.component_type.chip.primary

import android.content.Context
import androidx.core.content.ContextCompat
import ge.space.ui.components.bank_cards.chip.card.SPPrimaryChip
import ge.space.ui.util.view_factory.component_type.SPViewComponentType
import ge.space.ui.util.view_factory.SPViewData

class SPPrimaryChipIconImpl(context: Context) :
    SPViewComponentType<SPViewData.SPrimaryChipData>(context) {

    override fun create(type: SPViewData.SPrimaryChipData): SPPrimaryChip {
        return SPPrimaryChip(context).apply {
            setViewStyle(type.styleRes)
            chipHeight = type.chipHeight
            chipWidth = type.chipWidth
            color = ContextCompat.getColor(context, android.R.color.transparent)
            type.params?.let {
                setPadding(it.paddingStart, it.paddingTop, it.paddingEnd, it.paddingBottom)
            }
        }
    }
}