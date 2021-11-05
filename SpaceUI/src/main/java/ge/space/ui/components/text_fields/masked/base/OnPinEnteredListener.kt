package ge.space.ui.components.text_fields.masked.base


/**
 * Interface which allow to listen view and are called when the text is entered
 *
 */
interface OnPinEnteredListener {

    /**
     * function called when user entered a whole text
     *
     * @param pinCode returns entered text
     */
    fun onPinEntered(pinCode: CharSequence)
}