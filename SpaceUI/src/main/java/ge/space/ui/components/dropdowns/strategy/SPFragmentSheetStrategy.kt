package ge.space.ui.components.dropdowns.strategy

import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ge.space.spaceui.R
import ge.space.ui.util.extension.inflate

/**
 * Fragment strategy realization of [SPBottomSheetStrategy]
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
        container: LinearLayout,
        dismissEvent: (Data?) -> Unit
    ) {

        if (fragment is SPBottomSheetResultListener<*>) {
            fragment.setBottomSheetResult { data ->
                (data as? Data)?.let { dismissEvent(it) }
            }
        }
        fm.beginTransaction().add(container.id, fragment)
            .commitNow()
    }
}