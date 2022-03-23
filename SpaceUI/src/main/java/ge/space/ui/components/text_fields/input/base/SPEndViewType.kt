package ge.space.ui.components.text_fields.input.base

/**
 * Sealed class helps to handle end view type for SPTextFieldInput.
 *
 * Contains:
 * @object SPNoneViewType [SPEndViewType] for state when we don't have any view.
 * @object SPCardViewType [SPEndViewType] provides a card chip view.
 * @object SPRemovableViewType [SPEndViewType] provides a remove image with deleting all text after click on it.
 * @class SPCurrencyViewType [SPEndViewType] provides an information for currency text view.
 * @class SPImageViewType [SPEndViewType] provides an image view.
 */
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
