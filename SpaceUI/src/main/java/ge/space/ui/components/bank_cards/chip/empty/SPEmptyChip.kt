package ge.space.ui.components.bank_cards.chip.empty

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpSmallEmptyChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle

/**
 * A simple empty chip just for show an empty state
 *
 * @property style allows to change the appearance which allows to change
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
    var style: SPEmptyChipStyle = SPEmptyChipStyle.White
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
        binding.ivBackground.setImageResource(
            getBackgroundImage()
        )
    }

    private fun getBackgroundImage() =
        when(style) {
            SPEmptyChipStyle.White -> R.drawable.ic_small_empty_chip_light
            else -> R.drawable.ic_small_empty_chip_dark
        }
}