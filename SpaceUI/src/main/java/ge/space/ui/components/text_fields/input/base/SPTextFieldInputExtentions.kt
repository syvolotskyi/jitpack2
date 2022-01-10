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
    setupContentInputViewByType(SPTextInputViewType.NUMBER)
    setupEndViewByType(SPEndViewType.CURRENCY, currency = currency)
}

/**
 *  Setup a view as input for date
 */
fun SPTextFieldInput.setupDateInput(mask: String) {
    setupContentInputViewByType(SPTextInputViewType.MASK, inputMask = mask)
    setupEndViewByType(SPEndViewType.NONE)
    setupStartViewByType(SPStartViewType.NONE)
}

/**
 * Setup a view as input for phone
 */
fun SPTextFieldInput.setupPhoneInput(prefix: String, mask:String) {
    setupStartViewByType(SPStartViewType.PHONE_PREFIX, phonePrefix = prefix)
    setupContentInputViewByType(SPTextInputViewType.MASK, inputMask = mask, hint)
    setupEndViewByType(SPEndViewType.NONE)
}

/**
 * Setup a Content View due to type
 */
fun SPTextFieldInput.setupContentInputViewByType(
    type: SPTextInputViewType,
    inputMask: String = mask,
    inputHint: String = hint
) {
    contentInputView = when (type) {
        SPTextInputViewType.TEXT -> SPViewData.SPEditTextData(
            textAppearance,
            inputHint
        ).createView(context)
        SPTextInputViewType.MASK -> SPViewData.SPMaskedEditTextData(
            textAppearance,
            inputMask,
            inputHint
        ).createView(context)
        SPTextInputViewType.NUMBER -> getNumberEditTextViewData(
            context,
            inputHint
        ).createView(context)
    } as EditText
}

/**
 * Setup a endView due to type
 */
fun SPTextFieldInput.setupEndViewByType(
    type: SPEndViewType,
    image: Int = 0,
    currency: String = ""
) {
    endView = when (type) {
        SPEndViewType.NONE -> null
        SPEndViewType.CARD -> getSmallCardView(context)
        SPEndViewType.CURRENCY -> getCurrencyViewData(context, currency)
        SPEndViewType.REMOVABLE -> SPViewData.SPImageResourcesData(
            R.drawable.ic_close_circle_24_filled,
            SPViewData.SPViewDataParams(
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
            )
        )
        SPEndViewType.IMAGE -> SPViewData.SPImageResourcesData(
            image,
            SPViewData.SPViewDataParams(
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
            )
        )
    }?.createView(context)

    if (type == SPEndViewType.REMOVABLE)
        setTrailClickListener { removeAllText() }
}

/**
 * Setup a startView due to type
 */
fun SPTextFieldInput.setupStartViewByType(
    type: SPStartViewType,
    icon: Int = R.drawable.ic_chat_message_24_regular,
    phonePrefix: String = EMPTY_TEXT
) {
    startView = when (type) {
        SPStartViewType.NONE -> null
        SPStartViewType.CARD -> getSmallCardView(context)
        SPStartViewType.PHONE_PREFIX -> SPViewData.SPTextData(
            phonePrefix,
            textAppearance,
            SPViewData.SPViewDataParams(
                gravity =  Gravity.END,
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16),
                paddingBottom = context.resources.getDimensionPixelSize(R.dimen.dimen_p_1),
            )
        )
        SPStartViewType.IMAGE -> SPViewData.SPImageResourcesData(
            icon,
            SPViewData.SPViewDataParams(
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
            )
        )

    }?.createView(context)
}

private fun getSmallCardView(context: Context): SPViewData.SPrimaryChipData =
    SPDefaultPrimaryChipData.getSmallChipData(
        context, SPViewData.SPViewDataParams(
            paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12),
            paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12)
        )
    )