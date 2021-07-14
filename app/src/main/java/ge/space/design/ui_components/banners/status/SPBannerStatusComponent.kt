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
import ge.space.ui.components.banners.SPBannerStatus

class SPBannerStatusComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_banner_status

    override fun getDescriptionResId(): Int = R.string.component_banner_status_description

    override fun getComponentClass(): Class<*> = FactorySP::class.java

    class FactorySP : SPComponentFactory {

        private lateinit var binding: SpBannerStatusShowcaseBinding

        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            binding = SpBannerStatusShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )
            setAttributes()

            with(binding) {
                bannerStatus.setBannerStyle(R.style.SPBanner_Base)
                bannerStatus.statusState = SPBannerStatus.StatusStates.Success

                chooseStateButton.setOnClickListener { v: View ->
                    val popup = PopupMenu(environmentSP.context, v)
                    popup.menuInflater.inflate(R.menu.sp_banner_menu, popup.menu)

                    popup.setOnMenuItemClickListener { menuItem: MenuItem ->

                        binding.chooseStateButton.text = menuItem.title.toString()

                        bannerStatus.statusState = when (menuItem.itemId) {
                            R.id.option_success -> SPBannerStatus.StatusStates.Success
                            R.id.option_error -> SPBannerStatus.StatusStates.Error
                            R.id.option_pending -> SPBannerStatus.StatusStates.Pending
                            R.id.option_info -> SPBannerStatus.StatusStates.Info
                            else -> SPBannerStatus.StatusStates.Success
                        }
                        true
                    }
                    popup.show()
                }

                showFullScreenButton.setOnClickListener {
                    val intent =
                        Intent(environmentSP.context, SPBannerFullScreenActivity::class.java)
                    val bannerData = SPBannerData(
                        SPBannerType.Status,
                        bannerStatus.bannerTitle,
                        bannerStatus.bannerSubtitle,
                        bannerStatus.bannerDescription,
                        bannerStatus.titleVisibility,
                        bannerStatus.subTitleVisibility,
                        bannerStatus.descriptionVisibility,
                        state = bannerStatus.statusState
                    )
                    intent.putExtra(SPBannerFullScreenActivity.KEY_DATA, bannerData)
                    environmentSP.context.startActivity(intent)
                }
            }


            return binding.root
        }

        private fun setAttributes() {
            with(binding) {
                bannerInputTextsView.bannerTitleEditText.onTextChanged {
                    bannerStatus.bannerTitle = it
                }
                bannerInputTextsView.bannerSubTitleEditText.onTextChanged {
                    bannerStatus.bannerSubtitle = it
                }
                bannerInputTextsView.bannerDescEditText.onTextChanged {
                    bannerStatus.bannerDescription = it
                }
                bannerInputTextsView.bannerTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    bannerStatus.titleVisibility = isChecked
                }
                bannerInputTextsView.bannerSubTitleVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    bannerStatus.subTitleVisibility = isChecked
                }
                bannerInputTextsView.bannerDescVisibleCheck.setOnCheckedChangeListener { _, isChecked ->
                    bannerStatus.descriptionVisibility = isChecked
                }
            }
        }
    }
}