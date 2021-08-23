package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPrimaryChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.util.view_factory.SPViewData

/**
 * A chip extended by [SPBaseChip] allows to show a primary info of bank.
 * The info depends on the flavor (TBC or Space)
 */
class SPPrimaryChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseChip(context, attrs, defStyleAttr) {

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
        context.withStyledAttributes(
            attrs,
            R.styleable.sp_chip_primary,
            defStyleAttr
        ) {
            primaryChipImage = getResourceId(
                R.styleable.sp_chip_primary_chipIcon,
                R.drawable.img_primary_chip
            )
        }
    }

    override fun setChipStyle(styleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(styleRes, R.styleable.sp_chip_primary)

        styleAttrs.run {
            primaryChipImage = getResourceId(
                R.styleable.sp_chip_primary_chipIcon,
                R.drawable.img_primary_chip
            )
        }
        handleChipSize()
    }

    override fun handleChipSize() {
        binding.ivBackground.setHeight(chipHeight)
        binding.ivBackground.setWidth(chipWidth)
    }

    override fun getViewData(): SPViewData {
        return SPViewData.SPrimaryChipData(chipHeight, chipWidth, 0)
    }

    private fun handleImageBackground() {
        binding.ivBackground.setImageResource(primaryChipImage)
    }

}