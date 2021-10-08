package ge.space.ui.components.banner

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBannerLayoutBinding
import ge.space.ui.base.SPBaseView.Companion.EMPTY_TEXT
import ge.space.ui.util.extension.SPSetViewStyleInterface
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

class SPBannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.SPBanner_Base,

) : LinearLayout(context, attrs, defStyleAttr), SPSetViewStyleInterface {

    private val binding = SpBannerLayoutBinding.inflate(LayoutInflater.from(context), this, true)

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

    @DrawableRes
    var bannerImage: Int? = null
        set(value) {
            field = value
            setBannerResource(SPViewData.SPImageResourcesData(value!!))
        }

    init {
        getContext().withStyledAttributes(attrs, R.styleable.SPBannerView, defStyleAttr, defStyleRes) {
            bannerTitle = getString(R.styleable.SPBannerView_banner_title).toString()
            bannerSubtitle = getString(R.styleable.SPBannerView_banner_subtitle).orEmpty()
            bannerDescription = getString(R.styleable.SPBannerView_banner_description).orEmpty()
            titleVisibility = getBoolean(R.styleable.SPBannerView_banner_title_is_visible, true)
            subTitleVisibility = getBoolean(R.styleable.SPBannerView_banner_subtitle_is_visible, true)
            descriptionVisibility = getBoolean(R.styleable.SPBannerView_banner_description_is_visible, true)
            bannerImage = getResourceId(R.styleable.SPBannerView_banner_image, -1)

            setTextAppearances()
        }

    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.theme.obtainStyledAttributes(newStyle, R.styleable.SPBannerView).run {
            setTextAppearances()
        }
    }

    fun setBannerResource(resourceData: SPViewData) {
        binding.bannerResourceContainer.removeAllViews()
        when (resourceData) {
            is SPViewData.SPImageResourcesData, is SPViewData.SPNewCreditCards ->
                resourceData.let {
                    val resource = it.createView(context)
                    binding.bannerResourceContainer.addView(resource)
                }
            else ->
                throw IllegalStateException("banner resource container can not accept $resourceData type")
        }

    }

    private fun TypedArray.setTextAppearances() {
        with(binding) {
            bannerTitle.setTextStyle(
                getResourceId(
                    R.styleable.SPBannerView_banner_title_text_appearance,
                    R.style.h600_bold_caps_label_secondary
                )
            )

            bannerSubtitle.setTextStyle(
                getResourceId(
                    R.styleable.SPBannerView_banner_subtitle_text_appearance,
                    R.style.h600_medium_label_secondary
                )
            )

            bannerDescription.setTextStyle(
                getResourceId(
                    R.styleable.SPBannerView_banner_description_text_appearance,
                    R.style.h700_medium_secondary
                )
            )
        }
    }

}