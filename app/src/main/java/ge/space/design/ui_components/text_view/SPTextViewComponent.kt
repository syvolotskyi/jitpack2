package ge.space.design.ui_components.text_view

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.tag.SPTagComponent

class SPTextViewComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.text_views

    override fun getDescriptionResId(): Int = R.string.text_views_desc

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            SPTextViewComponent(),
            SPTagComponent(),
        )
    }
}