package ge.space.ui.components.statusmessage

import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R


enum class SPMessageStatus(
    @StyleRes val textAppearance: Int,
    @DrawableRes val icon: Int) {
    SUCCESS(R.style.SPStatusTextView_Success_TextAppearance, R.drawable.ic_checkmark_circle_16_regular),
    ERROR(R.style.SPStatusTextView_Error_TextAppearance, R.drawable.ic_alert_circle_16_regular),
    PENDING(R.style.SPStatusTextView_Pending_TextAppearance, R.drawable.ic_clock_circle_16_regular),
    INFO(R.style.SPStatusTextView_Info_TextAppearance, R.drawable.ic_info_circle_16_regular)
}