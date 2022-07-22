package ge.space.ui.components.dropdowns.core

/**
 * Bottomsheet Result listener, where [Data] is an item return type
 */
interface SPBottomSheetResultListener<Data> {
    /**
     * Sets a lambda result listener
     *
     * @param listener [Data] calls when bottom sheet is dismissed
     */
    fun setBottomSheetResult(listener: (Data) -> Unit)
}