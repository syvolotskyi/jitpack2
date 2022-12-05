package ge.space.ui.components.dialogs.builder

import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.MAX_TWICE_BUTTONS
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.MIN_TWICE_BUTTONS
import ge.space.ui.components.dialogs.base.SPBaseDialogBuilder
import ge.space.ui.components.dialogs.data.*
import ge.space.ui.components.dialogs.dialog_types.SPDialogEditText

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class SPEditTextDialogDsl

/**
 * Builder class which allows to create [SPDialogEditText]
 */
@SPEditTextDialogDsl
class SPEditTextDialogBuilder : SPBaseDialogBuilder<SPDialogEditText>() {

    internal var title: String? = null
    internal var buttons: Array<SPEditTextDialogInfoHolder> = arrayOf()
    internal var dismissHandler: SPDialogDismissHandler? = null
    internal var onChange: SPEditTextDialogChangeHandler? = null

    /**
     * Defines data for the dialog bottom buttons visibility
     */
    fun setDismissHandler(onDismissed: () -> Unit): SPEditTextDialogBuilder {
        dismissHandler = SPDialogDismissHandler(onDismissed)
        return this
    }

    /**
     * Defines data for the dialog bottom buttons visibility
     */
    fun setOnChanged(onChange: (String) -> Unit): SPEditTextDialogBuilder {
        this.onChange = SPEditTextDialogChangeHandler(onChange)
        return this
    }

    /**
     * Dialog initializing by passing [SPDialogData]
     *
     * @param dialog allows to configure SPInfoDialog by using specific parameters
     */
    fun initDialog(dialog: SPEditTextDialogData): SPEditTextDialogBuilder {
        title = dialog.title
        setButtons(dialog.buttons.toTypedArray())

        return this
    }

    /**
     * Defines data for the dialog bottom buttons. It applies next params
     *
     * @param buttons applies button models
     * @throws IllegalStateException if the dialog bottom button type is twice and the [pairs]
     * count is less then [MIN_TWICE_BUTTONS] the exception throws because there are no any
     * possibilities to add both right button and left one.
     */
    private fun setButtons(
        buttons: Array<SPEditTextDialogInfoHolder>
    ) {
        if (buttons.count() < MIN_TWICE_BUTTONS) {
            throw IllegalStateException("buttons parameter has to contain at least $MIN_TWICE_BUTTONS elements for using Twice button type")
        }

        if (buttons.count() > MAX_TWICE_BUTTONS) {
            throw IllegalStateException("buttons parameter has to contain at most $MIN_TWICE_BUTTONS elements")
        }

        this.buttons = buttons
    }

    /**
     * Builds [SPDialogEditText] by using constructor
     */
    override fun build(): SPDialogEditText = SPDialogEditText(this)

}