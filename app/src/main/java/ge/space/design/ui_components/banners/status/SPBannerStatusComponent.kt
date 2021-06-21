package ge.space.design.ui_components.banners.status

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewpager2.widget.ViewPager2
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpBannerStatusShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.banners.SPBannerStatus

class SPBannerStatusComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banner_status

    override fun getDescriptionResId(): Int = R.string.component_banner_status_description

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val layoutBinding = SpBannerStatusShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )

            val banners = SPBannerStatusStyles.list

            layoutBinding.chooseStateButton.setOnClickListener {v: View ->
                val popup = PopupMenu(environmentSP.context, v)
                popup.menuInflater.inflate(R.menu.sp_banner_menu, popup.menu)

                popup.setOnMenuItemClickListener { menuItem: MenuItem ->
                    layoutBinding.chooseStateButton.text = menuItem.title.toString()
                    true
                }
                popup.show()
            }
            return layoutBinding.root
        }


    }

}