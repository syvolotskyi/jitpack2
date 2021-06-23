package ge.space.design.ui_components.banners.full_screen

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SPBannerData(
    val type: SPBannerType,
    val title: String,
    val subtitle: String,
    val description: String,
    val titleVisibility: Boolean,
    val subtitleVisibility: Boolean,
    val descVisibility: Boolean,
    val image: Int = 0,
    val style: Int = 0
) : Parcelable


enum class SPBannerType {
    Status,
    Illustration
}