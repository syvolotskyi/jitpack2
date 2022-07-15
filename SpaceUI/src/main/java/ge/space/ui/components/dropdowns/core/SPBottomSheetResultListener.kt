package ge.space.ui.components.dropdowns.core

/**
 * Result listener where [Data] is type of item
 */
interface SPBottomSheetResultListener<Data> {
    /**
     * Sets a lambda result listener
     *
     * @param listener [Data] calls when bottom sheet is dismissed
     */
    fun setBottomSheetResult(listener: (Data) -> Unit)
}