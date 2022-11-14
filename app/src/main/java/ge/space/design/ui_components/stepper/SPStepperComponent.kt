package ge.space.design.ui_components.stepper

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemBankCardShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutStepperShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.bank_cards.card.SPBankCardSupport
import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.spaceui.databinding.SpProgressNavigatorItemBinding
import ge.space.ui.components.bank_cards.data.SPBankCardModel
import ge.space.ui.components.bottomsheet.core.SPListAdapter
import ge.space.ui.components.list_adapter.SPBaseListAdapter

class SPStepperComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.stepper_showcase

    override fun getDescriptionResId(): Int = R.string.stepper_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutStepperShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val adapter = SPListAdapter<SpItemBankCardShowcaseBinding, SPBankCardSupport>(false)
            .setup {
                onCreate { parent ->
                    SpItemBankCardShowcaseBinding.inflate(
                        environment.requireThemedLayoutInflater(R.style.SPBankCardView_Base),
                        parent,
                        false
                    )
                }
                onBind { binding, item, _ ->
                    with(binding.bankCard) {
                        model = item.item.cardModel
                        accountNumber = item.item.accountNumber
                        bankLogo = item.item.bankLogo
                        amount = item.item.amount
                        paySystemUrl = item.item.paySystemUrl
                        cardBackground = item.item.cardBackground
                        payWaveType = item.item.payWaveType
                        status = item.item.bankCardStatus
                        accountNumberStyle = item.item.accountNumberStyle
                        balanceVisible = item.item.balanceVisible
                        isCredit = item.item.isCredit
                        hasChip = item.item.hasChip
                        hasPayWave = item.item.hasPayWave
                        isFavorite = item.item.isFavorite
                        accountVisible = item.item.accountVisible
                    }
                }
            }
            adapter.setAdapterItems(SPButtonStyles.list)
            layoutBinding.recyclerview.layoutManager = LinearLayoutManager(environment.context, LinearLayoutManager.HORIZONTAL, false)
            layoutBinding.recyclerview.adapter = adapter

            layoutBinding.recyclerviewPagerIndicatorHorizontal.attachToRecyclerView(layoutBinding.recyclerview)
            return layoutBinding.root
        }
    }
}