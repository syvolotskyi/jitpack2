package ge.space.ui.components.dialogs

import androidx.fragment.app.FragmentActivity
import ge.space.ui.components.dialogs.dialog_types.SPDialogEditText
import ge.space.ui.components.dialogs.dialog_types.SPDialog
import ge.space.ui.components.dialogs.builder.SPEditTextDialogBuilder
import ge.space.ui.components.dialogs.builder.SPInfoDialogBuilder
import ge.space.ui.components.dialogs.data.*

/**
 * Creates and shows [SPDialog] by using both a title and a label with multiple buttons
 *
 * @param dialogInfo sets both a title and a label of the dialog with buttons
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showMultipleButtonDialog(
    dialogInfo: SPDialogInfo,
    dialogIcon: SPDialogIcon,
    dismiss: () -> Unit = { }
) {
    buildInfoDialog(
        dialogInfo.title,
        dialogInfo.label,
        true,
        dialogInfo.buttonModels,
        dialogIcon,
        dismiss
    )
        .show(supportFragmentManager, SPDialog::class.java.name)
}

/**
 * Creates and shows [SPDialogEditText] by using both a title and buttons with an EditText
 *
 * @param dialogInfo sets both a title and a label of the dialog with buttons
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showEditTextDialog(
    dialogInfo: SPEditTextDialogInfo,
    dismiss: () -> Unit = { }
) {
    buildEditTextDialog(dialogInfo.title, dialogInfo.buttonModels, dismiss)
        .show(supportFragmentManager, SPDialog::class.java.name)
}

/**
 * Creates and shows [SPDialog] by using both a title and a label with twice buttons
 *
 * @param dialogInfo sets both a title and a label of the dialog with buttons
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showQuestionnaireDialog(
    dialogInfo: SPDialogInfo,
    dialogIcon: SPDialogIcon,
    dismiss: () -> Unit = { }
) {
    buildInfoDialog(
        dialogInfo.title,
        dialogInfo.label,
        false,
        dialogInfo.buttonModels,
        dialogIcon,
        dismiss
    )
        .show(supportFragmentManager, SPDialog::class.java.name)
}

/**
 * Creates and shows [SPDialog] by using both a title and a label
 *
 * @param dialogInfo sets both a title and a label of the dialog
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showStandardInfoDialog(dialogInfo: SPDialogInfo, dismiss: () -> Unit = { }) {
    buildRichTitleDialog(
        title = dialogInfo.title,
        label = dialogInfo.label,
        dismiss = dismiss
    ).show(supportFragmentManager, SPDialog::class.java.name)
}

/**
 * Creates and shows [SPDialog] by using a title
 *
 * @param title sets a title of the dialog
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showTitleDialog(title: String, dismiss: () -> Unit = { }) {
    buildTitleDialog(
        title = title,
        dismiss = dismiss
    )
        .show(supportFragmentManager, SPDialog::class.java.name)
}

/**
 * Creates and shows [SPDialog] by using a label
 *
 * @param label sets a label of the dialog
 * @param dismiss sets a lambda action which the dialog dismissing handles
 */
fun FragmentActivity.showLabelDialog(label: String, dismiss: () -> Unit = { }) {
    buildLabelDialog(
        label = label,
        dismiss = dismiss
    )
        .show(supportFragmentManager, SPDialog::class.java.name)
}

/**
 * Helper extension which helps to build [SPDialog] using [SPDialogData.SPInfoDialogData]
 */
private fun buildInfoDialog(
    title: String?,
    label: String?,
    multiple: Boolean = true,
    buttonModels: ArrayList<SPDialogInfoHolder> = arrayListOf(),
    icon: SPDialogIcon = SPDialogIcon.Info(),
    dismiss: () -> Unit = { }
) =
    SPInfoDialogBuilder()
        .initDialog(
            SPDialogData.SPInfoDialogData(
                title = title,
                label = label,
                buttonMultiple = multiple,
                buttons = buttonModels
            )
        )
        .setIcon(icon)
        .setDismissHandler {
            dismiss()
        }
        .build()

/**
 * Helper extension which helps to build [SPDialog] using [SPDialogData.SPTitleLabelDialogData]
 */
private fun buildRichTitleDialog(
    title: String?,
    label: String?,
    dismiss: () -> Unit = { }
) =
    SPInfoDialogBuilder()
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
 * Helper extension which helps to build [SPDialog] using [SPDialogData.SPTitleDialogData]
 */
private fun buildTitleDialog(
    title: String,
    dismiss: () -> Unit = { }
) =
    SPInfoDialogBuilder()
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
 * Helper extension which helps to build [SPDialog] using [SPDialogData.SPLabelDialogData]
 */
private fun buildLabelDialog(
    label: String,
    dismiss: () -> Unit = { }
) =
    SPInfoDialogBuilder()
        .initDialog(
            SPDialogData.SPLabelDialogData(
                label = label
            )
        )
        .setDismissHandler {
            dismiss()
        }
        .build()

/**
 * Helper extension which helps to build [SPDialogEditText] using [SPEditTextDialogData]
 */
private fun buildEditTextDialog(
    title: String,
    buttons: ArrayList<SPEditTextDialogInfoHolder>,
    dismiss: () -> Unit = { }
) =
    SPEditTextDialogBuilder()
        .initDialog(
            SPEditTextDialogData(
                title = title,
                buttons = buttons
            )
        )
        .setDismissHandler {
            dismiss()
        }
        .build()