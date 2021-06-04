package ge.space.ui.view.dialog.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import ge.space.spaceui.R
import ge.space.ui.view.dialog.data.SPButtonsDialogHolder
import ge.space.ui.view.dialog.data.SPDialogDismissHandler
import ge.space.ui.view.dialog.view.SPDialogBottomButtonLayout

/**
 * Abstract base Dialog extended from [DialogFragment] that allows to change its configuration.
 * It has to be extended to apply ViewBinding.
 *
 * @property binding [VB] has to be realized by overriding getViewBinding() method
 * @property dismissHandler every button click triggers dialog dismissing and then this
 * action can be handled
 *
 * @param VB keeps ViewBinding type
 * @param BT keeps SPButtonsDialogHolder type
 */
abstract class SPBaseDialog<VB : ViewBinding, BT : SPButtonsDialogHolder> : DialogFragment() {

    /**
     * Reference to [VB] instance which is related to ViewBinding
     */
    protected lateinit var binding: VB
        private set

    /**
     * Lazy property for initialize ViewBinding in constructor
     */
    private val _binding by lazy {
        getViewBinding()
    }

    /**
     * Lazy property for initialize Dialog dismiss handler
     */
    protected abstract val dismissHandler: SPDialogDismissHandler?

    /**
     * Lazy abstract property for button positions
     */
    protected abstract val isButtonsMultiple: Boolean

    /**
     * Lazy abstract property for button models
     */
    protected abstract val buttonObjects: Array<BT>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = _binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDismissAction()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        dismissHandler?.onDismissed?.invoke()
    }

    protected fun convertDialogButtonsType(): SPDialogBottomButtonLayout.SPDialogBottomButton =
        if (isButtonsMultiple) {
            SPDialogBottomButtonLayout.SPDialogBottomButton.SPDialogBottomButtonMultiple(
                getMultipleButtons()
            )
        } else {
            SPDialogBottomButtonLayout.SPDialogBottomButton.SPDialogBottomButtonTwice(
                createDialogButtonModel(buttonObjects[LEFT_PAIR_INDEX]),
                createDialogButtonModel(buttonObjects[RIGHT_PAIR_INDEX]),
            )
        }

    private fun getMultipleButtons(): List<SPDialogBottomButtonLayout.SPDialogBottomButtonModel> {
        val buttons = mutableListOf<SPDialogBottomButtonLayout.SPDialogBottomButtonModel>()
        buttonObjects.forEach {
            buttons.add(
                createDialogButtonModel(it)
            )
        }

        return buttons
    }

    override fun getTheme(): Int = R.style.SPBaseDialog

    /**
     * Creates a specific button model
     */
    protected abstract fun createDialogButtonModel(buttonObj: BT)
        : SPDialogBottomButtonLayout.SPDialogBottomButtonModel

    /**
     * Allows to set a dialog dismiss action
     */
    protected abstract fun setDismissAction()

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB

    companion object {
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_LABEL = "KEY_LABEL"
        const val KEY_INFO_ICON_VISIBLE = "KEY_INFO_ICON_VISIBLE"
        const val KEY_TITLE_VISIBLE = "KEY_TITLE_VISIBLE"
        const val KEY_LABEL_VISIBLE = "KEY_LABEL_VISIBLE"
        const val KEY_BUTTONS_VISIBLE = "KEY_BUTTONS_VISIBLE"
        const val KEY_MULTIPLE = "KEY_MULTIPLE"
        const val KEY_BUTTON_OBJECT = "KEY_BUTTON_OBJECT"
        const val KEY_DISMISS = "KEY_DISMISS"

        const val LEFT_PAIR_INDEX = 0
        const val RIGHT_PAIR_INDEX = 1

        const val MIN_TWICE_BUTTONS = 2
        const val MAX_TWICE_BUTTONS = 5
    }
}