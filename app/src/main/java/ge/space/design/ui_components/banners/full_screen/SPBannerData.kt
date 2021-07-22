package ge.space.design.ui_components.banners.full_screen

import android.os.Parcelable
import ge.space.ui.util.view_factory.SPViewData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


/**
 * [SPBannerData] is a parcelable data class
 * it is needed for showing
 * edited banner view in a full screen, on [SPBannerFullScreenActivity]
 */

@Parcelize
data class SPBannerData(
    val title: String,
    val subtitle: String,
    val description: String,
    val titleVisibility: Boolean,
    val subtitleVisibility: Boolean,
    val descVisibility: Boolean,
    val resource: @RawValue SPViewData
) : Parcelable
