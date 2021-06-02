package ge.space.ui.view.dialog.data

import android.os.Parcelable
import ge.space.ui.view.dialog.SPInfoDialog
import ge.space.ui.view.dialog.view.SPDialogBottomVerticalButton
import kotlinx.android.parcel.Parcelize

/**
 * Sealed class which allows to init [SPInfoDialog]
 */
sealed class SPDialogData {
    /**
     * Allows to init default [SPInfoDialog] with icon, buttons, title and label
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
     * Allows to init [SPInfoDialog] just with the top and the second titles
     *
     * @property title applies the top title
     * @property label applies the second title
     */
    data class SPTitleLabelDialogData(
        val title: String?,
        val label: String?
    ) : SPDialogData()

    /**
     * Allows to init [SPInfoDialog] just with the top title
     *
     * @property title applies the top title
     */
    data class SPTitleDialogData(
        val title: String
    ) : SPDialogData()

    /**
     * Allows to init [SPInfoDialog] just with the second title
     *
     * @property label applies the second title
     */
    data class SPLabelDialogData(
        val label: String
    ) : SPDialogData()
}

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
 * Allows to wrap data for creating [SPInfoDialog]
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
) : Parcelable