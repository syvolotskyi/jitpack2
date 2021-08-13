package ge.space.ui.components.bank_cards.chip.empty

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
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
        context.withStyledAttributes(
            attrs,
            R.styleable.sp_chip_empty,
            defStyleAttr
        ) { withEmptyChipStyledResource() }
    }

    override fun setChipStyle(styleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(styleRes, R.styleable.sp_chip_empty)

        styleAttrs.run { withEmptyChipStyledResource() }
    }

    private fun TypedArray.withEmptyChipStyledResource() {
        val styleId = getInt(
            R.styleable.sp_chip_empty_emptyChipAppearance,
            DEFAULT_OBTAIN_VAL
        )
        emptyViewStyle = SPEmptyChipStyle.values()[styleId]

        color = ContextCompat.getColor(context, android.R.color.transparent)
    }

    override fun getViewData(): SPViewData =
        SPViewData.SPEmptyChipData(chipHeight, chipWidth, emptyViewStyle)

    private fun getBackgroundImage() =
        when (emptyViewStyle) {
            SPEmptyChipStyle.White -> R.drawable.ic_small_empty_chip_light
            else -> R.drawable.ic_small_empty_chip_dark
        }
}