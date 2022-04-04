package ge.space.ui.components.bank_cards.chip.icon

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpChipIconLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPChipIconStyle
import ge.space.ui.util.extension.loadImageUrl
import ge.space.ui.util.extension.resolveColorByAttr
import ge.space.ui.util.extension.setHeight
import ge.space.ui.util.extension.setWidth
import ge.space.ui.util.view_factory.SPViewData

/**
 * An extended view of [SPBaseView] which is for change an icon and size of the view.
 * Also the view allows to change big image by its URL. In the last case the small icon
 * is hidden
 *
 * @property icon allows to load an image by its URL
 * @property bigPhotoUrl allows to load a big image and hides the icon
 * @property iconStyle changes the icon appearance
 */
class SPChipIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPBankCardView_Chip
) : SPBaseChip(context, attrs, defStyleAttr, defStyleRes) {

    /**
     * Small icon
     */
    @DrawableRes
    var icon: Int = R.drawable.ic_bank_24_regular
        set(value) {
            field = value

            binding.ivIcon.setImageResource(icon)
        }

    /**
     * Big icon. If it's not null the big image is loaded by URL and the small
     * icon is hidden
     */
    var bigPhotoUrl: String? = null
        set(value) {
            field = value

            handlePhotoUrl()
        }

    /**
     * Changes the small icon appearance
     */
    var iconStyle: SPChipIconStyle = SPChipIconStyle.Accent
        set(value) {
            field = value

            handleIconAppearance()
        }

    /**
     * Changes the width size of the view
     */
    var brandLogoSizeHeight: Int = 0
        set(value) {
            field = value

            binding.ivBigImage.setHeight(brandLogoSizeHeight)
        }

    /**
     * Binds a view
     */
    private val binding =
        SpChipIconLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPChipIcon,
            defStyleAttr,
            defStyleRes
        ) {
            withStyledResource()
        }
        handleCardAppearance()
    }

    private fun TypedArray.withStyledResource() {
        icon = getResourceId(
            R.styleable.SPChipIcon_chipIcon,
            R.drawable.ic_bank_24_regular
        )
        iconStyle = SPChipIconStyle.values()[
                getInt(R.styleable.SPChipIcon_chipIconAppearance, DEFAULT_OBTAIN_VAL)
        ]
    }

    override fun setChipStyle(styleRes: Int) {
        context.withStyledAttributes(styleRes, R.styleable.SPChipIcon) { withStyledResource() }
        handleCardAppearance()
    }

    fun handleCardAppearance() {
        handleIconAppearance()
        handleChipSize()
    }

    override fun handleChipSize() {
        with(binding) {
            frame.setWidth(chipWidth)
            frame.setHeight(chipHeight)
            ivBigImage.setWidth(chipWidth)
            ivBigImage.setHeight(chipHeight)
        }
    }

    override fun getViewData(): SPViewData =
        SPViewData.SPChipData(chipHeight, chipWidth, icon, 0)

    private fun handleIconAppearance() {
        val colorAttr = getColorAttr()
        val color = context.resolveColorByAttr(colorAttr)
        with(binding) {
            ivIcon.setColorFilter(
                color, PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun handlePhotoUrl() {

        val hasPhotoUrl = getHasPhotoUrl()
        handleImageViewsVisibility(hasPhotoUrl)

        binding.ivBigImage.isVisible = hasPhotoUrl
        loadPhotoUrl(binding.ivBigImage)
    }

    private fun loadPhotoUrl(bigPhoto: AppCompatImageView) {
        bigPhotoUrl?.let { url ->
            context.loadImageUrl(
                url,
                bigPhoto
            )
        }
    }

    private fun getHasPhotoUrl() =
        bigPhotoUrl != null


    private fun handleImageViewsVisibility(hasPhotoUrl: Boolean) {
        with(binding) {
            ivIcon.isVisible = !hasPhotoUrl
        }
    }

    private fun getColorAttr() = when (iconStyle) {
        SPChipIconStyle.Accent -> R.attr.colorAccent
        else -> R.attr.static_black
    }
}