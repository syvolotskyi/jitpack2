package ge.space.ui.components.dropdowns.strategy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ge.space.spaceui.R

class SPBottomMenuHelperFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sp_map_menu_view, container, false)
    }

    interface SPBottomMenuListener {
        fun showFragment(fragment: Fragment)
        fun popBackStack()
        fun dismissDialog(isDataChanged: Boolean = false)
    }

    interface SPBottomMenuNavFragment {
        fun setBottomMenuNavInterface(listener: SPBottomMenuListener)
    }

}
