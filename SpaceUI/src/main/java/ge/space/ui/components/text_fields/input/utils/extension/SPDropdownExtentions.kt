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
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        return (other as? SPDropdownItemModel)?.id == this.id
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + value.hashCode()
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        return result
    }
}

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



