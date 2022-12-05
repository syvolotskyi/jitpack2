package ge.space.ui.components.dialogs.builder

import ge.space.ui.components.dialogs.dialog_types.SPDialog
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.MAX_TWICE_BUTTONS
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.MIN_TWICE_BUTTONS
import ge.space.ui.components.dialogs.base.SPBaseDialogBuilder
import ge.space.ui.components.dialogs.data.SPDialogData
import ge.space.ui.components.dialogs.data.SPDialogDismissHandler
import ge.space.ui.components.dialogs.data.SPDialogIcon
import ge.space.ui.components.dialogs.data.SPDialogInfoHolder

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class SPInfoDialogDsl
/**
 * Builder class which allows to create [SPDialog]
 */
@SPEditTextDialogDsl
class SPInfoDialogBuilder : SPBaseDialogBuilder<SPDialog>() {

    internal var title: String? = null
    internal var label: String? = null
    internal var titleVisible: Boolean = true
    internal var labelVisible: Boolean = true
    internal var infoIconVisible: Boolean = true
    internal var buttonsVisible: Boolean = true
    internal var isMultiple: Boolean = false
    internal var buttons: Array<SPDialogInfoHolder> = arrayOf()
    internal var dismissHandler: SPDialogDismissHandler? = null
    internal var dialogIcon: SPDialogIcon = SPDialogIcon.Info()

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

    /**
     * Dialog icon changing by passing [SPDialogIcon]
     *
     * @param icon configures an icon with a color
     */
    fun setIcon(icon: SPDialogIcon): SPInfoDialogBuilder {
        changeIcon(icon)

        return this
    }

    private fun changeIcon(icon: SPDialogIcon) {
        dialogIcon = icon
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
     * Builds [SPDialog] by using constructor
     */
    override fun build(): SPDialog = SPDialog(this)
}