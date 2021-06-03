package ge.space.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.addTextChangedListener
import ge.space.spaceui.databinding.SpEditTextDialogBinding
import ge.space.ui.util.extension.argument
import ge.space.ui.util.extension.nonNullArgument
import ge.space.ui.view.dialog.base.SPBaseDialog
import ge.space.ui.view.dialog.data.SPDialogDismissHandler
import ge.space.ui.view.dialog.data.SPDialogInfoHolder
import ge.space.ui.view.dialog.data.SPEditTextDialogChangeHandler

/**
 * Dialog with EditText which allows to manipulate next parameters:
 *
 * @property title allows to set top title
 * @property buttonObjects describes dialog bottom buttons
 * @property editTextChange handles text change
 */
class SPEditTextDialog : SPBaseDialog<SpEditTextDialogBinding>() {

    private val title: String? by argument(KEY_TITLE, null)

    private val editTextChange: SPEditTextDialogChangeHandler? by argument(
        KEY_EDIT_TEXT_CHANGE, null
    )

    override val buttonObjects: Array<SPDialogInfoHolder> by nonNullArgument(
        KEY_BUTTON_OBJECT,
        arrayOf()
    )

    override val isButtonsMultiple: Boolean
        get() = IS_BUTTON_MULTIPLE

    override val dismissHandler: SPDialogDismissHandler? by argument(KEY_DISMISS, null)

    override fun getViewBinding(): SpEditTextDialogBinding =
        SpEditTextDialogBinding.inflate(LayoutInflater.from(context))

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

            etDialog.addTextChangedListener {
                editTextChange?.onChanged?.invoke(
                    it?.toString().orEmpty()
                )
            }
        }
    }

    override fun setDismissAction() {
        binding.lytRoot.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val KEY_EDIT_TEXT_CHANGE = "KEY_EDIT_TEXT_CHANGE"

        private const val IS_BUTTON_MULTIPLE = false
    }
}