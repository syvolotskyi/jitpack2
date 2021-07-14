package ge.space.ui.components.banners

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import ge.space.spaceui.R
import ge.space.ui.components.banners.base.SPBannerBaseView

class SPBannerStatus @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBannerBaseView(context, attrs, defStyleAttr) {

    var statusState = StatusStates.Success
        set(value) {
            field = value
            handleStates()
        }

    private fun handleStates() {
        when (statusState) {
            StatusStates.Success -> setStatusImage(R.drawable.img_status_success)
            StatusStates.Error -> setStatusImage(R.drawable.img_status_error)
            StatusStates.Pending -> setStatusImage(R.drawable.img_status_pending)
            StatusStates.Info -> setStatusImage(R.drawable.img_status_info)
        }
    }

    private fun setStatusImage(statusImage: Int) {
        binding.bannerImage.setImageDrawable(ContextCompat.getDrawable(context, statusImage))
    }

    enum class StatusStates {
        Success,
        Error,
        Pending,
        Info
    }
}
