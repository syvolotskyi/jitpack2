package ge.space.ui.view.dialog.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import ge.space.spaceui.R
import ge.space.ui.view.dialog.data.SPDialogDismissHandler

/**
 * Abstract base Dialog extended from [DialogFragment] that allows to change its configuration.
 * It has to be extended to apply ViewBinding.
 *
 * @property binding [VB] has to be realized by overriding getViewBinding() method
 * @property dismissHandler every button click triggers dialog dismissing and then this
 * action can be handled
 */
abstract class SPBaseDialog<VB : ViewBinding> : DialogFragment() {

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

    override fun getTheme(): Int = R.style.SPBaseDialog

    /**
     * Allows to set a dialog dismiss action
     */
    protected abstract fun setDismissAction()

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB
}