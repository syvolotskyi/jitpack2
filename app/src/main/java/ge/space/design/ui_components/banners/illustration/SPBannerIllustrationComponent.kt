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

        lateinit var layoutBinding: SpBannerIllustrationShowcaseBinding

        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            layoutBinding = SpBannerIllustrationShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )

            layoutBinding.apply {
                bannerInputTextsView.bannerTitleEditText.onTextChanged {
                    BannerIllustration.bannerTitle = it
                }
                bannerInputTextsView.bannerSubTitleEditText.onTextChanged {
                    BannerIllustration.bannerSubtitle = it
                }
                bannerInputTextsView.bannerDescEditText.onTextChanged {
                    BannerIllustration.bannerDescription = it
                }
                bannerInputTextsView.bannerTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    BannerIllustration.titleVisibility = isChecked
                }
                bannerInputTextsView.bannerSubTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    BannerIllustration.subTitleVisibility = isChecked
                }
                bannerInputTextsView.bannerDescVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    BannerIllustration.descriptionVisibility = isChecked
                }

                showFullScreenButton.setOnClickListener {
                    val intent = Intent(environmentSP.context, SPBannerFullScreenActivity::class.java)
                    val bannerData = SPBannerData(
                        SPBannerType.Illustration,
                        BannerIllustration.bannerTitle,
                        BannerIllustration.bannerSubtitle,
                        BannerIllustration.bannerDescription,
                        BannerIllustration.bannerImage
                    )
                    intent.putExtra("BannerAttributes", bannerData)
                    environmentSP.context.startActivity(intent)

                }

                changeImageButton.setOnClickListener {
                    BannerIllustration.bannerImage =
                        if (BannerIllustration.bannerImage == R.drawable.sp_banner_illustration_example)
                            R.drawable.sp_banner_illustration_example2
                        else R.drawable.sp_banner_illustration_example
                }
            }

            return layoutBinding.root
        }

    }

}