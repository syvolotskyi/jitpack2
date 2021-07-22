package ge.space.ui.components.text_fields.input.dropdown.data

import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown

// Todo please use java doc here
interface SPOnBindInterface<T> {
    fun getBindItemModel(): (view: SPTextFieldDropdown<T>, item: T) -> Unit
}

