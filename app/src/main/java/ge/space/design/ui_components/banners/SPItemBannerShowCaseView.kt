package ge.space.design.ui_components.banners

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemBannersShowcaseBinding

class SPItemBannerShowCaseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        SpItemBannersShowcaseBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init{
        binding.bannerTitleEditText.setText(R.string.banner_title)
    }

}