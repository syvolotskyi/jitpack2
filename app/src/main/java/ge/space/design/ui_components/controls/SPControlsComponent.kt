package ge.space.design.ui_components.controls

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.controls.toggleicon.SPToggleIconComponent

class SPControlsComponent : ShowCaseComponent {
    override fun getNameResId() = R.string.controls

    override fun getDescriptionResId() = R.string.controls_desc

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(SPToggleIconComponent())
    }

}