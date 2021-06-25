package ge.space.ui.components.bank_cards.chip

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpSmallEmptyChipLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPSmallEmptyStyle

/**
 * Comment
 */
class SPSmallEmptyChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Comment
     */
    var style: SPSmallEmptyStyle = SPSmallEmptyStyle.White
        set(value) {
            field = value

            handleSmallEmptyChipBackground()
        }

    /**
     * Comment
     */
    private val binding =
        SpSmallEmptyChipLayoutBinding.inflate(LayoutInflater.from(context), this)

    private fun handleSmallEmptyChipBackground() {
        val backImage = getBackgroundImage()
        binding.ivBackground.setImageResource(
            backImage
        )
    }

    private fun getBackgroundImage() =
        when(style) {
            SPSmallEmptyStyle.White -> R.drawable.ic_small_empty_chip_light
            else -> R.drawable.ic_small_empty_chip_dark
        }
}