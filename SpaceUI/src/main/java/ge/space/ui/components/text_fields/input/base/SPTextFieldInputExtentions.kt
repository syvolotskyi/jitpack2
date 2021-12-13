package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.view.Gravity
import android.widget.EditText
import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView
import ge.space.ui.util.view_factory.component_type.chip.primary.SPDefaultPrimaryChipData
import ge.space.ui.util.view_factory.extentions.getCurrencyViewData
import ge.space.ui.util.view_factory.extentions.getNumberEditTextViewData

/**
 * Setup a view as input for number and currency
 */
fun SPTextFieldInput.setupNumberInput(currency: String) {
    setupContextViewByType(SPContextViewType.NUMBER)
    setupTrailViewByType(SPTrailViewType.CURRENCY, currency = currency)
}

/**
 * Setup a view as input for phone
 */
fun SPTextFieldInput.setupPhoneInput(prefix: String, mask:String) {
    setupContextViewByType(SPContextViewType.MASK, inputMask = mask)
    setupTrailViewByType(SPTrailViewType.NONE)
    setupLeadingViewByType(SPLeadingViewType.PHONE_PREFIX, phonePrefix = prefix)
}

/**
 * Setup a Context View due to type
 */
fun SPTextFieldInput.setupContextViewByType(
    type: SPContextViewType,
    inputMask: String = mask,
    inputHint: String = hint
) {
    contextView = when (type) {
        SPContextViewType.TEXT -> SPViewData.SPEditTextData(
            R.style.h600_bold_caps,
            inputHint
        ).createView(context)
        SPContextViewType.MASK -> SPViewData.SPMaskedEditTextData(
            R.style.h600_bold_text_field_phone,
            inputMask,
            inputHint
        ).createView(context)
        SPContextViewType.NUMBER -> getNumberEditTextViewData(
            context,
            inputHint
        ).createView(context)
    } as EditText
}

/**
 * Setup a trailView due to type
 */
fun SPTextFieldInput.setupTrailViewByType(
    type: SPTrailViewType,
    image: Int = 0,
    currency: String = ""
) {
    trailView = when (type) {
        SPTrailViewType.NONE -> null
        SPTrailViewType.CARD -> getSmallCardView(context)
        SPTrailViewType.CURRENCY -> getCurrencyViewData(context, currency)
        SPTrailViewType.REMOVABLE -> SPViewData.SPImageResourcesData(
            R.drawable.ic_close_circle_24_filled,
            SPViewData.SPViewDataParams(
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
            )
        )
        SPTrailViewType.IMAGE -> SPViewData.SPImageResourcesData(
            image,
            SPViewData.SPViewDataParams(
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
            )
        )
    }?.createView(context)

    if (type == SPTrailViewType.REMOVABLE)
        setTrailClickListener { removeAllText() }
}

/**
 * Setup a leadingView due to type
 */
fun SPTextFieldInput.setupLeadingViewByType(
    type: SPLeadingViewType,
    icon: Int = R.drawable.ic_chat_message_24_regular,
    phonePrefix: String = EMPTY_TEXT
) {
    leadingView = when (type) {
        SPLeadingViewType.NONE -> null
        SPLeadingViewType.CARD -> getSmallCardView(context)
        SPLeadingViewType.PHONE_PREFIX -> SPViewData.SPTextData(
            phonePrefix,
            textStyle = R.style.h600_bold_text_field_phone,
            SPViewData.SPViewDataParams(
                gravity =  Gravity.END,
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_4),
                paddingBottom = context.resources.getDimensionPixelSize(R.dimen.dimen_p_1),
            )
        )
        SPLeadingViewType.IMAGE -> SPViewData.SPImageResourcesData(
            icon,
            SPViewData.SPViewDataParams(
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
            )
        )

    }?.createView(context)
}

fun getSmallCardView(context: Context): SPViewData.SPrimaryChipData =
    SPDefaultPrimaryChipData.getSmallChipData(
        context, SPViewData.SPViewDataParams(
            paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12),
            paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12)
        )
    )