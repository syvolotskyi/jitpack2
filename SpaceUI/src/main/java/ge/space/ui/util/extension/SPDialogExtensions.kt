package ge.space.ui.util.extension

import androidx.fragment.app.FragmentActivity
import ge.space.spaceui.R
import ge.space.ui.view.button.SPButton
import ge.space.ui.view.dialog.SPInfoDialog
import ge.space.ui.view.dialog.builder.SPInfoDialogBuilder
import ge.space.ui.view.dialog.data.SPDialogData
import ge.space.ui.view.dialog.data.SPDialogInfo
import ge.space.ui.view.dialog.data.SPDialogInfoHolder
import ge.space.ui.view.dialog.view.SPDialogBottomVerticalButton

/**
 * Sets a dialog bottom button style by a specific type
 *
 * @param type [SPDialogBottomVerticalButton.BottomButtonType] keeps a type for
 * a specific style
 */
fun SPButton.bottomType(type : SPDialogBottomVerticalButton.BottomButtonType) {
    when(type) {
        SPDialogBottomVerticalButton.BottomButtonType.Default ->
            setButtonStyle(
                R.style.SPBaseView_SPBaseButton_Medium_Transparent
            )
        SPDialogBottomVerticalButton.BottomButtonType.Remove ->
            setButtonStyle(
                R.style.SPBaseView_SPBaseButton_Medium_Transparent_Remove
            )
        SPDialogBottomVerticalButton.BottomButtonType.Cancel ->
            setButtonStyle(
                R.style.SPBaseView_SPBaseButton_Medium_Transparent_Cancel
            )
    }
}

/**
 * Creates and shows [SPInfoDialog] by using both a title and a label with multiple buttons
 *
 * @param dialogInfo sets both a title and a label of the dialog with buttons
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showMultipleDialog(dialogInfo: SPDialogInfo, dismiss: () -> Unit = { }) {
    buildInfoDialog(dialogInfo.title, dialogInfo.label, true, dialogInfo.buttonModels, dismiss)
        .show(supportFragmentManager, SPInfoDialog::class.java.name)
}

/**
 * Creates and shows [SPInfoDialog] by using both a title and a label with twice buttons
 *
 * @param dialogInfo sets both a title and a label of the dialog with buttons
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showTwiceDialog(dialogInfo: SPDialogInfo, dismiss: () -> Unit = { }) {
    buildInfoDialog(dialogInfo.title, dialogInfo.label, false, dialogInfo.buttonModels, dismiss)
        .show(supportFragmentManager, SPInfoDialog::class.java.name)
}

/**
 * Creates and shows [SPInfoDialog] by using both a title and a label
 *
 * @param dialogInfo sets both a title and a label of the dialog
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showRichTitleDialog(dialogInfo: SPDialogInfo, dismiss: () -> Unit = { }) {
    buildRichTitleDialog(
        title = dialogInfo.title,
        label = dialogInfo.label,
        dismiss = dismiss
    )
        .show(supportFragmentManager, SPInfoDialog::class.java.name)
}

/**
 * Creates and shows [SPInfoDialog] by using a title
 *
 * @param title sets a title of the dialog
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showTitleDialog(title: String, dismiss: () -> Unit = { }) {
    buildTitleDialog(
        title = title,
        dismiss = dismiss
    )
        .show(supportFragmentManager, SPInfoDialog::class.java.name)
}

/**
 * Creates and shows [SPInfoDialog] by using a label
 *
 * @param label sets a label of the dialog
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showLabelDialog(label: String, dismiss: () -> Unit = { }) {
    buildLabelDialog(
        label = label,
        dismiss = dismiss
    )
        .show(supportFragmentManager, SPInfoDialog::class.java.name)
}

/**
 * Helper extension which helps to build [SPInfoDialog] using [SPDialogData.SPInfoDialogData]
 */
private fun FragmentActivity.buildInfoDialog(
    title: String,
    label: String,
    multiple: Boolean = true,
    buttonModels: ArrayList<SPDialogInfoHolder> = arrayListOf(),
    dismiss: () -> Unit = { }
) =
    SPInfoDialogBuilder(this)
        .initDialog(
            SPDialogData.SPInfoDialogData(
                title = title,
                label = label,
                buttonMultiple = multiple,
                buttons = buttonModels
            )
        )
        .setDismissHandler {
            dismiss()
        }
        .build()

/**
 * Helper extension which helps to build [SPInfoDialog] using [SPDialogData.SPTitleLabelDialogData]
 */
private fun FragmentActivity.buildRichTitleDialog(
    title: String,
    label: String,
    dismiss: () -> Unit = { }
) =
    SPInfoDialogBuilder(this)
        .initDialog(
            SPDialogData.SPTitleLabelDialogData(
                title = title,
                label = label
            )
        )
        .setDismissHandler {
            dismiss()
        }
        .build()

/**
 * Helper extension which helps to build [SPInfoDialog] using [SPDialogData.SPTitleDialogData]
 */
private fun FragmentActivity.buildTitleDialog(
    title: String,
    dismiss: () -> Unit = { }
) =
    SPInfoDialogBuilder(this)
        .initDialog(
            SPDialogData.SPTitleDialogData(
                title = title
            )
        )
        .setDismissHandler {
            dismiss()
        }
        .build()

/**
 * Helper extension which helps to build [SPInfoDialog] using [SPDialogData.SPLabelDialogData]
 */
private fun FragmentActivity.buildLabelDialog(
    label: String,
    dismiss: () -> Unit = { }
) =
    SPInfoDialogBuilder(this)
        .initDialog(
            SPDialogData.SPLabelDialogData(
                label = label
            )
        )
        .setDismissHandler {
            dismiss()
        }
        .build()