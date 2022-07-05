package ge.space.ui.components.dropdowns.strategy

import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import ge.space.ui.components.dropdowns.SPBottomSheetFragment

/**
 * Strategy realization for [SPBottomSheetFragment]
 */

interface SPBottomSheetStrategy {

    /**
     * Calls for creation a content in bottom sheet fragment
     *
     * @param container [LinearLayout] for content view
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    fun onCreate(fm: FragmentManager, container: LinearLayout, dismissEvent: () -> Unit)
}

