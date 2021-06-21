package ge.space.ui.components.bank_cards

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBankCardLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.*
import ge.space.ui.util.extension.visibleOrGone

/**
 * A bank card view which allows to show info of a bank card. A user can
 * set next properties:
 *
 * @property cardBackground applies a background color or colors (if it's a gradient)
 * @property payWaveType applies a pay wave style
 * @property hasPayWave sets a visibility of pay wave icon
 * @property hasChip applies a visibility of a chip icon
 * @property status sets a block/fetch status for the view
 * @property accountNumberStyle sets an account number style according to the background
 * @property accountNumber applies an account number string
 * @property isFavorite if it's true a star icon shows
 * @property bankLogo sets a bank logo
 * @property bankName sets a bank name string
 * @property amount sets an amount string
 * @property accountVisible applies an account visibility
 * @property balanceVisible applies a balance string visibility
 * @property model sets a card type
 * @property isCredit if it's true blue credit layout shows
 * @property paySystemUrl sets a pay system icon by its URL
 */
class SPBankCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Applies a type of the bank card
     */
    var model: SPBankCardModel = SPBankCardModel.SPPhysical("")
        set(value) {
            field = value

            handleBankCardType()
        }

    /**
     * Applies a title of a bank name
     */
    private var bankName: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.lytBankCardHeader.tvBankName.text = value
        }

    /**
     * Applies a bank card view background
     */
    var cardBackground: SPBankCardGradient = SPBankCardGradient.SPLinear()
        set(value) {
            field = value

            binding.vGradient.gradient = value
        }

    /**
     * Applies a pay wave type style according to the background
     */
    var payWaveType: SPPayWaveType = SPPayWaveType.Light
        set(value) {
            field = value

            handleBackGradientTypeWithPayWave()
        }

    /**
     * Applies a pay wave icon visibility
     */
    var hasPayWave: Boolean = true
        set(value) {
            field = value

            binding.lytBankCardBody.ivPayWave.visibleOrGone(value)
        }

    /**
     * Applies a chip icon visibility
     */
    var hasChip: Boolean = true
        set(value) {
            field = value

            binding.lytBankCardBody.ivChip.visibleOrGone(value)
        }

    /**
     * Applies block/pending status of the view
     */
    var status: SPBankCardStatus = SPBankCardStatus.Available
        set(value) {
            field = value

            handleBlockingView()
        }

    /**
     * Applies an account number style according to the background
     */
    var accountNumberStyle: SPAccountNumberStyle = SPAccountNumberStyle.White
        set(value) {
            field = value

            handleAccountNumberStyle()
        }

    /**
     * Applies account number string
     */
    var accountNumber: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.lytBankCardBody.tvAccountNumber.text = value
        }

    /**
     * Applies a visibility of the star icon
     */
    var isFavorite: Boolean = false
        set(value) {
            field = value

            binding.lytBankCardHeader.ivFavorite.visibleOrGone(value)
        }

    /**
     * Applies a bank logo by its URL
     */
    var bankLogo: String = EMPTY_TEXT
        set(value) {
            field = value

            handleBankLogo()
        }

    /**
     * Applies an amount of the account
     */
    var amount: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.lytBankCardHeader.tvAmount.text = value
        }

    /**
     * Applies a visibility of the account
     */
    var accountVisible: Boolean = true
        set(value) {
            field = value

            binding.lytBankCardBody.tvAccountNumber.visibleOrGone(value)
        }

    /**
     * Applies a balance visibility
     */
    var balanceVisible: Boolean = true
        set(value) {
            field = value

            binding.lytBankCardHeader.lytBalance.visibleOrGone(value)
        }

    /**
     * Applies a visibility of the credit title
     */
    var isCredit: Boolean = false
        set(value) {
            field = value

            binding.lytBankCardBody.lytCredit.visibleOrGone(value)
        }

    /**
     * Applies a payment system icon by its URL
     */
    var paySystemUrl: String = EMPTY_TEXT
        set(value) {
            field = value

            handlePaySystemImage()
        }


    private fun handlePaySystemImage() {
        if (paySystemUrl.isNotEmpty()) {
            Glide.with(context)
                .load(paySystemUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.lytBankCardBody.ivPaySystem)
        }
    }

    private fun handleBankCardType() {
        with(binding.lytBankCardHeader) {
            tvBankCardType.visibleOrGone(model !is SPBankCardModel.SPDefault)
            tvBankCardType.text = getCardTypeTitle(model)
            bankName = model.name
        }
    }

    private fun getCardTypeTitle(cardType: SPBankCardModel): String =
        when(cardType) {
            is SPBankCardModel.SPDigital -> String.format(
                HARDCODED_DIGITAL_CARD_TEMP,
                cardType.currency
            )
            else -> HARDCODED_PHYSICAL_CARD
        }

    private fun handleBankLogo() {
        if (bankLogo.isNotEmpty()) {
            val cornerRadius = getBankLogoCornersRadius()
            loadBankLogo(cornerRadius)
        }
    }

    private fun loadBankLogo(cornerRadius: Int) {
        Glide.with(context)
            .load(bankLogo)
            .transform(CenterCrop(), RoundedCorners(cornerRadius))
            .placeholder(R.drawable.bg_bank_card_logo)
            .error(R.drawable.bg_bank_card_logo)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.lytBankCardHeader.ivBankImage)
    }

    private fun getBankLogoCornersRadius() =
        resources.getDimension(R.dimen.sp_bank_logo_round_radius)
            .toInt()

    private fun handleAccountNumberStyle() {
        val color = colorByAccountNumberStyle()
        binding.lytBankCardBody.tvAccountNumber.setTextColor(
            ContextCompat.getColor(context, color)
        )
    }

    private fun colorByAccountNumberStyle() = when (accountNumberStyle) {
        SPAccountNumberStyle.Dark -> R.color.static_primary_black
        else -> R.color.static_primary_white
    }

    private val binding =
        SpBankCardLayoutBinding.inflate(LayoutInflater.from(context), this)

    private fun handleBlockingView() {
        val blockLayoutVisible = status != SPBankCardStatus.Available
        binding.lytNotAvailable.lytNonAvailableStatus
            .visibleOrGone(
                visible = blockLayoutVisible
            )
        tryChangeNonAvailableStatus(blockLayoutVisible)
    }

    private fun tryChangeNonAvailableStatus(blockLayoutVisible: Boolean) {
        if (blockLayoutVisible) {
            setStatusPair(
                getStatusPair()
            )
        }
    }

    private fun setStatusPair(statusPair: Pair<String, Int>) {
        with(binding.lytNotAvailable) {
            ivStatus.setImageResource(statusPair.second)
            tvCardStatus.text = statusPair.first
        }
    }

    private fun getStatusPair() = when (status) {
        SPBankCardStatus.Pending -> Pair(
            HARDCODED_PENDING_TITLE,
            R.drawable.ic_alert
        )
        else -> Pair(
            HARDCODED_BLOCKED_TITLE,
            R.drawable.ic_blocked
        )
    }

    private fun handleBackGradientTypeWithPayWave() {
        val color =  getPayWaveColor()
        binding.lytBankCardBody.ivPayWave.setColorFilter(
            ContextCompat.getColor(context, color),
            PorterDuff.Mode.SRC_IN
        )
    }

    private fun getPayWaveColor() = when (payWaveType) {
        SPPayWaveType.Light -> R.color.transparent_white_half
        SPPayWaveType.Dark -> R.color.transparent_black_half
    }

    companion object {
        private const val HARDCODED_BLOCKED_TITLE = "Your card is blocked"
        private const val HARDCODED_PENDING_TITLE = "Your card will be activated soon"
        private const val HARDCODED_PHYSICAL_CARD = "Physical Card"
        private const val HARDCODED_DIGITAL_CARD_TEMP = "Digital Card %s"
    }
}