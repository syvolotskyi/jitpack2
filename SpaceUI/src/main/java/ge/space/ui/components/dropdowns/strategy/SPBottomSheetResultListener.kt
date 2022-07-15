package ge.space.ui.components.dropdowns.strategy

import ge.space.ui.components.dropdowns.SPBottomSheetFragment


/**
 * Set listener for dismiss
 */
interface SPBottomSheetResultListener<Data> {

    /**
     * Set listener for dismiss
     *
     * @param listener [(Data) -> Unit] calls when bottom sheet is dismissed and throw the result
     */
    fun onResult(listener: (Data) -> Unit)
}