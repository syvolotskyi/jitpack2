package ge.space.design.ui_components.stepper.view_pager

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutViewPageShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.pager_indicator.helper.SPPageIndicatorAttachmentType

class SPViewPagerComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.page_indicator_view_pager

    override fun getDescriptionResId(): Int = R.string.page_indicator_view_pager_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutViewPageShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val adapter = SPViewPagerAdapter(environment.requireFragmentActivity())

            layoutBinding.pager.adapter = adapter

            layoutBinding.pagerIndicatorHorizontal.attachTo(SPPageIndicatorAttachmentType.SPViewPager2(layoutBinding.pager))
            return layoutBinding.root
        }
    }
}