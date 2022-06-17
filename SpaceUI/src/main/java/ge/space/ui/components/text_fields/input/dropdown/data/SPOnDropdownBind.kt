package ge.space.ui.components.text_fields.input.dropdown.data

import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown

/**
 * OnBindInterface help to handle dropdown the binding after selecting an item
 *
 * @param T keeps Item type
 */
interface SPOnDropdownBind<T> {

    /**
     * Function to throw binding selected item to dropdown
     *
     * @return a lambda to handle the binding process of dropdown
     */
    fun getBindItemModel(): (view: SPTextFieldDropdown<T>, item: T) -> Unit
}

