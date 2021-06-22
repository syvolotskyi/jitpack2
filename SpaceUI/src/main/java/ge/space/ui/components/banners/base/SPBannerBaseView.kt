package ge.space.ui.components.banners.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.widget.TextViewCompat
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBannerLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPBaseView.Companion.EMPTY_TEXT

abstract class SPBannerBaseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected val binding = SpBannerLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    var bannerTitle: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.bannerTitle.text = value
        }

    var bannerSubtitle: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.bannerSubtitle.text = value
        }

    var bannerDescription: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.bannerDescription.text = value
        }

    var titleVisibility: Boolean = true
        set(value) {
            field = value

            binding.bannerTitle.visibility = if (value) View.VISIBLE else View.GONE
        }

    var subTitleVisibility: Boolean = true
        set(value) {
            field = value

            binding.bannerSubtitle.visibility = if (value) View.VISIBLE else View.GONE
        }

    var descriptionVisibility: Boolean = true
        set(value) {
            field = value

            binding.bannerDescription.visibility = if (value) View.VISIBLE else View.GONE
        }

    init{
        getContext().withStyledAttributes(attrs, R.styleable.SPBannerBaseView, defStyleAttr) {
            bannerTitle = getString(R.styleable.SPBannerBaseView_sp_bannerTitle).toString()
            bannerSubtitle = getString(R.styleable.SPBannerBaseView_sp_bannerSubTitle).orEmpty()
            bannerDescription = getString(R.styleable.SPBannerBaseView_sp_bannerDescription).orEmpty()
            titleVisibility = getBoolean(R.styleable.SPBannerBaseView_sp_bannerTitle_isVisible, true)
            subTitleVisibility = getBoolean(R.styleable.SPBannerBaseView_sp_bannerSubTitle_isVisible, true)
            descriptionVisibility = getBoolean(R.styleable.SPBannerBaseView_sp_bannerDescription_isVisible, true)
        }

    }

    open fun setBannerStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPBannerBaseView)

        styleAttrs.run {
            val titleTextAppearance = getResourceId(
                R.styleable.SPBannerBaseView_sp_bannerTitle_textAppearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
            updateTitleTextAppearance(titleTextAppearance)

            val subtitleTextAppearance = getResourceId(
                R.styleable.SPBannerBaseView_sp_bannerSubtitle_textAppearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
            updateSubtitleTextAppearance(subtitleTextAppearance)

            val descTextAppearance = getResourceId(
                R.styleable.SPBannerBaseView_sp_bannerDescription_textAppearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
            updateDescTextAppearance(descTextAppearance)

            recycle()
        }
    }

    private fun updateTitleTextAppearance(@StyleRes textAppearance: Int) {
        TextViewCompat.setTextAppearance(binding.bannerTitle, textAppearance)
    }

    private fun updateSubtitleTextAppearance(@StyleRes textAppearance: Int) {
        TextViewCompat.setTextAppearance(binding.bannerSubtitle, textAppearance)
    }

    private fun updateDescTextAppearance(@StyleRes textAppearance: Int) {
        TextViewCompat.setTextAppearance(binding.bannerDescription, textAppearance)
    }

}