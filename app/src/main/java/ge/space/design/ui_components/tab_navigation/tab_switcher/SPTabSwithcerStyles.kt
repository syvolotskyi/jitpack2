package ge.space.design.ui_components.tab_navigation.tab_switcher

import com.example.spacedesignsystem.R

data class SPTabSwitcherSupportsLoading(
    val resId: Int = R.style.SPTabSwitcher,
    val title: String,
    val isChecked: Boolean = false,
)

object SPTabSwitcherStyles {
    val list = listOf(
        SPTabSwitcherSupportsLoading(title = "14-21ივლისი 2018",isChecked= true),
        SPTabSwitcherSupportsLoading( title = "14-21ივლისი 2018"),
        SPTabSwitcherSupportsLoading(title = "14-21ივლისი 2018"),
    )
}