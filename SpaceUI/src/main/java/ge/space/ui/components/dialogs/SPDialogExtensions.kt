package ge.space.ui.components.dialogs

import androidx.fragment.app.FragmentActivity
import ge.space.ui.components.dialogs.dialog_types.SPDialogEditText
import ge.space.ui.components.dialogs.dialog_types.SPDialog
import ge.space.ui.components.dialogs.builder.SPEditTextDialogBuilder
import ge.space.ui.components.dialogs.builder.SPInfoDialogBuilder
import ge.space.ui.components.dialogs.data.*
import ge.space.ui.components.dialogs.dialog_types.SPDialog.Companion.dialog
import ge.space.ui.components.dialogs.dialog_types.SPDialogEditText.Companion.dialogEditText

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
) = dialog {

        initDialog(
            SPDialogData.SPInfoDialogData(
                title = title,
                label = label,
                buttonMultiple = multiple,
                buttons = buttonModels
            )
        )
        setIcon(icon)
        setDismissHandler {
            dismiss()
        }
}

/**
 * Helper extension which helps to build [SPDialog] using [SPDialogData.SPTitleLabelDialogData]
 */
private fun buildRichTitleDialog(
    title: String?,
    label: String?,
    dismiss: () -> Unit = { }
) = dialog {
    initDialog(
        SPDialogData.SPTitleLabelDialogData(
            title = title,
            label = label
        )
    )
    setDismissHandler { dismiss() }
}

/**
 * Helper extension which helps to build [SPDialog] using [SPDialogData.SPTitleDialogData]
 */
private fun buildTitleDialog(
    title: String,
    dismiss: () -> Unit = { }
) = dialog {
    initDialog(SPDialogData.SPTitleDialogData(title = title))
    setDismissHandler { dismiss() }
}

/**
 * Helper extension which helps to build [SPDialog] using [SPDialogData.SPLabelDialogData]
 */
private fun buildLabelDialog(
    label: String,
    dismiss: () -> Unit = { }
) = dialog {
    initDialog(SPDialogData.SPLabelDialogData(label = label))
    setDismissHandler { dismiss() }
}


/**
 * Helper extension which helps to build [SPDialogEditText] using [SPEditTextDialogData]
 */
private fun buildEditTextDialog(
    title: String,
    buttons: ArrayList<SPEditTextDialogInfoHolder>,
    dismiss: () -> Unit = { }
) =
    dialogEditText {
        initDialog(SPEditTextDialogData(title = title, buttons = buttons))
        setDismissHandler { dismiss() }
    }