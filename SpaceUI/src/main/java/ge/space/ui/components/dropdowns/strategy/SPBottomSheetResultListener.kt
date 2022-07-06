package ge.space.ui.components.dropdowns.strategy
interface SPBottomSheetResultListener<Data> {
    fun setBottomSheetResult(listener: (Data) -> Unit)
}