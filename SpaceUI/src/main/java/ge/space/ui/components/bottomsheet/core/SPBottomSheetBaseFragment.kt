package ge.space.ui.components.bottomsheet.core

import androidx.fragment.app.Fragment

/**
 * Bottomsheet Base Fragment, where [Data] is an item return type
 */
abstract class SPBottomSheetBaseFragment<Data> : Fragment() {
    /**
     * Calls when the bottom sheet was closed and returs a [Data]
     */
    protected var onDismiss: (Data) -> Unit = {}

    /**
     * Sets a lambda result listener
     *
     * @param listener [Data] calls when bottom sheet is dismissed
     */
    fun setBottomSheetResult(listener: (Data) -> Unit) {
        onDismiss = listener
    }
}