package ge.space.ui.components.bank_cards.chip.empty

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpSmallEmptyChipLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import ge.space.ui.util.view_factory.SPViewData

/**
 * A simple empty chip just for show an empty state
 *
 * @property emptyViewStyle allows to change the appearance which allows to change
 * between light and dark styles
 */
class SPEmptyChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPBankCardView_ChipEmpty_Dark
) : SPBaseChip(context, attrs, defStyleAttr) {

    /**
     * Changes the style of the view
     */
    var emptyViewStyle: SPEmptyChipStyle = SPEmptyChipStyle.White
        set(value) {
            field = value

            handleSmallEmptyChipBackground()
        }

    /**
     * Binds a view
     */
    private val binding =
        SpSmallEmptyChipLayoutBinding.inflate(LayoutInflater.from(context), this)

    private fun handleSmallEmptyChipBackground() {
        binding.ivBackground.setImageResource(getBackgroundImage())
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

    override fun handleChipSize() {
        binding.ivBackground.setHeight(chipHeight)
        binding.ivBackground.setWidth(chipWidth)
    }

    override fun setChipStyle(styleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(styleRes, R.styleable.SPEmptyChip)

        styleAttrs.run { withEmptyChipStyledResource() }
    }

    private fun TypedArray.withEmptyChipStyledResource() {
        val styleId = getInt(
            R.styleable.SPEmptyChip_emptyChipAppearance,
            DEFAULT_OBTAIN_VAL
        )
        emptyViewStyle = SPEmptyChipStyle.values()[styleId]

        color = ContextCompat.getColor(context, android.R.color.transparent)

        handleChipSize()
    }

    override fun getViewData(): SPViewData =
        SPViewData.SPEmptyChipData(chipHeight, chipWidth, emptyViewStyle, R.style.SPBankCardView_EmptySmall_Base)

    private fun getBackgroundImage() =
        when (emptyViewStyle) {
            SPEmptyChipStyle.White -> R.drawable.ic_small_empty_chip_light
            else -> R.drawable.ic_small_empty_chip_dark
        }
}