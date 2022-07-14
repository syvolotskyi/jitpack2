package ge.space.ui.components.dropdowns.strategy

import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import ge.space.ui.components.dropdowns.SPBottomSheetFragment

/**
 * Strategy realization for [SPBottomSheetFragment]
 */

interface SPBottomSheetStrategy<Item> {

    /**
     * Calls for initializing strategy
     *
     * @param fm [FragmentManager] is supportFragmentManager
     * @param container [LinearLayout] is parent container
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    fun onCreate(fm: FragmentManager, container: LinearLayout, dismissEvent: (Item?) -> Unit)
}

