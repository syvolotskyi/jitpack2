package ge.space.ui.components.dropdowns.strategy

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Fragment strategy realization of [SPBottomSheetStrategy]
 *
 * @param fragment [Fragment] which should be inflated in container
 * @param onResult: [(Data) -> Unit] calls when dialog is dismissed
 */

class SPFragmentSheetStrategy<Data>(
    private val fragment: Fragment,
    private val onResult: (Data) -> Unit
) : SPBottomSheetStrategy {

    /**
     * Calls for creation a content in bottom sheet fragment
     *
     */
    override fun onCreate(
        fm: FragmentManager,
        container: ViewGroup,
        dismissEvent: () -> Unit
    ) {

        if (fragment is SPBottomSheetResultListener<*>) {
            fragment.onResult { data ->
                (data as? Data)?.let { onResult(it) }
                dismissEvent()
            }
        }
        fm.beginTransaction().add(container.id, fragment)
            .commitNow()
    }
}