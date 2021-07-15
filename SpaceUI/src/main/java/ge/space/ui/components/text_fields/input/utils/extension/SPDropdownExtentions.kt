package ge.space.ui.components.text_fields.input.utils.extension

import ge.space.ui.components.image.SPIconFactory
import ge.space.ui.components.image.SPCompanionIconFactory
import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown


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
    val iconData: SPIconFactory.SPIconData,
)

/**
 * item bind extension. using generic's we can add different bindings for dropdowns
 *
 */
fun SPTextFieldDropdown.buildWithDropdownItemModel() {
    val context = this.context
    this.bindViewValue = { item ->
        text = item.value

        val factory = SPCompanionIconFactory(context)
        val image = factory.create(item.iconData)

        setImage(image)
    }
}



