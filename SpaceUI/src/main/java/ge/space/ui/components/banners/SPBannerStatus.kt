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

    private var statusState = StatusStates.Success
        set(value) {
            field = value
            handleStates()
        }

    fun setBannerStatusStyle(defStyleRes: Int) {
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPBannerStatus)
        styleAttrs.run {
            val statusStateInd = styleAttrs.getInt(R.styleable.SPBannerStatus_sp_bannerStatusState, 0)
            statusState = StatusStates.values()[statusStateInd]
            recycle()
        }
    }

    private fun handleStates() {
        when (statusState) {
            StatusStates.Success -> setStatusImage(R.drawable.ic_status_success)
            StatusStates.Error -> setStatusImage(R.drawable.ic_status_error)
            StatusStates.Pending -> setStatusImage(R.drawable.ic_status_pending)
            StatusStates.Info -> setStatusImage(R.drawable.ic_status_info)
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

