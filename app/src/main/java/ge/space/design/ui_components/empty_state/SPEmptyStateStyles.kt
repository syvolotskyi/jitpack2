package ge.space.design.ui_components.empty_state

import com.example.spacedesignsystem.R

data class SPEmptySupportsLoading(
    val resId: Int = R.style.SPEmptyStateBase,
    val title: Int = 0,
    val description: Int = R.string.example_text,
    val button: Int = R.string.show_btn,
)

object SPEmptyStateStyles {
    val list = listOf(
        SPEmptySupportsLoading(title = R.string.app_name),
        SPEmptySupportsLoading(R.style.SPEmptyStateBase_WithButton, title = R.string.app_name),
        SPEmptySupportsLoading(title = R.string.app_name),
    )
}