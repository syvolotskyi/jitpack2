package ge.space.design.ui_components.banners.full_screen

import android.os.Bundle
import ge.space.design.main.ui.SPBaseActivity
import ge.space.ui.components.banners.SPBannerIllustration
import ge.space.ui.components.banners.SPBannerStatus
import ge.space.ui.components.banners.base.SPBannerBaseView

class SPBannerFullScreenActivity : SPBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent?.getParcelableExtra<SPBannerData>(KEY_DATA)

        if(data!!.type == SPBannerType.Status){
            val view = SPBannerStatus(this)
            view.setBannerStatusStyle(data.style)
            setView(data, view)
        }else if( data.type ==SPBannerType.Illustration){
            val view = SPBannerIllustration(this)
            view.bannerImage = data.image
            setView(data, view)
        }
    }

    private fun setView(data: SPBannerData?, view: SPBannerBaseView) {
        setContentView(view)
        view.apply {
            bannerTitle = data!!.title
            bannerSubtitle = data.subtitle
            bannerDescription = data.description
            titleVisibility = data.titleVisibility
            subTitleVisibility = data.subtitleVisibility
            descriptionVisibility = data.descVisibility
        }
    }

    companion object {
        val KEY_DATA = this::class.toString()
    }

}