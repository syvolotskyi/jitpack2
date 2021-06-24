package ge.space.ui.components.buttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatImageView
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpAddBankButtonLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPChipBankCardSize

/**
 * Comment
 */
class SPAddBankCardButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Comment
     */
    var cardSize : SPChipBankCardSize = SPChipBankCardSize.Big
        set(value) {
            field = value

            handleCardAppearance()
        }

    /**
     * Comment
     */
    private val binding =
        SpAddBankButtonLayoutBinding.inflate(LayoutInflater.from(context), this)

    private fun handleCardAppearance() {
        val isBig = cardSize == SPChipBankCardSize.Big

        with(binding) {
            ivPlus.setImageSize(isBig)

            val style = getStyle(isBig)
            setStyle(style)
            setSize(isBig)
        }
    }

    private fun getStyle(isBig: Boolean) = if (isBig) R.style.SPBankCardView_AddBankCardButton
        else R.style.SPBankCardView_AddBankCardButton_Small

    private fun AppCompatImageView.setImageSize(
        isBig: Boolean
    ) {
        width(
            isBig,
            R.dimen.sp_add_bank_card_button_icon_size,
            R.dimen.sp_add_bank_card_button_icon_size_small
        )
        height(
            isBig,
            R.dimen.sp_add_bank_card_button_icon_size,
            R.dimen.sp_add_bank_card_button_icon_size_small
        )
    }

    private fun setSize(isBig: Boolean) {
        width(isBig, R.dimen.sp_bank_chip_width, R.dimen.sp_bank_chip_width_small)
        height(isBig, R.dimen.sp_bank_chip_height, R.dimen.sp_bank_chip_height_small)
    }

    private fun View.width(
        isBig: Boolean,
        @DimenRes widthId: Int,
        @DimenRes smallWidthId: Int
    ) {
        setWidth(
            resources.getDimension(
                if (isBig) widthId
                else smallWidthId
            ).toInt()
        )
    }

    private fun View.height(
        isBig: Boolean,
        @DimenRes heightId: Int,
        @DimenRes smallHeightId: Int
    ) {
        setHeight(
            resources.getDimension(
                if (isBig) heightId
                else smallHeightId
            ).toInt()
        )
    }
}