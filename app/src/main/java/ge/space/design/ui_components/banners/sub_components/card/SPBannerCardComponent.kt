package ge.space.design.ui_components.banners.sub_components.card

import android.view.View
import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.banners.SPBannerBaseFactory
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.util.view_factory.SPViewData

class SPBannerCardComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banner_card

    override fun getDescriptionResId(): Int = R.string.component_banner_card_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPBannerBaseFactory() {
        override var bannerResource: SPViewData = SPViewData.SPNewCreditCards(SPChipSize.Small)

        override fun changeBannerResource(v: View) {
            bannerResource =
                if (bannerResource == SPViewData.SPNewCreditCards(SPChipSize.Small))
                    SPViewData.SPNewCreditCards(SPChipSize.Big)
                else SPViewData.SPNewCreditCards(SPChipSize.Small)

            setBannerResource()
        }
    }
}



