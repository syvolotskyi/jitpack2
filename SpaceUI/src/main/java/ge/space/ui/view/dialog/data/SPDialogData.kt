package ge.space.ui.view.dialog.data

import ge.space.ui.view.dialog.SPInfoDialog

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
        val title: String,
        val label: String
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