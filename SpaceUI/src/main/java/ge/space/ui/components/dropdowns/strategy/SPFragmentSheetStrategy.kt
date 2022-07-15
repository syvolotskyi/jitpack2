package ge.space.ui.components.dropdowns.strategy

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ge.space.ui.components.dropdowns.core.SPBottomSheetResultListener

/**
 * Fragment strategy realization of [SPBottomSheetStrategy]
 * Data is onResult return type
 *
 * @param fragment [Fragment] which should be inflated in container
 * @param onResult: [(Data) -> Unit] calls when dialog is dismissed
 */

class SPFragmentSheetStrategy<Data>(
    private val fragment: Fragment
) : SPBottomSheetStrategy<Data> {

    /**
     * Calls for initializing strategy
     *
     * @param fm [FragmentManager] is supportFragmentManager
     * @param container [LinearLayout] is parent container
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    override fun onCreate(
        fm: FragmentManager,
        container: ViewGroup,
        dismissEvent: (Data?) -> Unit
    ) {

        if (fragment is SPBottomSheetResultListener<*>) {
            fragment.onResult { data ->
                (data as? Data)?.let { dismissEvent(it) }
            }
        }
        fm.beginTransaction().add(container.id, fragment)
            .commitNow()
    }
}