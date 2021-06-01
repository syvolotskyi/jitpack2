package ge.space.ui.view.dialog.data

import ge.space.ui.view.dialog.SPInfoDialog

/**
 * Allows to wrap data for creating [SPInfoDialog]
 *
 * @property title applies a title of the dialog
 * @property label applies a label of the dialog
 * @property buttonModels applies a list of buttons
 */
data class SPDialogInfo(
    val title: String,
    val label: String,
    val buttonModels: ArrayList<SPDialogInfoHolder> = arrayListOf()
)