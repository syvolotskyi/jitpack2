package ge.space.design.ui_components.banners.sub_components.status

import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.banners.SPBannerBaseFactory
import ge.space.ui.util.view_factory.SPViewData

class SPBannerStatusComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banner_status

    override fun getDescriptionResId(): Int = R.string.component_banner_status_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPBannerBaseFactory() {

        override var bannerResource: SPViewData = SPViewData.SPImageResourcesData(R.drawable.img_status_success)

        override fun changeBannerResource(v: View) {
            with(binding) {

                val popup = PopupMenu(showCaseEnvironment.context, v)
                popup.menuInflater.inflate(R.menu.sp_banner_menu, popup.menu)

                popup.setOnMenuItemClickListener { menuItem: MenuItem ->

                    changeResourceButton.text = menuItem.title.toString()

                    bannerResource = when (menuItem.itemId) {
                        R.id.option_success -> SPViewData.SPImageResourcesData(R.drawable.img_status_success)
                        R.id.option_error -> SPViewData.SPImageResourcesData(R.drawable.img_status_error)
                        R.id.option_pending -> SPViewData.SPImageResourcesData(R.drawable.img_status_pending)
                        R.id.option_info -> SPViewData.SPImageResourcesData(R.drawable.img_status_info)
                        else -> SPViewData.SPImageResourcesData(R.drawable.img_status_success)

                    }
                    setBannerResource()
                    true
                }
                popup.show()
            }

        }

    }
}