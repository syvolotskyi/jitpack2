package ge.space.design.ui_components.banners

import android.content.Intent
import android.view.View
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpBannerShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.banners.full_screen.SPBannerData
import ge.space.design.ui_components.banners.full_screen.SPBannerFullScreenActivity
import ge.space.ui.util.extension.onTextChanged
import ge.space.ui.util.view_factory.SPViewData

abstract class SPBannerBaseFactory : SPComponentFactory {

    protected lateinit var binding: SpBannerShowcaseBinding
    protected lateinit var showCaseEnvironment: SPShowCaseEnvironment
    protected abstract var bannerResource : SPViewData

    override fun create(environment: SPShowCaseEnvironment): Any {

        binding = SpBannerShowcaseBinding.inflate(environment.requireLayoutInflater())
        showCaseEnvironment = environment

        setAttributes()

        with(binding) {

            changeResourceButton.setOnClickListener { v: View ->
                changeBannerResource(v)
            }

            showFullScreenButton.setOnClickListener {
                navigateToFullScreen()
            }

            setBannerResource()

        }
        return binding.root
    }

    private fun setAttributes() {
        with(binding) {
            bannerInputTextsView.bannerTitleEditText.onTextChanged {
                bannerView.bannerTitle = it
            }
            bannerInputTextsView.bannerSubTitleEditText.onTextChanged {
                bannerView.bannerSubtitle = it
            }
            bannerInputTextsView.bannerDescEditText.onTextChanged {
                bannerView.bannerDescription = it
            }
            bannerInputTextsView.bannerTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                bannerView.titleVisibility = isChecked
            }
            bannerInputTextsView.bannerSubTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                bannerView.subTitleVisibility = isChecked
            }
            bannerInputTextsView.bannerDescVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                bannerView.descriptionVisibility = isChecked
            }
        }
    }

    private fun navigateToFullScreen() {
        with(binding) {
            val intent =
                Intent(showCaseEnvironment.context, SPBannerFullScreenActivity::class.java)
            val bannerData = SPBannerData(
                bannerView.bannerTitle,
                bannerView.bannerSubtitle,
                bannerView.bannerDescription,
                bannerView.titleVisibility,
                bannerView.subTitleVisibility,
                bannerView.descriptionVisibility,
                bannerResource
            )
            intent.putExtra(SPBannerFullScreenActivity.KEY_DATA, bannerData)
            showCaseEnvironment.context.startActivity(intent)
        }

    }

    abstract fun changeBannerResource(v: View)

    protected fun setBannerResource(){
        binding.bannerView.setBannerResource(bannerResource)
    }


}