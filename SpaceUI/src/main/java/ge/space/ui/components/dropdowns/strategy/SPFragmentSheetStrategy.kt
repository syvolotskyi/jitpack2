package ge.space.ui.components.dropdowns.strategy

import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ge.space.spaceui.R
import ge.space.ui.util.extension.inflate

/**
 * Fragment strategy realization of [SPBottomSheetStrategy]
 */

class SPFragmentSheetStrategy(
    private val fragment: Fragment,
    private val fm: FragmentManager
) : SPBottomSheetStrategy {

    private var helperFragment: SPBottomMenuHelperFragment? = null
    private var childFragmentManager: FragmentManager? = null

    private fun initFragmentState(container: LinearLayout): Boolean {

        if (helperFragment == null) {
            val frag = container.inflate(R.layout.sp_bottom_menu_dialog_fragment_hepler)
            container.addView(frag)
            helperFragment =
                fm.fragments.find { it is SPBottomMenuHelperFragment } as? SPBottomMenuHelperFragment
            childFragmentManager = helperFragment?.childFragmentManager
        }
        if (helperFragment == null) return false
        return true
    }

    override fun onCreate(container: LinearLayout, dismissEvent: () -> Unit) {

//            startState = SPSlidingUpPanelLayout.PanelState.EXPANDED
        if (!initFragmentState(container)) return
        val helperViewId = helperFragment?.view?.id ?: return

        if (fragment is SPBottomMenuHelperFragment.SPBottomMenuNavFragment) {
            fragment.setBottomMenuNavInterface(fragmentHandler)
        }
        helperFragment?.childFragmentManager?.beginTransaction()?.add(helperViewId, fragment)
            ?.commitNow()
//        willslideAfterShow = true
    }

    private val fragmentHandler = object : SPBottomMenuHelperFragment.SPBottomMenuListener {

        override fun dismissDialog(isDataChanged: Boolean) {
            /*  if (isDataChanged) {
                  menuListener?.let { it(resultPosition, isDataChanged) }
              }
              hideBottomMenuDialog()*/
        }


        override fun showFragment(fragment: Fragment) {
            if (fragment is SPBottomMenuHelperFragment.SPBottomMenuNavFragment) {
                fragment.setBottomMenuNavInterface(this)
            }
            val helperViewId = helperFragment?.view?.id ?: return
            childFragmentManager?.beginTransaction()?.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.exit_to_right
            )?.add(helperViewId, fragment)?.addToBackStack(fragment::class.java.simpleName)
                ?.commit()
        }

        override fun popBackStack() {
            childFragmentManager?.popBackStack()
        }
    }

}