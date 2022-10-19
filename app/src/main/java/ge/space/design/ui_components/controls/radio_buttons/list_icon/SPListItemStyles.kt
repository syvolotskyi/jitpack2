package ge.space.design.ui_components.controls.radio_buttons.list_icon

import com.example.spacedesignsystem.R
import ge.space.ui.components.buttons.SPButton


data class SPListIconSupportsLoading(
    val titleId: Int,
    val url: String =  "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Flag_of_Georgia.svg/2560px-Flag_of_Georgia.svg.png",
)

object SPListItemStyles {
    val georgian =
        SPListIconSupportsLoading(R.string.georgia)
    val english = SPListIconSupportsLoading(
        R.string.english,
        "https://upload.wikimedia.org/wikipedia/en/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1200px-Flag_of_the_United_Kingdom.svg.png"
    )
    val ukraine = SPListIconSupportsLoading(
        R.string.ukraine,
        "https://dnmu.edu.ua/wp-content/uploads/2020/07/derzhavnyj-prapor-ukrayiny.jpg"
    )

}