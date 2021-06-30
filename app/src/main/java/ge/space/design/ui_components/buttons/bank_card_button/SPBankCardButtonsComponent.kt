package ge.space.design.ui_components.buttons.bank_card_button

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemAddBankCardButtonShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutBankCardButtonsShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.buttons.SPAddBankCardButton

class SPBankCardButtonsComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_bank_card_buttons

    override fun getDescriptionResId(): Int = R.string.component_bank_card_button_description

    override fun getComponentClass(): Class<*>? = Factory::class.java

    class Factory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutBankCardButtonsShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val cards = mutableListOf<SPAddBankCardButton>()

            SPBankCardButtonStyles.list.forEach { bankCardSample ->
                val itemBinding = SpItemAddBankCardButtonShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.bankCardsButtonsLayout,
                    true
                )

                with(itemBinding.addBankCardButton) {
                    cardSize = bankCardSample.size
                    setOnClickListener {
                        Toast.makeText(environment.context, "Clicked", Toast.LENGTH_SHORT).show()
                    }
                }

                cards.add(itemBinding.addBankCardButton)
            }

            return binding.root
        }
    }
}