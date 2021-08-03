package ge.space.ui.components.bank_cards.chip.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatImageView
import ge.space.spaceui.databinding.SpSecondaryChipLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChip
import ge.space.ui.util.extension.loadImageUrl
import ge.space.ui.util.extension.visibleOrGone
import ge.space.ui.util.view_factory.SPViewData

/**
 * Allows to show chips with both a bank logo and a payment system icon on
 * light background. Also the view allows to hide or show a border
 *
 * @property hasBorder hides or shows a border of the view
 * @property paymentSystemUrl loads a payment system icon
 * @property bankLogoUrl loads a bank icon
 */
class SPSecondaryChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseChip(context, attrs, defStyleAttr) {

    /**
     * Allows to hide or show a border for the view
     */
    var hasBorder: Boolean = false
        set(value) {
            field = value

            binding.border.visibleOrGone(value)
        }

    /**
     * Allows to load a payment service icon by URL
     */
    var paymentSystemUrl: String = EMPTY_TEXT
        set(value) {
            field = value

            handleLogo()
        }

    /**
     * Allows to to load a bank logo icon by URL
     */
    var bankLogoUrl: String = ""
        set(value) {
            field = value

            binding.placeholder.logoUrl = value
        }

    /**
     * Binds a view
     */
    private val binding =
        SpSecondaryChipLayoutBinding.inflate(LayoutInflater.from(context), this)

    private fun handleLogo() {
        loadPaymentSystemLogo(
            binding.ivPaymentSystem
        )
    }

    private fun loadPaymentSystemLogo(ivPaymentSystem: AppCompatImageView) {
        if (paymentSystemUrl.isNotEmpty()) {
            context.loadImageUrl(
                paymentSystemUrl,
                ivPaymentSystem
            )
        }
    }

    override fun getViewData(): SPViewData =
        SPViewData.SPSecondaryChipData(size, bankLogoUrl, paymentSystemUrl, hasBorder, getStyle())

}