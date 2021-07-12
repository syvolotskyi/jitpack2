package ge.space.ui.components.bank_cards.card

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.getColorRes
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.*
import ge.space.ui.util.extension.loadImageUrl
import ge.space.ui.util.extension.loadRoundImageUrlWithPlaceholder
import ge.space.ui.util.extension.visibleOrGone
import ge.space.spaceui.databinding.SpBankCardLayoutBinding
import ge.space.spaceui.databinding.SpBankCardNonAvailableBinding

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
    @AttrRes defStyleAttr: Int = 0,
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

            checkFavoriteVisible()
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

    /**
     * Applies a binding of the child views
     */
    private val binding =
        SpBankCardLayoutBinding.inflate(LayoutInflater.from(context), this)

    private val nonAvailableBankCardBinding =
        SpBankCardNonAvailableBinding.bind(binding.root)

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.sp_bank_card_view,
            defStyleAttr
        ) {
            setBankCardTextAppearances()
        }
    }

    private fun TypedArray.setBankCardTextAppearances() {
        nonAvailableBankCardBinding.tvCardStatus.setTextStyle(
            getResourceId(
                R.styleable.sp_bank_card_view_cardStatusTextAppearance,
                DEFAULT_OBTAIN_VAL
            )
        )

        with(binding.lytBankCardHeader) {
            tvAmount.setTextStyle(
                getResourceId(
                    R.styleable.sp_bank_card_view_cardAmountTextAppearance,
                    DEFAULT_OBTAIN_VAL
                )
            )

            tvBankName.setTextStyle(
                getResourceId(
                    R.styleable.sp_bank_card_view_cardBankNameTextAppearance,
                    DEFAULT_OBTAIN_VAL
                )
            )

            tvBankCardType.setTextStyle(
                getResourceId(
                    R.styleable.sp_bank_card_view_cardTypeTextAppearance,
                    DEFAULT_OBTAIN_VAL
                )
            )

            tvBalanceTitle.setTextStyle(
                getResourceId(
                    R.styleable.sp_bank_card_view_cardBalanceTextAppearance,
                    DEFAULT_OBTAIN_VAL
                )
            )
        }

        with(binding.lytBankCardBody) {
            tvCreditTitle.setTextStyle(
                getResourceId(
                    R.styleable.sp_bank_card_view_cardCreditTitleTextAppearance,
                    DEFAULT_OBTAIN_VAL
                )
            )

            tvAccountNumber.setTextStyle(
                getResourceId(
                    R.styleable.sp_bank_card_view_cardAccountNumberTextAppearance,
                    DEFAULT_OBTAIN_VAL
                )
            )
        }
    }

    private fun handlePaySystemImage() {
        if (paySystemUrl.isNotEmpty()) {
            context.loadImageUrl(
                paySystemUrl,
                binding.lytBankCardBody.ivPaySystem
            )
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

    private fun checkFavoriteVisible() {
        with(binding.lytBankCardHeader.favorite) {
            lytRoot.visibleOrGone(isFavorite)
        }
    }

    private fun handleBankLogo() {
        if (bankLogo.isNotEmpty()) {
            val cornerRadius = getBankLogoCornersRadius()
            loadBankLogo(cornerRadius)
        }
    }

    private fun loadBankLogo(cornerRadius: Int) {
        context.loadRoundImageUrlWithPlaceholder(
            bankLogo,
            binding.lytBankCardHeader.ivBankImage,
            R.drawable.bg_bank_card_logo,
            cornerRadius
        )
    }

    private fun getBankLogoCornersRadius() =
        resources.getDimension(R.dimen.sp_bank_logo_round_radius)
            .toInt()

    private fun handleAccountNumberStyle() {
        val color = colorByAccountNumberStyle()
        binding.lytBankCardBody.tvAccountNumber.setTextColor(
            context.getColorRes(color)
        )
    }

    private fun colorByAccountNumberStyle() = when (accountNumberStyle) {
        SPAccountNumberStyle.Dark -> R.color.static_primary_black
        else -> R.color.static_primary_white
    }

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
            context.getColorRes(color),
            PorterDuff.Mode.SRC_IN
        )
    }

    private fun getPayWaveColor() = when (payWaveType) {
        SPPayWaveType.Light -> R.color.transparent_white_half
        SPPayWaveType.Dark -> R.color.transparent_black_half
    }

    // These strings are temporary and needed to wait for comments from Backend side
    companion object {
        private const val HARDCODED_BLOCKED_TITLE = "Your card is blocked"
        private const val HARDCODED_PENDING_TITLE = "Your card will be activated soon"
        private const val HARDCODED_PHYSICAL_CARD = "Physical Card"
        private const val HARDCODED_DIGITAL_CARD_TEMP = "Digital Card %s"
    }
}