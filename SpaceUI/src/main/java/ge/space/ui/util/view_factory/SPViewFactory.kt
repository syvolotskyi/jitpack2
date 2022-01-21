package ge.space.ui.util.view_factory

import android.content.Context
import android.view.View
import ge.space.ui.util.view_factory.component_type.card.SPNewCreditCardImpl
import ge.space.ui.util.view_factory.component_type.chip.*
import ge.space.ui.util.view_factory.component_type.chip.empty.SPEmptyChipIconImpl
import ge.space.ui.util.view_factory.component_type.chip.primary.SPPrimaryChipIconImpl
import ge.space.ui.util.view_factory.component_type.image.SPImageResImpl
import ge.space.ui.util.view_factory.component_type.image.SPImageUrlImpl
import ge.space.ui.util.view_factory.component_type.text.SPEditTextImpl
import ge.space.ui.util.view_factory.component_type.text.SPMaskedEditTextImpl
import ge.space.ui.util.view_factory.component_type.text.SPTextInitialsImpl

interface SPViewFactory {
    companion object {
        /**
         * Returns a view object.
         */
        fun SPViewData.createView(context: Context): View {
            return when (this) {
                is SPViewData.SPImageResourcesData -> SPImageResImpl(context).create(this)
                is SPViewData.SPImageUrlData -> SPImageUrlImpl(context).create(this)
                is SPViewData.SPrimaryChipData -> SPPrimaryChipIconImpl(context).create(this)
                is SPViewData.SPSecondaryChipData -> SPSecondaryChipIconImpl(context).create(this)
                is SPViewData.SPDigitalChipData -> SPDigitalChipIconImpl(context).create(this)
                is SPViewData.SPEmptyChipData -> SPEmptyChipIconImpl(context).create(this)
                is SPViewData.SPNewCreditCards -> SPNewCreditCardImpl(context).create(this)
                is SPViewData.SPChipData -> SPChipIconImpl(context).create(this)
                is SPViewData.SPTextData -> SPTextInitialsImpl(context).create(this)
                is SPViewData.SPEditTextData -> SPEditTextImpl(context).create(this)
                is SPViewData.SPMaskedEditTextData -> SPMaskedEditTextImpl(context).create(this)
            }
        }
    }
}

