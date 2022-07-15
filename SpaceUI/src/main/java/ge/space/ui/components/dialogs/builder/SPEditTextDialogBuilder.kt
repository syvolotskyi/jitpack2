package ge.space.ui.components.dialogs.builder

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import ge.space.ui.components.dialogs.dialog_types.SPDialogEditText
import ge.space.ui.components.dialogs.dialog_types.SPDialogEditText.Companion.KEY_EDIT_TEXT_CHANGE
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_BUTTON_OBJECT
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_DISMISS
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.KEY_TITLE
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.MAX_TWICE_BUTTONS
import ge.space.ui.components.dialogs.base.SPBaseDialog.Companion.MIN_TWICE_BUTTONS
import ge.space.ui.components.dialogs.base.SPBaseDialogBuilder
import ge.space.ui.components.dialogs.data.*

/**
 * Builder class which allows to create [SPDialogEditText]
 */
class SPEditTextDialogBuilder : SPBaseDialogBuilder<SPDialogEditText>() {

    private var title: String? = null
    private var buttons: Array<SPEditTextDialogInfoHolder> = arrayOf()
    private var dismissHandler: SPDialogDismissHandler? = null
    private var onChange: SPEditTextDialogChangeHandler? = null

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
     * Builds [SPDialogEditText] by using properties with keys
     */
    override fun build(): SPDialogEditText =
        SPDialogEditText().apply {
            arguments = bundleOf(
                KEY_TITLE to this@SPEditTextDialogBuilder.title,
                KEY_BUTTON_OBJECT to this@SPEditTextDialogBuilder.buttons,
                KEY_DISMISS to this@SPEditTextDialogBuilder.dismissHandler,
                KEY_EDIT_TEXT_CHANGE to this@SPEditTextDialogBuilder.onChange
            )
        }
}