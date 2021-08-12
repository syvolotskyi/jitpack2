package ge.space.design.ui_components.bank_cards.card

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemBankCardShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutBankCardShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.bank_cards.card.SPBankCardView

class SPBankCardViewComponent : ShowCaseComponent {
    override fun getNameResId(): Int = R.string.component_bank_card_views

    override fun getDescriptionResId(): Int = R.string.component_bank_card_views_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutBankCardShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val cards = mutableListOf<SPBankCardView>()

            SPButtonStyles.list.forEach { bankCardSample ->
                val itemBinding = SpItemBankCardShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_Base),
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

                cards.add(itemBinding.bankCard)
            }

            return binding.root
        }
    }
}