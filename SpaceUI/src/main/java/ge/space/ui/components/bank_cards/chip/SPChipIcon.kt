package ge.space.ui.components.bank_cards.chip

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import ge.space.extensions.resolveColorByAttr
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseChipIcon
import ge.space.ui.components.bank_cards.data.SPChipIconAppearance

/**
 * Comment
 */
class SPChipIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseChipIcon(context, attrs, defStyleAttr) {

    /**
     * Comment
     */
    @DrawableRes
    var icon: Int = R.drawable.ic_bank_24_regular
        set(value) {
            field = value

            changeIcon()
        }

    /**
     * Comment
     */
    var iconAppearance: SPChipIconAppearance = SPChipIconAppearance.Accent
        set(value) {
            field = value

            handleIconAppearance()
        }

    init {
        changeIcon()
        handleIconAppearance()
    }

    private fun changeIcon() {
        binding.ivIcon.setImageResource(
            icon
        )
    }

    private fun handleIconAppearance() {
        val colorAttr = getColorAttr()
        val color = context.resolveColorByAttr(colorAttr)
        binding.ivIcon.setColorFilter(
            color, PorterDuff.Mode.SRC_IN
        )
    }

    private fun getColorAttr() = when (iconAppearance) {
        SPChipIconAppearance.Accent -> R.attr.colorAccent
        else -> R.attr.static_black
    }
}