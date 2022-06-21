package ge.space.ui.components.dropdowns.strategy

import android.widget.LinearLayout
import ge.space.ui.components.dropdowns.SpBottomSheetFragment

/**
 * Strategy realization for [SpBottomSheetFragment]
 */

interface SpBottomSheetStrategy {
    fun onAddCreate(container: LinearLayout, dismissEvent: () -> Unit)

}

