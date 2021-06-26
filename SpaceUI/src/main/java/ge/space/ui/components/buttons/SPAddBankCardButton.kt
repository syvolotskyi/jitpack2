package ge.space.ui.components.buttons

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseChipIcon

/**
 * Comment
 */
class SPAddBankCardButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseChipIcon(context, attrs, defStyleAttr) {

    /**
     * Comment
     */
    override fun setImageSize() {
        with(binding.ivIcon) {
            width(
                R.dimen.sp_add_bank_card_button_icon_size,
                R.dimen.sp_add_bank_card_button_icon_size_small
            )
            height(
                R.dimen.sp_add_bank_card_button_icon_size,
                R.dimen.sp_add_bank_card_button_icon_size_small
            )
        }
    }
}