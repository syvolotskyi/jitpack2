package ge.space.ui.components.bottomsheet.strategy

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ge.space.ui.components.bottomsheet.core.SPBottomSheetFragment
import ge.space.ui.util.extension.show

/**
 * Strategy realization for [SPBottomSheetFragment].
 * Data is onResult return type
 */

interface SPBottomSheetStrategy<Data> {

    /**
     * Calls for initializing strategy.
     *
     * @param sheetFragment [ SPBottomSheetFragment<*>] is a bottom sheet fragment
     * @param container [ViewGroup] is parent container
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    fun onCreate(
        sheetFragment: SPBottomSheetFragment<*>, container: ViewGroup, dismissEvent: (Data?) -> Unit
    ) = container.show()
}

