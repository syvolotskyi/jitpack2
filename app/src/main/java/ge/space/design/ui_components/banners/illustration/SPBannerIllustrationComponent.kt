package ge.space.design.ui_components.banners.illustration

import android.content.Context
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpBannerIllustrationShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.extensions.onTextChanged

class SPBannerIllustrationComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banner_illustration

    override fun getDescriptionResId(): Int = R.string.component_banner_illustration_description

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {

        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val layoutBinding = SpBannerIllustrationShowcaseBinding.inflate(
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
            }

            return layoutBinding.root
        }


    }

}