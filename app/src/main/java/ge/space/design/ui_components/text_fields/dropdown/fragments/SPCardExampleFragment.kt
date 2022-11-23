package ge.space.design.ui_components.text_fields.dropdown.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpExampleFragmentLayoutBinding
import com.example.spacedesignsystem.databinding.SpItemBankCardShowcaseBinding
import ge.space.design.ui_components.bank_cards.card.SPBankCardSupport
import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPBankCardModel
import ge.space.ui.components.bottomsheet.navigation.SPBottomSheetScreen
import ge.space.ui.components.bottomsheet.navigation.router.SPBottomSheetRouter
import ge.space.ui.components.bottomsheet.strategy.SPFragmentSheetStrategy.Companion.DISMISS_KEY
import ge.space.ui.util.extension.onClick
import org.koin.android.ext.android.inject


class SPCardExampleFragment : Fragment() {

    private val binding by lazy {
        SpExampleFragmentLayoutBinding.inflate(LayoutInflater.from(context))
    }

    private val router: SPBottomSheetRouter by inject()

    private val card = SPBankCardSupport(
        cardModel = SPBankCardModel.SPPhysical("TBC"),
        cardBackground = SPBankCardGradient.SPNoneGradient(SPButtonStyles.BRAND_PRIMARY_COLOR),
        accountNumber = "**** 3232",
        bankLogo = SPButtonStyles.LOGO_TBC,
        amount = "2 000 750 UZS",
        paySystemUrl = SPButtonStyles.VARIANT_UNION_PAY_LOGO
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bankCardsLayout.removeAllViews()
        val itemBinding = SpItemBankCardShowcaseBinding.inflate(
            LayoutInflater.from(context)
                .cloneInContext(ContextThemeWrapper(context, R.style.SPBankCardView_Base)),
            binding.bankCardsLayout,
            true
        )

        with(itemBinding.bankCard) {
            model = card.cardModel
            accountNumber = card.accountNumber
            bankLogo = card.bankLogo
            amount = card.amount
            paySystemUrl = card.paySystemUrl
            cardBackground = card.cardBackground
            payWaveType = card.payWaveType
            status = card.bankCardStatus
            accountNumberStyle = card.accountNumberStyle
            balanceVisible = card.balanceVisible
            isCredit = card.isCredit
            hasChip = card.hasChip
            hasPayWave = card.hasPayWave
            isFavorite = card.isFavorite
            accountVisible = card.accountVisible
        }

        binding.nextButton.onClick {
            router.openScreen(SPBottomSheetScreen { SPTextExampleFragment() })
        }

        binding.saveButton.onClick {
            router.sendResult(DISMISS_KEY,"Closed")
        }
    }
}