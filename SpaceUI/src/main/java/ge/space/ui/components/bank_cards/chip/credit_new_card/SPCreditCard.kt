package ge.space.ui.components.bank_cards.chip.credit_new_card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import ge.space.spaceui.databinding.SpCreditCardLayoutBinding

/**
 * A simple empty chip just for show an empty state
 */
class SPCreditCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding =
        SpCreditCardLayoutBinding.inflate(LayoutInflater.from(context), this)
}