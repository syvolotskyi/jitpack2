package ge.space.design.ui_components.banners.illustration

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpBannerIllustrationShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPBannerIllustrationComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banner_illustration

    override fun getDescriptionResId(): Int = R.string.component_banner_illustration_description

    override fun getComponentClass(): Class<*>? = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val layoutBinding = SpBannerIllustrationShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )
            return layoutBinding.root
        }
    }

}