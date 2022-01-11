package ge.space.ui.components.text_fields.input.base

sealed class SPEndViewType {
    object SPNoneViewType : SPEndViewType()
    object SPCardViewType : SPEndViewType()
    object SPRemovableViewType : SPEndViewType()
    data class SPCurrencyViewType(val currency: String) : SPEndViewType()
    data class SPImageViewType(val icon: Int = 0) :
        SPEndViewType()

    companion object {
        const val CARD = 1
        const val REMOVABLE = 2
        const val CURRENCY = 3
        const val IMAGE = 4
    }
}
