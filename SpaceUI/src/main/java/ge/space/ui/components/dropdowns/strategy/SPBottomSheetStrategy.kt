package ge.space.ui.components.dropdowns.strategy

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import ge.space.ui.components.dropdowns.core.SPBottomSheetFragment

/**
 * Strategy realization for [SPBottomSheetFragment].
 * Data is onResult return type
 */

interface SPBottomSheetStrategy<Data> {

    /**
     * Calls for initializing strategy
     *
     * @param fm [FragmentManager] is supportFragmentManager
     * @param container [LinearLayout] is parent container
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    fun onCreate(fm: FragmentManager, container: ViewGroup, dismissEvent: (Data?) -> Unit)
}

