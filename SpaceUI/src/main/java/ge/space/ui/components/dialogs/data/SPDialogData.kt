package ge.space.ui.components.dialogs.data

import android.os.Parcelable
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.ui.components.dialogs.dialog_types.SPDialogEditText
import ge.space.ui.components.dialogs.dialog_types.SPDialog
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import kotlinx.android.parcel.Parcelize

/**
 * Sealed class which allows to init [SPDialog]
 */
sealed class SPDialogData {
    /**
     * Allows to init default [SPDialog] with icon, buttons, title and label
     *
     * @property title applies the top title. If it's null its view is gone
     * @property label applies the second title. If it's null its view is gone
     * @property buttonMultiple applies a type of bottom button type
     * @property buttons applies buttons models with their click handlers
     */
    data class SPInfoDialogData(
        val title: String?,
        val label: String?,
        val buttonMultiple: Boolean = false,
        val buttons: ArrayList<SPDialogInfoHolder>
    ) : SPDialogData()

    /**
     * Allows to init [SPDialog] just with the top and the second titles
     *
     * @property title applies the top title
     * @property label applies the second title
     */
    data class SPTitleLabelDialogData(
        val title: String?,
        val label: String?
    ) : SPDialogData()

    /**
     * Allows to init [SPDialog] just with the top title
     *
     * @property title applies the top title
     */
    data class SPTitleDialogData(
        val title: String
    ) : SPDialogData()

    /**
     * Allows to init [SPDialog] just with the second title
     *
     * @property label applies the second title
     */
    data class SPLabelDialogData(
        val label: String
    ) : SPDialogData()
}

/**
 * Allows to init default [SPDialogEditText] with both a title and buttons
 *
 * @property title applies the top title. If it's null its view is gone
 * @property buttons applies buttons models with their click handlers
 */
data class SPEditTextDialogData(
    val title: String?,
    val buttons: ArrayList<SPEditTextDialogInfoHolder>
) : SPDialogData()

/**
 * Wrapper which allows pass a dismiss handler action
 *
 * @property onDismissed keeps an action for dismiss handle
 */
@Parcelize
class SPDialogDismissHandler(
    val onDismissed: (() -> Unit?)? = null
): Parcelable

/**
 * Wrapper which allows pass a change EditText handler
 *
 * @property onChanged keeps an action for EditText listener
 */
@Parcelize
class SPEditTextDialogChangeHandler(
    val onChanged: ((String) -> Unit?)? = null
): Parcelable

/**
 * Allows to wrap data for creating [SPDialog]
 *
 * @property title applies a title of the dialog
 * @property label applies a label of the dialog
 * @property buttonModels applies a list of buttons
 */
data class SPDialogInfo(
    val title: String?,
    val label: String?,
    val buttonModels: ArrayList<SPDialogInfoHolder> = arrayListOf()
)

/**
 * Allows to wrap data for creating [SPDialogEditText]
 *
 * @property title applies a title of the dialog
 * @property buttonModels applies a list of buttons
 */
data class SPEditTextDialogInfo(
    val title: String,
    val buttonModels: ArrayList<SPEditTextDialogInfoHolder> = arrayListOf()
)


/**
 * Marker class for buttons dialogs
 */
@Parcelize
open class SPButtonsDialogHolder : Parcelable

/**
 * Wrapper which allows to pass texts for both button Label and button type with click action
 * for a button model.
 *
 * @property labelTxt applies a button title
 * @property buttonType applies a button type for its style
 * @property clickEvent applies a click action for a button
 */
@Parcelize
class SPDialogInfoHolder(
    val labelTxt: String,
    val buttonType: SPDialogBottomVerticalButton.BottomButtonType,
    val clickEvent: (() -> Unit?)? = null
) : SPButtonsDialogHolder()

/**
 * Wrapper which allows to pass texts for both button Label and button type with click action
 * for a button model.
 *
 * @property labelTxt applies a button title
 * @property buttonType applies a button type for its style
 * @property clickEvent applies a click action for a button
 */
@Parcelize
class SPEditTextDialogInfoHolder(
    val labelTxt: String,
    val buttonType: SPDialogBottomVerticalButton.BottomButtonType,
    val clickEvent: ((String?) -> Unit?)? = null
) : SPButtonsDialogHolder()

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
        @AttrRes override val colorAttr: Int = R.attr.colorAccent
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
        @AttrRes override val colorAttr: Int = R.attr.colorAccent
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
        @AttrRes override val colorAttr: Int = R.attr.colorAccent
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
        @AttrRes override val colorAttr: Int = R.attr.colorAccent
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
        @AttrRes override val colorAttr: Int = R.attr.colorAccent
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
        @AttrRes override val colorAttr: Int = R.attr.colorAccent
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
        @AttrRes override val colorAttr: Int = R.attr.colorAccent
    ) : SPDialogIcon() {

        override val icon: Int =
            R.drawable.ic_picture_24_regular
    }
}