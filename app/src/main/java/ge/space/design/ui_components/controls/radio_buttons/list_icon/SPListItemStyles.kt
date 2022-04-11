package ge.space.design.ui_components.controls.radio_buttons.list_icon

import com.example.spacedesignsystem.R
import ge.space.ui.components.buttons.SPButton


data class SPListIconSupportsLoading(
    val titleId: Int,
    val url: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/Flag_of_Georgia_rounded.svg/1200px-Flag_of_Georgia_rounded.svg.png",
)

object SPListItemStyles {
    val georgian =
        SPListIconSupportsLoading(R.string.georgian)
    val english = SPListIconSupportsLoading(
        R.string.english,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Flag_of_England.svg/2560px-Flag_of_England.svg.png"
    )
    val ukraine = SPListIconSupportsLoading(
        R.string.ukraine,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Flag_of_Ukraine.svg/800px-Flag_of_Ukraine.svg.png"
    )

}