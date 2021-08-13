package ge.space.ui.components.bank_cards.chip.icon

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.extensions.resolveColorByAttr
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpChipIconLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPChipIconStyle
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.util.extension.loadRoundImageUrl
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
) : SPBaseChip(context, attrs, defStyleAttr) {

    /**
     * Small icon
     */
    @DrawableRes
    var icon: Int = R.drawable.ic_bank_24_regular
        set(value) {
            field = value

            changeIcon()
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
     * Changes the iconRadius appearance
     */
    var iconRadius: Int = 0
        set(value) {
            field = value

            handleCardAppearance()
        }

    /**
     * Binds a view
     */
    private val binding =
        SpChipIconLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.sp_chip,
            defStyleAttr
        ) {
            withStyledResource()
        }
    }

    private fun TypedArray.withStyledResource() {
        val styleRes = getResourceId(R.styleable.sp_chip_chipStyle, DEFAULT_OBTAIN_VAL)
        if (styleRes > DEFAULT_OBTAIN_VAL) {
            setChipStyle(styleRes)
        } else {
            handleCardAppearance()
        }
    }

    override fun setChipStyle(styleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(styleRes, R.styleable.sp_chip_icon)

        styleAttrs.run {
            icon = getResourceId(
                R.styleable.sp_chip_icon_chipIcon,
                R.drawable.ic_bank_24_regular
            )
            iconStyle = SPChipIconStyle.values()[
                getInt(R.styleable.sp_chip_icon_chipIconAppearance, DEFAULT_OBTAIN_VAL)
            ]
            size = SPChipSize.values()[
                getInt(R.styleable.sp_chip_icon_cardSize, DEFAULT_OBTAIN_VAL)
            ]

            iconRadius = getDimensionPixelSize(
                R.styleable.sp_chip_icon_iconRadius, DEFAULT_OBTAIN_VAL
            )
        }
    }

    fun handleCardAppearance() {
        changeIcon()
        handleIconAppearance()
    }

    override fun getViewData(): SPViewData =
         SPViewData.SPChipData(size, icon, 0)

    private fun changeIcon() {
        with(binding) {
            ivIcon.setImageResource(icon)
        }
    }

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
            context.loadRoundImageUrl(
                url,
                bigPhoto,
                iconRadius
            )
        }
    }

    private fun getHasPhotoUrl() =
        bigPhotoUrl != null


    private fun handleImageViewsVisibility(hasPhotoUrl: Boolean) {
        with(binding) {
            ivIcon.isVisible =!hasPhotoUrl
        }
    }

    private fun getColorAttr() = when (iconStyle) {
        SPChipIconStyle.Accent -> R.attr.colorAccent
        else -> R.attr.static_black
    }
}