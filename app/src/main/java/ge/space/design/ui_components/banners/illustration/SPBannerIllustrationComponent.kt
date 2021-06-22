package ge.space.design.ui_components.banners.illustration

import androidx.fragment.app.FragmentActivity
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpBannerIllustrationShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.banners.SPBannerFullScreenFragment
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
            val activity = environmentSP.requireFragmentActivity()

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
                    showFullScreen(
                        activity,
                        BannerIllustration.bannerTitle,
                        BannerIllustration.bannerSubtitle,
                        BannerIllustration.bannerDescription,
                        BannerIllustration.bannerImage
                    )
                }

                changeImageButton.setOnClickListener {
                    BannerIllustration.bannerImage =
                        if (BannerIllustration.bannerImage == R.drawable.sp_banner_illustration_example)
                            R.drawable.sp_banner_illustration_example2
                        else R.drawable.sp_banner_illustration_example
                }
            }

            setTextAppearances()

            return layoutBinding.root
        }

        private fun setTextAppearances() {
//            layoutBinding.BannerIllustration.apply {
////                updateTitleTextAppearance(R.style.SPBannerTitleStyle)
////                updateSubtitleTextAppearance(R.style.SPBannerSubTitleStyle)
////                updateDescTextAppearance(R.style.SPBannerDescStyle)
//            }
            layoutBinding.BannerIllustration.setBannerStyle(R.style.SPBanner_Base)
        }

        private fun showFullScreen(
            fragmentActivity: FragmentActivity,
            bannerTitle: String,
            bannerSubtitle: String,
            bannerDescription: String,
            bannerImage: Int,
        ) {

            val nextFrag = SPBannerFullScreenFragment()
            fragmentActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.contentView, nextFrag, "findThisFragment")
                .commit()
        }

    }

}