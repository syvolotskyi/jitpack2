package ge.space.ui.components.banners

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBannerStatusLayoutBinding
import ge.space.ui.components.banners.base.SPBannerBaseView

class SPBannerStatus @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBannerBaseView<SpBannerStatusLayoutBinding>(context, attrs, defStyleAttr) {

    init{
        setBannerStatusStyle()
    }

    override fun getViewBinding(): SpBannerStatusLayoutBinding =
        SpBannerStatusLayoutBinding.inflate(LayoutInflater.from(context), this, false)

    override fun updateBannerTitle(text: String) {
        binding.statusTitle.text = text
    }

    override fun updateBannerSubTitle(text: String) {
        binding.statusSubtitle.text = text
    }

    override fun updateBannerDescription(text: String) {
        binding.statusDescription.text = text
    }

    override fun updateTitleVisibility(value: Boolean) {
        binding.statusTitle.visibility = if(value) View.VISIBLE else View.GONE
    }

    override fun updateSubTitleVisibility(value: Boolean) {
        binding.statusSubtitle.visibility = if(value) View.VISIBLE else View.GONE
    }

    override fun updateDescriptionVisibility(value: Boolean) {
        binding.statusDescription.visibility = if(value) View.VISIBLE else View.GONE
    }

    override fun setBannerStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPBannerBaseView)

        styleAttrs.run {
            bannerTitle = getString(R.styleable.SPBannerBaseView_sp_bannerTitle).orEmpty()
            bannerSubtitle = getString(R.styleable.SPBannerBaseView_sp_bannerSubTitle).orEmpty()
            bannerDescription = getString(R.styleable.SPBannerBaseView_sp_bannerDescription).orEmpty()
            recycle()
        }
    }

    private fun setBannerStatusStyle(){
        val styleAttrs = context.theme.obtainStyledAttributes(R.styleable.SPBannerStatus)
    }


}