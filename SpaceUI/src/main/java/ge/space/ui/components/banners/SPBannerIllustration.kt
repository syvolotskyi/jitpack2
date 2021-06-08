package ge.space.ui.components.banners

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.components.banners.base.SPBannerBaseView

class SPBannerIllustration @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBannerBaseView(context, attrs, defStyleAttr) {

    @IdRes
    var bannerIllustration = 0
        set(value) {
            field = value
            binding.bannerImage.setImageResource(bannerIllustration)
        }

    override fun setBannerStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPBannerIllustration)

        styleAttrs.run {
            bannerIllustration = getResourceId(R.styleable.SPBannerIllustration_sp_bannerImage, 0)
            recycle()
        }
    }
}