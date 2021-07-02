package ge.space.ui.components.buttons

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseChipIcon
import ge.space.ui.util.extension.heightByIsBig
import ge.space.ui.util.extension.widthByIsBig

/**
 * A button which is used for adding new bank cards
 */
class SPAddBankCardButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseChipIcon(context, attrs, defStyleAttr) {

    /**
     * Sets a size of the image
     */
    override fun setImageSize() {
        with(binding.ivIcon) {
            widthByIsBig(
                isBig,
                R.dimen.sp_add_bank_card_button_icon_size,
                R.dimen.sp_add_bank_card_button_icon_size_small
            )
            heightByIsBig(
                isBig,
                R.dimen.sp_add_bank_card_button_icon_size,
                R.dimen.sp_add_bank_card_button_icon_size_small
            )
        }
    }
}