package ge.space.ui.components.text_fields.input.dropdown

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 *
 * @param id
 * @param value
 * @param imageUrl
 */

@Parcelize
data class SPDropdownItemModel (
    val id: Int,
    val value: String,
    val imageUrl: String? = null
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        return (other as? SPDropdownItemModel)?.id == this.id
    }

}

