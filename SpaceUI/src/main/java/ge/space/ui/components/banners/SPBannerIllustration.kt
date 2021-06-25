package ge.space.ui.components.banners

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.ui.components.banners.base.SPBannerBaseView

class SPBannerIllustration @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBannerBaseView(context, attrs, defStyleAttr) {

    @IdRes
    var bannerImage = 0
        set(value) {
            field = value
            binding.bannerImage.setImageResource(bannerImage)
        }

    init{
        context.withStyledAttributes(attrs,R.styleable.sp_banner_illustration, defStyleAttr) {
            bannerImage = getResourceId(R.styleable.sp_banner_illustration_banner_image, 0)
        }
    }

}