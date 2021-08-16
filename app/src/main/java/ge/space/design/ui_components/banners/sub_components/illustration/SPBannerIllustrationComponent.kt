package ge.space.design.ui_components.banners.sub_components.illustration

import android.view.View
import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.banners.SPBannerBaseFactory
import ge.space.ui.util.view_factory.SPViewData

class SPBannerIllustrationComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banner_illustration

    override fun getDescriptionResId(): Int = R.string.component_banner_illustration_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPBannerBaseFactory() {

        override var bannerResource: SPViewData = SPViewData.SPImageResourcesData(R.drawable.sp_banner_illustration_example)

        override fun changeBannerResource(v: View) {
            bannerResource =
                if (bannerResource == SPViewData.SPImageResourcesData(R.drawable.sp_banner_illustration_example))
                    SPViewData.SPImageResourcesData(R.drawable.sp_banner_illustration_example2)
                else SPViewData.SPImageResourcesData(R.drawable.sp_banner_illustration_example)

            setBannerResource()
        }
    }
}