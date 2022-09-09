package ge.space.ui.components.bottomsheet.strategy

import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import ge.space.ui.components.bottomsheet.core.SPBottomSheetFragment
import ge.space.ui.util.extension.show

/**
 * Strategy realization for [SPBottomSheetFragment].
 *  if you want to use bottomsheet you must override SPBottomSheetResultListener in your fragment
 * Data is onResult return type
 */

interface SPBottomSheetStrategy<Data> {

    /**
     * Calls for initializing strategy.
     *
     * @param fm [FragmentManager] is supportFragmentManager
     * @param container [ViewGroup] is parent container
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    fun onCreate(fm: FragmentManager, container: ViewGroup, dismissEvent: (Data?) -> Unit) = container.show()

}

