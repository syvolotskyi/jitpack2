package ge.space.ui.components.text_fields.input.dropdown.data

import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown

interface OnBindInterface<T> {
    fun getBindItemModel(): (view: SPTextFieldDropdown<T>, item: T) -> Unit
}

