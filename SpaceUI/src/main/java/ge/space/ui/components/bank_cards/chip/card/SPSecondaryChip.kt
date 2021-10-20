package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpSecondaryChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPPlaceholderSize
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.loadImageUrl
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
    @StyleRes defStyleRes: Int = R.style.SPBankCardView_ChipSecondary
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
    var hasBorder: Boolean = false
        set(value) {
            field = value

            handleBorder()
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
    private var placeholderSize: SPPlaceholderSize = SPPlaceholderSize.Medium
        set(value) {
            field = value

            binding.placeholder.placeholderSize = value
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPBaseView,
            defStyleAttr
        ) {
            setViewStyle(
                getResourceId(
                    R.styleable.SPBaseView_style,
                    defStyleRes
                )
            )
        }
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
            context.theme.obtainStyledAttributes(styleRes, R.styleable.SPSecondaryChip)

        styleAttrs.run {
            withSecondaryChipStyledResource()
        }
    }

    private fun TypedArray.withSecondaryChipStyledResource() {
        hasBorder = getBoolean(R.styleable.SPSecondaryChip_hasBorder, false)
        placeholderSize =
            SPPlaceholderSize.values()[getInt(
                R.styleable.SPSecondaryChip_placeholder_size,
                DEFAULT_OBTAIN_VAL
            )]

    }

    private fun handleBorder() {
        if (hasBorder) {
            changeBorder(
                context.getColorFromAttribute(R.attr.separator_non_opaque),
                resources.getDimensionPixelSize(R.dimen.dimen_p_0_5).toFloat()
            )
        } else {
            changeBorder(DEFAULT_OBTAIN_VAL, DEFAULT_OBTAIN_VAL.toFloat())
        }
    }

    override fun getViewData(): SPViewData =
        SPViewData.SPSecondaryChipData(
            chipHeight,
            chipWidth,
            bankLogoUrl,
            paymentSystemUrl,
            hasBorder,
            0
        )

}