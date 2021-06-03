package ge.space.ui.view.dialog.builder

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import ge.space.ui.view.dialog.SPInfoDialog
import ge.space.ui.view.dialog.SPInfoDialog.Companion.MINIMUM_TWICE_BUTTONS
import ge.space.ui.view.dialog.base.SPBaseDialogBuilder
import ge.space.ui.view.dialog.data.SPDialogData
import ge.space.ui.view.dialog.data.SPDialogDismissHandler
import ge.space.ui.view.dialog.data.SPDialogInfoHolder

/**
 * Builder class which allows to create [SPInfoDialog]
 */
class SPInfoDialogBuilder(
    activity: FragmentActivity
) : SPBaseDialogBuilder<SPInfoDialog>(activity) {

    private var title: String? = null
    private var label: String? = null
    private var titleVisible: Boolean = true
    private var labelVisible: Boolean = true
    private var infoIconVisible: Boolean = true
    private var buttonsVisible: Boolean = true
    private var isMultiple: Boolean = false
    private var buttons: Array<SPDialogInfoHolder> = arrayOf()
    private var dismissHandler: SPDialogDismissHandler? = null

    /**
     * Defines data for the dialog bottom buttons visibility
     */
    fun setDismissHandler(onDismissed: () -> Unit): SPInfoDialogBuilder {
        this.dismissHandler = SPDialogDismissHandler(onDismissed)
        return this
    }

    /**
     * Dialog initializing by passing [SPDialogData]
     *
     * @param dialog allows to configure SPInfoDialog by using specific parameters
     */
    fun initDialog(dialog: SPDialogData): SPInfoDialogBuilder {
        handleDialog(dialog)

        return this
    }

    private fun handleDialog(dialog: SPDialogData) {
        when (dialog) {
            is SPDialogData.SPInfoDialogData -> initInfoDialog(dialog)
            is SPDialogData.SPTitleLabelDialogData -> initTitleLabelDialog(dialog)
            is SPDialogData.SPTitleDialogData -> initTitleDialog(dialog)
            is SPDialogData.SPLabelDialogData -> initLabelDialog(dialog)
        }
    }

    private fun initInfoDialog(dialog: SPDialogData.SPInfoDialogData) {
        titleVisible = dialog.title?.let {
            title = dialog.title
            true
        } ?: false
        labelVisible = dialog.label?.let {
            label = dialog.label
            true
        } ?: false
        setButtons(dialog.buttonMultiple, dialog.buttons.toTypedArray())
    }

    private fun initTitleLabelDialog(dialog: SPDialogData.SPTitleLabelDialogData) {
        title = dialog.title
        label = dialog.label
        infoIconVisible = false
        buttonsVisible = false
    }

    private fun initTitleDialog(dialog: SPDialogData.SPTitleDialogData) {
        title = dialog.title
        labelVisible = false
        infoIconVisible = false
        buttonsVisible = false
    }

    private fun initLabelDialog(dialog: SPDialogData.SPLabelDialogData) {
        label = dialog.label
        titleVisible = false
        infoIconVisible = false
        buttonsVisible = false
    }

    /**
     * Defines data for the dialog bottom buttons. It applies next params
     *
     * @param multiple applies if the dialog bottom buttons is multiple. By default it's false
     * and it means that the dialog bottom buttons type is twice - for the right button and
     * for the left one.
     * @param buttons applies button models
     * @throws IllegalStateException if the dialog bottom button type is twice and the [pairs]
     * count is less then [MINIMUM_TWICE_BUTTONS] the exception throws because there are no any
     * possibilities to add both right button and left one.
     */
    private fun setButtons(
        multiple: Boolean = false,
        buttons: Array<SPDialogInfoHolder>
    ) {
        this.isMultiple = multiple

        if (!isMultiple && buttons.count() < MINIMUM_TWICE_BUTTONS) {
            throw IllegalStateException("Pairs parameter has to contain at least $MINIMUM_TWICE_BUTTONS elements for using Twice button type")
        }

        this.buttons = buttons
    }

    /**
     * Builds [SPInfoDialog] by using properties with keys
     */
    override fun build(): SPInfoDialog =
        SPInfoDialog().apply {
            arguments = bundleOf(
                SPInfoDialog.KEY_TITLE to this@SPInfoDialogBuilder.title,
                SPInfoDialog.KEY_LABEL to this@SPInfoDialogBuilder.label,
                SPInfoDialog.KEY_INFO_ICON_VISIBLE to this@SPInfoDialogBuilder.infoIconVisible,
                SPInfoDialog.KEY_TITLE_VISIBLE to this@SPInfoDialogBuilder.titleVisible,
                SPInfoDialog.KEY_LABEL_VISIBLE to this@SPInfoDialogBuilder.labelVisible,
                SPInfoDialog.KEY_BUTTONS_VISIBLE to this@SPInfoDialogBuilder.buttonsVisible,
                SPInfoDialog.KEY_MULTIPLE to this@SPInfoDialogBuilder.isMultiple,
                SPInfoDialog.KEY_BUTTON_OBJECT to this@SPInfoDialogBuilder.buttons,
                SPInfoDialog.KEY_DISMISS to this@SPInfoDialogBuilder.dismissHandler,
            )
        }
}