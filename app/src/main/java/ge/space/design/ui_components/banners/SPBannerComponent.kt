package ge.space.design.ui_components.banners

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.banners.sub_components.card.SPBannerCardComponent
import ge.space.design.ui_components.banners.sub_components.illustration.SPBannerIllustrationComponent
import ge.space.design.ui_components.banners.sub_components.status.SPBannerStatusComponent

class SPBannerComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banners

    override fun getDescriptionResId(): Int = R.string.banner_description

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            SPBannerStatusComponent(),
            SPBannerIllustrationComponent(),
            SPBannerCardComponent()
        )
    }

}