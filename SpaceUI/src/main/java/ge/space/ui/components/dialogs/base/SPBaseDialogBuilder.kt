package ge.space.ui.components.dialogs.base

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

/**
 * Base implementation of popup dialog builder
 */
abstract class SPBaseDialogBuilder<T : DialogFragment>(
    private val activity: FragmentActivity
) {

    /**
     * Builds target popup fragment for showing
     */
    abstract fun build(): T

    /**
     * Initialize [SPBaseDialogBuilder] object and than show popup dialog
     */
    fun show(tag: String) {
        try {
            if (activity.supportFragmentManager.findFragmentByTag(tag) == null) {
                build().show(activity.supportFragmentManager, tag)
            }
        } catch (ignored: IllegalStateException) {
            ignored.printStackTrace()
        }
    }
}