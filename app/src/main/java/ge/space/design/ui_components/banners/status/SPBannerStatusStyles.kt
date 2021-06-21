package ge.space.design.ui_components.banners.status

import com.example.spacedesignsystem.R

data class SPBannerStatusSupportsLoading(
    val resId: Int,
    val state: String
)

object SPBannerStatusStyles {

    val list = listOf(
        SPBannerStatusSupportsLoading(R.style.SPBannerStatusSuccess, "Success State"),
        SPBannerStatusSupportsLoading(R.style.SPBannerStatusError, "Error State"),
        SPBannerStatusSupportsLoading(R.style.SPBannerStatusPending, "Pending State"),
        SPBannerStatusSupportsLoading(R.style.SPBannerStatusInfo, "Info State"),
    )

}