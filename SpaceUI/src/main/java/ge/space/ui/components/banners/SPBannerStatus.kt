package ge.space.ui.components.banners

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.components.banners.base.SPBannerBaseView

class SPBannerStatus @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBannerBaseView(context, attrs, defStyleAttr) {

    @StyleRes
    var statusState = 0
        set(value) {
            field = value

            when (value) {
                R.style.SPBannerStatusSuccess -> setStatusImage(R.drawable.ic_status_success)
                R.style.SPBannerStatusError -> setStatusImage(R.drawable.ic_status_error)
                R.style.SPBannerStatusPending -> setStatusImage(R.drawable.ic_status_pending)
                R.style.SPBannerStatusInfo -> setStatusImage(R.drawable.ic_status_info)
            }
        }

    private fun setStatusImage(statusImage: Int){
        binding.bannerImage.setImageResource(statusImage)
    }


//    override fun setBannerStyle(@StyleRes defStyleRes: Int) {
//        val styleAttrs =
//            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPBannerStatus)
//
//        styleAttrs.run {
//            statusState = getResourceId(R.styleable.SPBannerStatus_sp_bannerStatusStyle, 0)
//            recycle()
//        }
//    }
}

