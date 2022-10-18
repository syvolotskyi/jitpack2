package ge.space.design.ui_components.accordion

import android.content.Context
import com.example.spacedesignsystem.R

data class SPAccordionLoading(
    val textTitle: String,
    val textExpanded: String
)


class SPAccordionStyles(context: Context) {
    val list = listOf(
        SPAccordionLoading(
            textTitle = context.getString(R.string.small_example_text),
            textExpanded = context.getString(R.string.example_text),
        ),
        SPAccordionLoading(
            textTitle = context.getString(R.string.small_example_text),
            textExpanded = context.getString(R.string.example_text),
        ),
        SPAccordionLoading(
            textTitle = context.getString(R.string.small_example_text),
            textExpanded = context.getString(R.string.example_text),
        ),
        SPAccordionLoading(
            textTitle = context.getString(R.string.small_example_text),
            textExpanded = context.getString(R.string.example_text),
        ),
        SPAccordionLoading(
            textTitle = context.getString(R.string.small_example_text),
            textExpanded = context.getString(R.string.example_text),
        ),
        SPAccordionLoading(
            textTitle = context.getString(R.string.small_example_text),
            textExpanded = context.getString(R.string.example_text),
        ),
    )
}