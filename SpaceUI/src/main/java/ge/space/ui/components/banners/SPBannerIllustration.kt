package ge.space.ui.components.banners

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBannerIllustrationLayoutBinding
import ge.space.ui.components.banners.base.SPBannerBaseView

class SPBannerIllustration @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBannerBaseView<SpBannerIllustrationLayoutBinding>(context, attrs, defStyleAttr) {

    @IdRes
    var bannerImage = 0
        set(value) {
            field = value
            binding.illustrationBannerImage.setImageResource(bannerImage)
        }

    override fun getViewBinding(): SpBannerIllustrationLayoutBinding =
        SpBannerIllustrationLayoutBinding.inflate(LayoutInflater.from(context), this, false)

    override fun updateBannerTitle(text: String) {
        binding.illustrationBannerTitle.text = text
    }

    override fun updateBannerSubTitle(text: String) {
        binding.illustrationBannerSubtitle.text = text
    }

    override fun updateBannerDescription(text: String) {
        binding.illustrationBannerDescription.text = text
    }

    override fun updateTitleVisibility(value: Boolean) {
        binding.illustrationBannerTitle.visibility = if(value) View.VISIBLE else View.GONE
    }

    override fun updateSubTitleVisibility(value: Boolean) {
        binding.illustrationBannerSubtitle.visibility = if(value) View.VISIBLE else View.GONE
    }

    override fun updateDescriptionVisibility(value: Boolean) {
        binding.illustrationBannerDescription.visibility = if(value) View.VISIBLE else View.GONE
    }

    override fun setBannerStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPBannerBaseView)

        styleAttrs.run {
            bannerTitle = getString(R.styleable.SPBannerBaseView_sp_bannerTitle).orEmpty()
            bannerSubtitle = getString(R.styleable.SPBannerBaseView_sp_bannerSubTitle).orEmpty()
            bannerDescription = getString(R.styleable.SPBannerBaseView_sp_bannerDescription).orEmpty()

            bannerImage = getResourceId(R.styleable.SPBannerIllustration_sp_bannerImage, 0)
            recycle()
        }
    }
}