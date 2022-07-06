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
    private val fragment: Fragment,
    private val onResult: (Data) -> Unit
) : SPBottomSheetStrategy {

    /**
     * Calls for creation a content in bottom sheet fragment
     *
     * @param container [LinearLayout] for content view
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    override fun onCreate(
        fm: FragmentManager,
        container: LinearLayout,
        dismissEvent: () -> Unit
    ) {

        if (fragment is SPBottomSheetResultListener<*>) {
            fragment.setBottomSheetResult { data ->
                (data as? Data)?.let { onResult(it) }
                dismissEvent()
            }
        }
        fm.beginTransaction().add(container.id, fragment)
            .commitNow()
    }
}