package ge.space.ui.components.dialogs.base

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

/**
 * Base implementation of popup dialog builder
 */
abstract class SPBaseDialogBuilder<T : DialogFragment>{

    /**
     * Builds target popup fragment for showing
     */
    abstract fun build(): T
}