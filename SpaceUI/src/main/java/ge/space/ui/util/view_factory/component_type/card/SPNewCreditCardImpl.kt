package ge.space.ui.util.view_factory.component_type.card

import android.content.Context
import ge.space.ui.components.bank_cards.chip.credit_new_card.SPNewCreditCard
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPNewCreditCardImpl(context: Context) : SPViewImpl<SPViewData.SPNewCreditCards>(context) {

    override fun create(type: SPViewData.SPNewCreditCards): SPNewCreditCard {
        return SPNewCreditCard(context).apply {
            size = type.chipSize
        }
    }

}