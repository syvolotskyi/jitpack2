package ge.space.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.DimenRes
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpAddBankButtonLayoutBinding
import ge.space.ui.components.bank_cards.data.SPChipBankCardSize

/**
 * Comment
 */
abstract class SPBaseChipIcon @JvmOverloads constructor(
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
    protected val isBig
        get() = cardSize == SPChipBankCardSize.Big

    /**
     * Comment
     */
    protected val binding by lazy {
        getAddBankButtonLayoutBinding()
    }

    private fun handleCardAppearance() {
        setImageSize()
        val style = getStyle()
        setStyle(style)
        setSize()
    }

    private fun getAddBankButtonLayoutBinding() =
        SpAddBankButtonLayoutBinding.inflate(LayoutInflater.from(context), this)

    private fun getStyle() = if (isBig) R.style.SPBankCardView_AddBankCardButton
        else R.style.SPBankCardView_AddBankCardButton_Small

    protected open fun setImageSize() { }

    private fun setSize() {
        width(R.dimen.sp_bank_chip_width, R.dimen.sp_bank_chip_width_small)
        height(R.dimen.sp_bank_chip_height, R.dimen.sp_bank_chip_height_small)
    }

    protected fun View.width(
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

    protected fun View.height(
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