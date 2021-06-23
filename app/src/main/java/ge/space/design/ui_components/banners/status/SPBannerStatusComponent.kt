package ge.space.design.ui_components.banners.status

import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpBannerStatusShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.banners.full_screen.SPBannerData
import ge.space.design.ui_components.banners.full_screen.SPBannerFullScreenActivity
import ge.space.design.ui_components.banners.full_screen.SPBannerType
import ge.space.extensions.onTextChanged

class SPBannerStatusComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banner_status

    override fun getDescriptionResId(): Int = R.string.component_banner_status_description

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {

        private lateinit var layoutBinding: SpBannerStatusShowcaseBinding

        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            layoutBinding = SpBannerStatusShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )
            setAttributes()

            layoutBinding.apply {
                var statusStyle = R.style.SPBannerStatusSuccess
                BannerStatus.setBannerStatusStyle(statusStyle)

                chooseStateButton.setOnClickListener { v: View ->
                    val popup = PopupMenu(environmentSP.context, v)
                    popup.menuInflater.inflate(R.menu.sp_banner_menu, popup.menu)

                    popup.setOnMenuItemClickListener { menuItem: MenuItem ->

                        layoutBinding.chooseStateButton.text = menuItem.title.toString()
                        when (menuItem.itemId) {
                            R.id.option_success -> {
                                statusStyle = R.style.SPBannerStatusSuccess
                            }
                            R.id.option_error -> {
                                statusStyle = R.style.SPBannerStatusError
                            }
                            R.id.option_pending -> {
                                statusStyle = R.style.SPBannerStatusPending
                            }
                            R.id.option_info -> {
                                statusStyle = R.style.SPBannerStatusInfo
                            }
                        }
                        BannerStatus.setBannerStatusStyle(statusStyle)
                        true
                    }
                    popup.show()
                }

                showFullScreenButton.setOnClickListener {
                    val intent =
                        Intent(environmentSP.context, SPBannerFullScreenActivity::class.java)
                    val bannerData = SPBannerData(
                        SPBannerType.Status,
                        BannerStatus.bannerTitle,
                        BannerStatus.bannerSubtitle,
                        BannerStatus.bannerDescription,
                        style = statusStyle
                    )
                    intent.putExtra("BannerAttributes", bannerData)
                    environmentSP.context.startActivity(intent)
                }
            }


            return layoutBinding.root
        }

        private fun setAttributes() {
            layoutBinding.apply {
                bannerInputTextsView.bannerTitleEditText.onTextChanged {
                    BannerStatus.bannerTitle = it
                }
                bannerInputTextsView.bannerSubTitleEditText.onTextChanged {
                    BannerStatus.bannerSubtitle = it
                }
                bannerInputTextsView.bannerDescEditText.onTextChanged {
                    BannerStatus.bannerDescription = it
                }
                bannerInputTextsView.bannerTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    BannerStatus.titleVisibility = isChecked
                }
                bannerInputTextsView.bannerSubTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    BannerStatus.subTitleVisibility = isChecked
                }
                bannerInputTextsView.bannerDescVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    BannerStatus.descriptionVisibility = isChecked
                }
            }
        }
    }
}