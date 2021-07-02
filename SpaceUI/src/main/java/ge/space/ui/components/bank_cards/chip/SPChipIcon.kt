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
import ge.space.ui.util.extension.loadRoundImageUrl
import ge.space.ui.util.extension.visibleOrGone

/**
 * An instance of [SPBaseChipIcon] which is for change an icon and size of the view.
 * Also the view allows to change big image by its URL. In the last case the small icon
 * is hidden
 *
 * @property icon allows to load an image by its URL
 * @property bigPhotoUrl allows to load a big image and hides the icon
 * @property iconAppearance changes the icon appearance
 *
 */
class SPChipIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseChipIcon(context, attrs, defStyleAttr) {

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

    private fun handlePhotoUrl() {
        val hasPhotoUrl = bigPhotoUrl != null

        with(binding) {
            ivIcon.visibleOrGone(!hasPhotoUrl)
            ivBigPhoto.visibleOrGone(hasPhotoUrl)

            bigPhotoUrl?.let { url ->
                context.loadRoundImageUrl(
                    url,
                    ivBigPhoto,
                    getRoundRadius()
                )
            }
        }
    }

    private fun getColorAttr() = when (iconAppearance) {
        SPChipIconAppearance.Accent -> R.attr.colorAccent
        else -> R.attr.static_black
    }

    private fun getRoundRadius() =
        resources.getDimension(
            if (isBig) R.dimen.sp_bank_round_radius_big
            else R.dimen.sp_bank_round_radius_small
        ).toInt()
}