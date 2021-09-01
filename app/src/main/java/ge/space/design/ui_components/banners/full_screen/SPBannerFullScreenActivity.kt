package ge.space.design.ui_components.banners.full_screen

import android.os.Bundle
import com.example.spacedesignsystem.R
import ge.space.design.main.ui.SPBaseActivity
import ge.space.ui.components.banner.SPBannerView

class SPBannerFullScreenActivity : SPBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent?.getParcelableExtra<SPBannerData>(KEY_DATA)
        val view = SPBannerView(this)

        view.apply {
            bannerTitle = data!!.title
            bannerSubtitle = data.subtitle
            bannerDescription = data.description
            titleVisibility = data.titleVisibility
            subTitleVisibility = data.subtitleVisibility
            descriptionVisibility = data.descVisibility
            setBannerResource(data.resource)
            setViewStyle(R.style.SPBanner_Base)
        }

        setContentView(view)

    }

    companion object {
        val KEY_DATA = this::class.toString()
    }

}