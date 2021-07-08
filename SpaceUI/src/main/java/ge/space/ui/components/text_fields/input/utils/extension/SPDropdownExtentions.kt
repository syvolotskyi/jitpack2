package ge.space.ui.components.text_fields.input.utils.extension

import android.os.Parcelable
import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import kotlinx.android.parcel.Parcelize


/**
 * Simple item model for dropdown. After merging project into Space project can be
 * replaced with real model or created extention to map into this model
 *
 * @param id
 * @param value
 * @param imageUrl
 */

@Parcelize
data class SPDropdownItemModel(
    val id: Int,
    val value: String,
    val imageUrl: String? = null
) : Parcelable

/**
 * item bind extension. using jenerics we can add different bindings for dropdowns
 *
 */
fun SPTextFieldDropdown<SPDropdownItemModel>.buildWithDropdownItemModel() {
    this.bindViewValue = { item ->
        text = item.value
        imageUrl = item.imageUrl.orEmpty()
    }
}

/**
 * item bind extension. using jenerics we can add different bindings for dropdowns
 *
 */
fun SPTextFieldDropdown<SPDropdownItemModel>.buildWithChipsDropdownItemModel() {
    this.bindViewValue = { item ->
        text = item.value
        imageUrl = item.imageUrl.orEmpty()
    }
}



