package ge.space.design.ui_components.stepper

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.stepper.recycler.SPPageIndicatorRecyclerComponent
import ge.space.design.ui_components.stepper.view_pager.SPViewPagerComponent

class SPPageIndicatorComponent  : ShowCaseComponent {
    override fun getNameResId() = R.string.page_indicator

    override fun getDescriptionResId() = R.string.page_indicator_desc

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            SPPageIndicatorRecyclerComponent(),
            SPViewPagerComponent()
        )
    }
}