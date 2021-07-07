package ge.space.ui.components.bank_cards.chip.credit_new_card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import ge.space.spaceui.databinding.SpNewBrandCreditCardLayoutBinding

/**
 * A simple card view which is for creating a new brand credit card
 */
class SPNewBrandCreditCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding =
        SpNewBrandCreditCardLayoutBinding.inflate(LayoutInflater.from(context), this)
}