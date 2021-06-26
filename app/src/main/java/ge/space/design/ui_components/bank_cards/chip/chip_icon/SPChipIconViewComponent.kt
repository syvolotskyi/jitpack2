package ge.space.design.ui_components.bank_cards.chip.chip_icon

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemChipIconShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutChipIconShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.bank_cards.chip.SPChipIcon

class SPChipIconViewComponent : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.chip_icons

    override fun getDescriptionResId(): Int = R.string.chip_icons_description

    override fun getComponentClass(): Class<*>? = Factory::class.java

    class Factory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutChipIconShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val cards = mutableListOf<SPChipIcon>()

            SPChipIconStyles.list.forEach { bankCardSample ->
                val itemBinding = SpItemChipIconShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView_EmptySmall_Base),
                    binding.bankCardsButtonsLayout,
                    true
                )

                with(itemBinding.addBankCardButton) {
                    cardSize = bankCardSample.size
                    iconAppearance = bankCardSample.iconAppearance
                    icon = bankCardSample.icon
                    bigPhotoUrl = bankCardSample.photoUrl
                }

                cards.add(itemBinding.addBankCardButton)
            }

            return binding.root
        }
    }
}