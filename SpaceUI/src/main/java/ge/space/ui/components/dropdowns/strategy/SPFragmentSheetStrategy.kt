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

    private var helperFragment: SPBottomMenuHelperFragment? = null
    private var childFragmentManager: FragmentManager? = null

 /*   private fun initFragmentState(container: LinearLayout): Boolean {
        if (helperFragment == null) {
            val frag = container.inflate(R.layout.sp_bottom_menu_dialog_fragment_hepler)
            container.addView(frag)
            helperFragment =
                fm.fragments.find { it is SPBottomMenuHelperFragment } as? SPBottomMenuHelperFragment
            childFragmentManager = helperFragment?.childFragmentManager
        }
        if (helperFragment == null) return false
        return true
    }*/

    override fun onCreate(
         fm: FragmentManager,container: LinearLayout, dismissEvent: () -> Unit) {

//            startState = SPSlidingUpPanelLayout.PanelState.EXPANDED

        if (fragment is SPBottomSheetResultListener<*>) {
            fragment.setBottomSheetResult { data ->
                (data as? Data)?.let { onResult(it) }
                dismissEvent()
            }
        }
        fm?.beginTransaction()?.add(container.id, fragment)
            ?.commitNow()
    }
}