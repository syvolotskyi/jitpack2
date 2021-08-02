package ge.space.ui.components.text_fields.input.dropdown.data

import ge.space.ui.util.view_factory.SPViewData


/**
 * Simple item model for dropdown.
 *
 * @param id
 * @param value
 * @param iconData
 */

data class SPDropdownItemModel(
    val id: Int,
    val value: String,
    val iconData: SPViewData? = null,
)



