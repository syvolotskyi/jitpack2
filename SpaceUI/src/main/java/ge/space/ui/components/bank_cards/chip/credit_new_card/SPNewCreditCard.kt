package ge.space.ui.components.bank_cards.chip.credit_new_card

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import ge.space.ui.components.bank_cards.data.SPChipSize

/**
 * A simple card view which is for creating a new credit card
 */
class SPNewCreditCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {


    /**
     * Applies a size of the view
     */
    var cardSize : SPChipSize = SPChipSize.Big
        set(value) {
            field = value

//            handleCardAppearance()
        }

    /**
     * Checks if [cardSize] == [SPChipSize.Big]
     */
    private val isBig
        get() = cardSize == SPChipSize.Big


}