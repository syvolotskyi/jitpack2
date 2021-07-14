package ge.space.design.ui_components.banners

import com.example.spacedesignsystem.R
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.ui_components.banners.illustration.SPBannerIllustrationComponent
import ge.space.design.ui_components.banners.status.SPBannerStatusComponent

class SPBannerComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banners

    override fun getDescriptionResId(): Int = R.string.banner_description

    override fun getSubComponents(): List<SPShowCaseComponent> {
        return listOf(
            SPBannerStatusComponent(),
            SPBannerIllustrationComponent()
        )
    }

}