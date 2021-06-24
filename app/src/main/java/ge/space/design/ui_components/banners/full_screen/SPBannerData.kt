package ge.space.design.ui_components.banners.full_screen

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * [SPBannerData] is a parcelable data class
 * it is needed for showing
 * edited banner view in a full screen, on [SPBannerFullScreenActivity]
 */

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

/**
 * [SPBannerType] specifies which banner
 * view should be added in [SPBannerFullScreenActivity]
 * it is needed for [SPBannerData]'s parameter
 */

enum class SPBannerType {
    Status,
    Illustration
}