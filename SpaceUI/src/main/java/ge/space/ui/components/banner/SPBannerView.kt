package ge.space.ui.components.banner

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBannerLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPBaseView.Companion.EMPTY_TEXT
import ge.space.ui.util.extension.SPSetViewStyleInterface
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

class SPBannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
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

    init {
        getContext().withStyledAttributes(attrs, R.styleable.sp_banner_base, defStyleAttr) {
            bannerTitle = getString(R.styleable.sp_banner_base_banner_title).toString()
            bannerSubtitle = getString(R.styleable.sp_banner_base_banner_subtitle).orEmpty()
            bannerDescription = getString(R.styleable.sp_banner_base_banner_description).orEmpty()
            titleVisibility = getBoolean(R.styleable.sp_banner_base_banner_title_is_visible, true)
            subTitleVisibility =
                getBoolean(R.styleable.sp_banner_base_banner_subtitle_is_visible, true)
            descriptionVisibility =
                getBoolean(R.styleable.sp_banner_base_banner_description_is_visible, true)

            setTextAppearances()
        }

    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.theme.obtainStyledAttributes(newStyle, R.styleable.sp_banner_base).run {
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
            bannerTitle.setTextStyle(getResourceId(
                R.styleable.sp_banner_base_banner_title_text_appearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            ))

            bannerSubtitle.setTextStyle(getResourceId(
                R.styleable.sp_banner_base_banner_subtitle_text_appearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            ))

            bannerDescription.setTextStyle(getResourceId(
                R.styleable.sp_banner_base_banner_description_text_appearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            ))
        }
    }

}