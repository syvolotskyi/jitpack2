package ge.space.design.ui_components.amount

import android.content.Context
import com.example.spacedesignsystem.R
import ge.space.ui.components.amount.SPAmountView
import ge.space.ui.util.extension.EMPTY_TEXT


data class SPAmountLoading(
    val styleId: Int = R.style.SPAmountView_Success,
    val amount: String,
    val currency: String,
    val title: String,
    val description: String,
    val addOnType: SPAmountView.AddOnType = SPAmountView.AddOnType.None,
    val addOnText: String = EMPTY_TEXT,
)

class SPAmountStyles(context: Context) {
    val list = listOf(
        SPAmountLoading(
            amount = "12.12", currency = "$", title = context.getString(R.string.app_name),
            description = context.getString(R.string.small_example_text)
        ),
        SPAmountLoading(
            styleId = R.style.SPAmountView_Error,
            amount = "12.12",
            currency = "$",
            title = context.getString(R.string.app_name),
            description = context.getString(R.string.small_example_text),
            addOnType = SPAmountView.AddOnType.Tooltip,
            addOnText = context.getString(R.string.show_btn),
        ),
        SPAmountLoading(
            styleId = R.style.SPAmountView_Brand,
            amount = "12.12",
            currency = "$",
            title = context.getString(R.string.app_name),
            description = context.getString(R.string.small_example_text),
            addOnType = SPAmountView.AddOnType.Info,
            addOnText = context.getString(R.string.show_btn)
        )
    )
}