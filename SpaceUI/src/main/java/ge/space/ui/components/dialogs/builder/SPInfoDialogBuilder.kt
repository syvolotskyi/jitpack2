package ge.space.ui.components.dialogs.builder

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import ge.space.ui.components.dialogs.dialog_types.SPDialog
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_BUTTONS_VISIBLE
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_BUTTON_OBJECT
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_DISMISS
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_INFO_ICON_VISIBLE
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_LABEL
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_LABEL_VISIBLE
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_MULTIPLE
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_TITLE
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_TITLE_VISIBLE
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.MAX_TWICE_BUTTONS
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.MIN_TWICE_BUTTONS
import ge.space.ui.components.dialogs.base.SPBaseDialogBuilder
import ge.space.ui.components.dialogs.data.SPDialogData
import ge.space.ui.components.dialogs.data.SPDialogDismissHandler
import ge.space.ui.components.dialogs.data.SPDialogInfoHolder

/**
 * Builder class which allows to create [SPDialog]
 */
class SPInfoDialogBuilder(
    activity: FragmentActivity
) : SPBaseDialogBuilder<SPDialog>(activity) {

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
        when(dialog) {
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
     * count is less then [MIN_TWICE_BUTTONS] the exception throws because there are no any
     * possibilities to add both right button and left one.
     */
    private fun setButtons(
        multiple: Boolean = false,
        buttons: Array<SPDialogInfoHolder>
    ) {
        this.isMultiple = multiple

        if (!isMultiple && buttons.count() < MIN_TWICE_BUTTONS) {
            throw IllegalStateException("Pairs parameter has to contain at least $MIN_TWICE_BUTTONS elements for using Twice button type")
        }

        if (buttons.count() > MAX_TWICE_BUTTONS) {
            throw IllegalStateException("buttons parameter has to contain at most $MIN_TWICE_BUTTONS elements")
        }

        this.buttons = buttons
    }

    /**
     * Builds [SPDialog] by using properties with keys
     */
    override fun build(): SPDialog =
        SPDialog().apply {
            arguments = bundleOf(
                KEY_TITLE to this@SPInfoDialogBuilder.title,
                KEY_LABEL to this@SPInfoDialogBuilder.label,
                KEY_INFO_ICON_VISIBLE to this@SPInfoDialogBuilder.infoIconVisible,
                KEY_TITLE_VISIBLE to this@SPInfoDialogBuilder.titleVisible,
                KEY_LABEL_VISIBLE to this@SPInfoDialogBuilder.labelVisible,
                KEY_BUTTONS_VISIBLE to this@SPInfoDialogBuilder.buttonsVisible,
                KEY_MULTIPLE to this@SPInfoDialogBuilder.isMultiple,
                KEY_BUTTON_OBJECT to this@SPInfoDialogBuilder.buttons,
                KEY_DISMISS to this@SPInfoDialogBuilder.dismissHandler,
            )
        }
}