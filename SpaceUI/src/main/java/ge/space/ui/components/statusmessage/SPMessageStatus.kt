package ge.space.ui.components.statusmessage

import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R


enum class SPMessageStatus(
    @StyleRes val textAppearance: Int,
    @DrawableRes val icon: Int) {
    SUCCESS(R.style.h800_medium_accent_green, R.drawable.ic_checkmark_circle_16_regular),
    ERROR(R.style.h800_medium_accent_magenta, R.drawable.ic_alert_circle_16_regular),
    PENDING(R.style.h800_medium_accent_orange, R.drawable.ic_clock_circle_16_regular),
    INFO(R.style.h800_medium_brand_primary, R.drawable.ic_info_circle_16_regular)
}

/*
sealed class SPMessageStatus(
    @StyleRes val textAppearance: Int,
    @DrawableRes val icon: Int) {

    class Success : SPMessageStatus(R.style.h800_medium_accent_green,
        R.drawable.ic_checkmark_circle_16_regular)

    class Error : SPMessageStatus(R.style.h800_medium_accent_magenta,
        R.drawable.ic_alert_circle_16_regular)

    class Pending : SPMessageStatus(R.style.h800_medium_accent_orange,
        R.drawable.ic_clock_circle_24_regular)

    class Info : SPMessageStatus(R.style.h800_medium_brand_primary,
        R.drawable.ic_info_circle_16_regular)

}*/
