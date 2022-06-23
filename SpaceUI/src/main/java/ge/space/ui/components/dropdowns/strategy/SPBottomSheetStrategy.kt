package ge.space.ui.components.dropdowns.strategy

import android.widget.LinearLayout
import ge.space.ui.components.dropdowns.SPBottomSheetFragment

/**
 * Strategy realization for [SPBottomSheetFragment]
 */

interface SPBottomSheetStrategy {
    fun onCreate(container: LinearLayout, dismissEvent: () -> Unit)

}

