package ge.space.design.ui_components.profile

import com.example.spacedesignsystem.R
import ge.space.ui.util.extension.EMPTY_TEXT

data class SPProfileHeadingLoading(
    val resId: Int = R.style.SPProfileHeading,
    val title: Int? = null,
    val description: Int? = null,
    val defaultIcon: Int? = R.drawable.ic_pencil_24_regular,
    val profileUrl: String? = null,
)

object SPProfileHeadingStyles {
    const val profileURL =
        "https://media.cntraveller.com/photos/611bf0b8f6bd8f17556db5e4/1:1/w_2000,h_2000,c_limit/gettyimages-1146431497.jpg"
    val list = listOf(
        SPProfileHeadingLoading(),
        SPProfileHeadingLoading(title = R.string.small_example_text, profileUrl = profileURL),
        SPProfileHeadingLoading(
            title = R.string.small_example_text,
            description = R.string.small_example_text,
            profileUrl = profileURL
        ),
        SPProfileHeadingLoading(
            title = R.string.small_example_text,
            description = R.string.small_example_text,
            defaultIcon = null,
            profileUrl = profileURL
        )

    )
}