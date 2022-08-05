package ge.space.ui.components.dialogs.data

import android.os.Parcelable
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import kotlinx.parcelize.Parcelize

/**
 * Sealed class which allows to change an icon for the Space dialogs. In order to add new
 * icon [SPDialogIcon] sealed class has to be expanded and added a new data class both with
 * a color and an icon
 *
 * @property color applies a color tint for the icon
 * @property icon abstract value which applies an icon
 */
sealed class SPDialogIcon(
    @AttrRes open val colorAttr: Int = R.attr.colorAccent
) : Parcelable {

    /**
     * Applies an icon
     */
    abstract val icon: Int

    /**
     * Data class which applies [R.drawable.ic_alert_circle_24_regular] icon
     *
     * @property colorAttr applies a color tint for the icon
     */
    @Parcelize
    data class Alert(
        @AttrRes override val colorAttr: Int = R.attr.status_primary_distractive
    ) : SPDialogIcon() {

        override val icon: Int =
            R.drawable.ic_alert_circle_24_regular
    }

    /**
     * Data class which applies [R.drawable.ic_biometric_24_regular] icon
     *
     * @property colorAttr applies a color tint for the icon
     */
    @Parcelize
    data class Biometric(
        @AttrRes override val colorAttr: Int = R.attr.status_primary_alert
    ) : SPDialogIcon() {

        override val icon: Int =
            R.drawable.ic_biometric_24_regular
    }

    /**
     * Data class which applies [R.drawable.ic_checkmark_circle_24_regular] icon
     *
     * @property colorAttr applies a color tint for the icon
     */
    @Parcelize
    data class Checkmark(
        @AttrRes override val colorAttr: Int = R.attr.status_primary_alert
    ) : SPDialogIcon() {

        override val icon: Int =
            R.drawable.ic_checkmark_circle_24_regular
    }

    /**
     * Data class which applies [R.drawable.ic_info_circle_24_regular] icon
     *
     * @property colorAttr applies a color tint for the icon
     */
    @Parcelize
    data class Info(
        @AttrRes override val colorAttr: Int = R.attr.brand_primary
    ) : SPDialogIcon() {

        override val icon: Int =
            R.drawable.ic_info_circle_24_regular
    }

    /**
     * Data class which applies [R.drawable.ic_trophy_winner_24_regular] icon
     *
     * @property colorAttr applies a color tint for the icon
     */
    @Parcelize
    data class TrophyWinner(
        @AttrRes override val colorAttr: Int = R.attr.brand_primary
    ) : SPDialogIcon() {

        override val icon: Int =
            R.drawable.ic_trophy_winner_24_regular
    }

    /**
     * Data class which applies [R.drawable.ic_star_broken_24_regular] icon
     *
     * @property colorAttr applies a color tint for the icon
     */
    @Parcelize
    data class StarBroken(
        @AttrRes override val colorAttr: Int = R.attr.brand_primary
    ) : SPDialogIcon() {

        override val icon: Int =
            R.drawable.ic_star_broken_24_regular
    }

    /**
     * Data class which applies [R.drawable.ic_picture_24_regular] icon
     *
     * @property colorAttr applies a color tint for the icon
     */
    @Parcelize
    data class Picture(
        @AttrRes override val colorAttr: Int = R.attr.brand_primary
    ) : SPDialogIcon() {

        override val icon: Int =
            R.drawable.ic_picture_24_regular
    }
}