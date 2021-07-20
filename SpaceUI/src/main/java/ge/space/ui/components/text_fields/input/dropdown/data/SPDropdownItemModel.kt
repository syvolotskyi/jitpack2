package ge.space.ui.components.text_fields.input.dropdown.data

import ge.space.ui.components.image.SPIconFactory


/**
 * Simple item model for dropdown. After merging project into Space project can be
 * replaced with real model or created extension to map into this model
 *
 * @param id
 * @param value
 * @param iconData
 */

data class SPDropdownItemModel(
    val id: Int,
    val value: String,
    val iconData: SPIconFactory.SPIconData? = null,
)



