package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpSecondaryChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import ge.space.ui.components.bank_cards.data.SPPlaceholderSize
import ge.space.ui.util.extension.loadImageUrl
import ge.space.ui.util.extension.visibleOrGone
import ge.space.ui.util.view_factory.SPViewData

/**
 * Allows to show chips with both a bank logo and a payment system icon on
 * light background. Also the view allows to hide or show a border
 *
 * @property hasBorder hides or shows a border of the view
 * @property paymentSystemUrl loads a payment system icon
 * @property bankLogoUrl loads a bank icon
 */
class SPSecondaryChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseChip(context, attrs, defStyleAttr) {
    /**
     * Binds a view
     */
    private val binding =
        SpSecondaryChipLayoutBinding.inflate(
            LayoutInflater.from(context),
            this
        )

    /**
     * Allows to hide or show a border for the view
     */
    var border: Int = 0
        set(value) {
            field = value

            if (border != 0) {
                binding.border.background = ContextCompat.getDrawable(context, border)
            }
            binding.border.isVisible = value != 0
        }

    /**
     * Allows to load a payment service icon by URL
     */
    var paymentSystemUrl: String = EMPTY_TEXT
        set(value) {
            field = value

            handleLogo()
        }

    /**
     * Allows to to load a bank logo icon by URL
     */
    var bankLogoUrl: String = ""
        set(value) {
            field = value

            binding.placeholder.logoUrl = value
        }

    /**
     * Changes a size of the view
     */
    private var placeholderSize: SPPlaceholderSize = SPPlaceholderSize.XSmall
        set(value) {
            field = value

            binding.placeholder.placeholderSize = value
        }

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.sp_chip_secondary,
            defStyleAttr
        ) { withSecondaryChipStyledResource() }
    }


    private fun handleLogo() {
        loadPaymentSystemLogo(
            binding.ivPaymentSystem
        )
    }

    private fun loadPaymentSystemLogo(ivPaymentSystem: AppCompatImageView) {
        if (paymentSystemUrl.isNotEmpty()) {
            context.loadImageUrl(
                paymentSystemUrl,
                ivPaymentSystem
            )
        }
    }

    override fun handleChipSize() {
        binding.placeholder.setHeight(chipHeight)
        binding.placeholder.setWidth(chipWidth)
    }

    override fun setChipStyle(styleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(styleRes, R.styleable.sp_chip_secondary)

        styleAttrs.run {
            withSecondaryChipStyledResource()
        }
    }

    private fun TypedArray.withSecondaryChipStyledResource() {
        border = getResourceId(R.styleable.sp_chip_secondary_border, 0)
        placeholderSize =
            SPPlaceholderSize.values()[getInt(
                R.styleable.sp_chip_secondary_placeholder_size,
                DEFAULT_OBTAIN_VAL
            )]

    }


    override fun getViewData(): SPViewData =
        SPViewData.SPSecondaryChipData(
            chipHeight,
            chipWidth,
            bankLogoUrl,
            paymentSystemUrl,
            border,
            0
        )

}