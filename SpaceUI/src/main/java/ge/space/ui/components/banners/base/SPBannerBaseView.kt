package ge.space.ui.components.banners.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBannerLayoutBinding
import ge.space.ui.base.SPBaseView.Companion.EMPTY_TEXT

abstract class SPBannerBaseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected val binding by lazy {
        SpBannerLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var bannerTitle: String = EMPTY_TEXT
        set(value) {
            field = value

            updateBannerTitle(value)
        }

    var bannerSubtitle: String = EMPTY_TEXT
        set(value) {
            field = value

            updateBannerSubTitle(value)
        }

    var bannerDescription: String = EMPTY_TEXT
        set(value) {
            field = value

            updateBannerDescription(value)
        }

    var titleVisibility: Boolean = true
        set(value) {
            field = value

            updateTitleVisibility(value)
        }

    var subTitleVisibility: Boolean = true
        set(value) {
            field = value

            updateSubTitleVisibility(value)
        }

    var descriptionVisibility: Boolean = true
        set(value) {
            field = value

            updateDescriptionVisibility(value)
        }

    init{
        setBannerBaseStyle()
    }

    private fun updateBannerTitle(text: String) {
        binding.bannerTitle.text = text
    }

    private fun updateBannerSubTitle(text: String) {
        binding.bannerSubtitle.text = text
    }

    private fun updateBannerDescription(text: String) {
        binding.bannerDescription.text = text
    }

    private fun updateTitleVisibility(value: Boolean) {
        binding.bannerTitle.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun updateSubTitleVisibility(value: Boolean) {
        binding.bannerSubtitle.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun updateDescriptionVisibility(value: Boolean) {
        binding.bannerDescription.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun setBannerBaseStyle() {
        val styleAttrs = context.theme.obtainStyledAttributes(R.styleable.SPBannerBaseView)

        styleAttrs.run {
            bannerTitle = getString(R.styleable.SPBannerBaseView_sp_bannerTitle).orEmpty()
            bannerSubtitle = getString(R.styleable.SPBannerBaseView_sp_bannerSubTitle).orEmpty()
            bannerDescription = getString(R.styleable.SPBannerBaseView_sp_bannerDescription).orEmpty()
            titleVisibility = getBoolean(R.styleable.SPBannerBaseView_sp_bannerTitle_isVisible, true)
            subTitleVisibility = getBoolean(R.styleable.SPBannerBaseView_sp_bannerSubTitle_isVisible, true)
            descriptionVisibility = getBoolean(R.styleable.SPBannerBaseView_sp_bannerDescription_isVisible, true)
            recycle()
        }
    }

    protected abstract fun setBannerStyle(@StyleRes defStyleRes: Int)

}