package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPrimaryChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.util.extension.setHeight
import ge.space.ui.util.extension.setWidth
import ge.space.ui.util.view_factory.SPViewData

/**
 * A chip extended by [SPBaseChip] allows to show a primary info of bank.
 * The info depends on the flavor (TBC or Space)
 */
class SPPrimaryChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPBankCardView_Chip
) : SPBaseChip(context, attrs, defStyleAttr, defStyleRes) {

    /**
     * Binds a view
     */
    private val binding =
        SpPrimaryChipLayoutBinding.inflate(LayoutInflater.from(context), this)

    @DrawableRes
    private var primaryChipImage = R.drawable.img_primary_chip
        set(value) {
            field = value
            handleImageBackground()
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPPrimaryChip,
            defStyleAttr,
            defStyleRes
        ) {
            applyPrimaryChipAttr()
        }
        handleChipSize()
    }

    override fun setChipStyle(styleRes: Int) {
        context.withStyledAttributes(styleRes, R.styleable.SPPrimaryChip) {
            applyPrimaryChipAttr()
        }
        handleChipSize()
    }

    private fun TypedArray.applyPrimaryChipAttr() {
        primaryChipImage = getResourceId(
            R.styleable.SPPrimaryChip_chipIcon,
            R.drawable.img_primary_chip
        )
    }

    override fun handleChipSize() {
        binding.ivBackground.setHeight(chipHeight)
        binding.ivBackground.setWidth(chipWidth)
    }

    override fun getViewData(): SPViewData {
        return SPViewData.SPrimaryChipData( chipStyleRes)
    }

    private fun handleImageBackground() {
        binding.ivBackground.setImageResource(primaryChipImage)
    }

}