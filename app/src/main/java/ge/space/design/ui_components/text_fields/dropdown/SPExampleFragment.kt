package ge.space.design.ui_components.text_fields.dropdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpExampleFragmentLayoutBinding
import com.example.spacedesignsystem.databinding.SpItemBankCardShowcaseBinding
import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.ui.components.bottomsheet.core.SPBottomSheetBaseFragment

class SPExampleFragment : SPBottomSheetBaseFragment<String>() {

    private var dismiss: (String) -> Unit = {}

    private val binding by lazy {
        SpExampleFragmentLayoutBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SPButtonStyles.list.forEach { bankCardSample ->
            val itemBinding = SpItemBankCardShowcaseBinding.inflate(
                LayoutInflater.from(context).cloneInContext(ContextThemeWrapper(context, R.style.SPBankCardView_Base)),
                binding.bankCardsLayout,
                true
            )

            with(itemBinding.bankCard) {
                model = bankCardSample.cardModel
                accountNumber = bankCardSample.accountNumber
                bankLogo = bankCardSample.bankLogo
                amount = bankCardSample.amount
                paySystemUrl = bankCardSample.paySystemUrl
                cardBackground = bankCardSample.cardBackground
                payWaveType = bankCardSample.payWaveType
                status = bankCardSample.bankCardStatus
                accountNumberStyle = bankCardSample.accountNumberStyle
                balanceVisible = bankCardSample.balanceVisible
                isCredit = bankCardSample.isCredit
                hasChip = bankCardSample.hasChip
                hasPayWave = bankCardSample.hasPayWave
                isFavorite = bankCardSample.isFavorite
                accountVisible = bankCardSample.accountVisible
            }
        }
        binding.saveButton.onClick {
            onDismiss("Closed")
        }
    }

}