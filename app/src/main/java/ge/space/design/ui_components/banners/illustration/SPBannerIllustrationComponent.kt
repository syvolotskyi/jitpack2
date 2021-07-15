package ge.space.design.ui_components.banners.illustration

import android.content.Intent
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpBannerIllustrationShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.banners.full_screen.SPBannerData
import ge.space.design.ui_components.banners.full_screen.SPBannerFullScreenActivity
import ge.space.design.ui_components.banners.full_screen.SPBannerType
import ge.space.extensions.onTextChanged

class SPBannerIllustrationComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banner_illustration

    override fun getDescriptionResId(): Int = R.string.component_banner_illustration_description

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {

        lateinit var binding: SpBannerIllustrationShowcaseBinding

        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            binding = SpBannerIllustrationShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )

            with(binding){
                bannerIllustration.setBannerStyle(R.style.SPBanner_Base)

                bannerInputTextsView.bannerTitleEditText.onTextChanged {
                    bannerIllustration.bannerTitle = it
                }
                bannerInputTextsView.bannerSubTitleEditText.onTextChanged {
                    bannerIllustration.bannerSubtitle = it
                }
                bannerInputTextsView.bannerDescEditText.onTextChanged {
                    bannerIllustration.bannerDescription = it
                }
                bannerInputTextsView.bannerTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    bannerIllustration.titleVisibility = isChecked
                }
                bannerInputTextsView.bannerSubTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    bannerIllustration.subTitleVisibility = isChecked
                }
                bannerInputTextsView.bannerDescVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    bannerIllustration.descriptionVisibility = isChecked
                }

                showFullScreenButton.setOnClickListener {
                    val intent = Intent(environmentSP.context, SPBannerFullScreenActivity::class.java)
                    val bannerData = SPBannerData(
                        SPBannerType.Illustration,
                        bannerIllustration.bannerTitle,
                        bannerIllustration.bannerSubtitle,
                        bannerIllustration.bannerDescription,
                        bannerIllustration.titleVisibility,
                        bannerIllustration.subTitleVisibility,
                        bannerIllustration.descriptionVisibility,
                        bannerIllustration.bannerImage
                    )
                    intent.putExtra(SPBannerFullScreenActivity.KEY_DATA, bannerData)
                    environmentSP.context.startActivity(intent)

                }

                changeImageButton.setOnClickListener {
                    bannerIllustration.bannerImage =
                        if (bannerIllustration.bannerImage == R.drawable.sp_banner_illustration_example)
                            R.drawable.sp_banner_illustration_example2
                        else R.drawable.sp_banner_illustration_example
                }
            }

            return binding.root
        }

    }

}