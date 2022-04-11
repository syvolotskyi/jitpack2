package ge.space.ui.components.dialogs.dialog_types

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import ge.space.spaceui.databinding.SpDialogEditTextLayoutBinding
import ge.space.ui.util.extension.argument
import ge.space.ui.util.extension.nonNullArgument
import ge.space.ui.components.dialogs.base.SPBaseDialog
import ge.space.ui.components.dialogs.data.SPDialogDismissHandler
import ge.space.ui.components.dialogs.data.SPEditTextDialogChangeHandler
import ge.space.ui.components.dialogs.data.SPEditTextDialogInfoHolder
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomButtonLayout
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import ge.space.ui.components.text_fields.input.utils.extension.doBeforeTextChanged
import ge.space.ui.util.extension.onClick

/**
 * Dialog with EditText which allows to manipulate next parameters:
 *
 * @property title allows to set top title
 * @property buttonObjects describes dialog bottom buttons
 * @property editTextChange handles text change
 */
class SPDialogEditText : SPBaseDialog<SpDialogEditTextLayoutBinding, SPEditTextDialogInfoHolder>() {

    private val title: String? by argument(KEY_TITLE, null)

    private val editTextChange: SPEditTextDialogChangeHandler? by argument(
        KEY_EDIT_TEXT_CHANGE, null
    )

    override val buttonObjects: Array<SPEditTextDialogInfoHolder> by nonNullArgument(
        KEY_BUTTON_OBJECT,
        arrayOf()
    )

    override val isButtonsMultiple: Boolean
        get() = IS_BUTTON_MULTIPLE

    override val dismissHandler: SPDialogDismissHandler? by argument(KEY_DISMISS, null)

    override fun getViewBinding(): SpDialogEditTextLayoutBinding =
        SpDialogEditTextLayoutBinding.inflate(LayoutInflater.from(context))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleView()
    }

    private fun handleView() {
        with(binding.lytDialogLinear) {
            tvDialogTitle.text = title

            lytButtons.setBottomButtons(
                convertDialogButtonsType()
            )

            etDialog.doBeforeTextChanged { text, _, _, _ ->
                editTextChange?.onChanged?.invoke(
                    text?.toString().orEmpty()
                )
            }
        }
    }

    override fun createDialogButtonModel(buttonObj: SPEditTextDialogInfoHolder) =
        SPDialogBottomButtonLayout.SPDialogBottomButtonModel(
            buttonObj.labelTxt,
            SPDialogBottomVerticalButton.BottomButtonType.valueOf(buttonObj.buttonType.toString())
        ) {
            buttonObj.clickEvent?.invoke(
                binding.lytDialogLinear.etDialog.text.toString()
            )
            dismiss()
        }

    override fun setDismissAction() {
        binding.lytRoot.onClick {
            dismiss()
        }
    }

    companion object {
        const val KEY_EDIT_TEXT_CHANGE = "KEY_EDIT_TEXT_CHANGE"

        private const val IS_BUTTON_MULTIPLE = false
    }
}