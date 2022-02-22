package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.SPACE
import ge.space.spaceui.R
import ge.space.ui.components.text_fields.input.utils.extension.doOnTextChanged
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView
import ge.space.ui.util.view_factory.component_type.chip.primary.SPDefaultPrimaryChipData.Companion.getSmallChipData
import ge.space.ui.util.view_factory.extentions.getCurrencyViewData
import ge.space.ui.util.view_factory.extentions.getNumberEditTextViewData

/**
 * Setup a view as input for number and currency
 */
fun SPTextFieldInput.setupNumberInput(currency: String) {
    inputType = SPTextInputViewType.AMOUNT_DECIMAL
    setupContentInputViewByType(SPTextInputViewType.SPNumberViewType())
    setupEndViewByType(SPEndViewType.SPCurrencyViewType(currency))
}

/**
 * Setup a view as input for amount decimal (with formatter) and currency
 */
fun SPTextFieldInput.setupAmountDecimalInput(currency: String) {
    inputType = SPTextInputViewType.AMOUNT_DECIMAL
    setupContentInputViewByType(SPTextInputViewType.SPNumberViewType(inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS))
    setupEndViewByType(SPEndViewType.SPCurrencyViewType(currency))
}

/**
 *  Setup a view as input for date
 */
fun SPTextFieldInput.setupDateInput(mask: String) {
    setupContentInputViewByType(SPTextInputViewType.SPMaskViewType(mask, hint))
    setupEndViewByType(SPEndViewType.SPNoneViewType)
    setupStartViewByType(SPStartViewType.SPNoneViewType)
}

/**
 * Setup a view as input for phone
 */
fun SPTextFieldInput.setupPhoneInput(prefix: String, mask: String) {
    setupStartViewByType(SPStartViewType.SPPhonePrefixViewType(phonePrefix = prefix))
    setupContentInputViewByType(SPTextInputViewType.SPMaskViewType(mask, hint))
    setupEndViewByType(SPEndViewType.SPNoneViewType)
}

/**
 * Setup a view as input for card
 *
 * @property mask [String] value which sets a mask.
 * @property cardEnteredListener [(cardNumber: String) -> Boolean] functions called when user enters card number,
 * require a Boolean result,
 * where true - card is checked, false - card is wrong.
 */
fun SPTextFieldInput.setupCardInput(
    mask: String = context.getString(R.string.card_mask),
    cardEnteredListener: (cardNumber: String) -> Boolean
) {
    setupStartViewByType(SPStartViewType.SPNoneViewType)
    setupContentInputViewByType(SPTextInputViewType.SPMaskViewType(mask, hint))
    setupEndViewByType(SPEndViewType.SPRemovableViewType)

    doOnTextChanged { text, _, _, _ ->
        if (!cardEnteredListener(
                text.toString().replace("X", EMPTY_TEXT)
                    .replace(SPACE, EMPTY_TEXT)
            )
        )
            setupEndViewByType(SPEndViewType.SPRemovableViewType)
    }
}


/**
 * Setup a Content View due to type
 */
fun SPTextFieldInput.setupContentInputViewByType(
    type: SPTextInputViewType
) {
    contentInputView = (when (type) {
        is SPTextInputViewType.SPEditTextViewType -> SPViewData.SPEditTextData(
            textAppearance,
            type.hint,
            type.inputType,
            type.lines,
            params = type.params ?: SPViewData.SPViewDataParams(
                gravity = Gravity.START or Gravity.CENTER_VERTICAL,
                paddingBottom = context.resources.getDimensionPixelSize(R.dimen.dimen_p_1)
            )
        )
        is SPTextInputViewType.SPMaskViewType -> SPViewData.SPMaskedEditTextData(
            textAppearance,
            type.mask,
            type.hint,
            SPViewData.SPViewDataParams(
                gravity = Gravity.START or Gravity.CENTER_VERTICAL,
                paddingBottom = context.resources.getDimensionPixelSize(R.dimen.dimen_p_1)
            )
        )
        is SPTextInputViewType.SPNumberViewType -> getNumberEditTextViewData(
            hint = type.hint,
            inputType = type.inputType,
            textAppearance
        )
    }).createView(context) as EditText


}

/**
 * Setup a endView due to type
 */
fun SPTextFieldInput.setupEndViewByType(
    type: SPEndViewType
) {
    endView = when (type) {
        is SPEndViewType.SPNoneViewType -> null
        is SPEndViewType.SPCardViewType -> getSmallCardView(context)
        is SPEndViewType.SPCurrencyViewType -> getCurrencyViewData(context, type.currency)
        is SPEndViewType.SPRemovableViewType -> SPViewData.SPImageResourcesData(
            R.drawable.ic_close_circle_24_filled,
            params = SPViewData.SPViewDataParams(
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
            )
        )
        is SPEndViewType.SPImageViewType -> SPViewData.SPImageResourcesData(
            type.icon,
            params = SPViewData.SPViewDataParams(
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
            )
        )
    }?.createView(context)

    if (type == SPEndViewType.SPRemovableViewType)
        setEndClickListener { removeAllText() }
}

/**
 * Setup a startView due to type
 */
fun SPTextFieldInput.setupStartViewByType(
    type: SPStartViewType

) {
    startView = when (type) {
        is SPStartViewType.SPNoneViewType -> null
        is SPStartViewType.SPCardViewType -> getSmallCardView(context)
        is SPStartViewType.SPPhonePrefixViewType -> SPViewData.SPTextData(
            type.phonePrefix,
            textAppearance,
            params = SPViewData.SPViewDataParams(
                gravity = Gravity.END or Gravity.CENTER_VERTICAL,
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_4),
                paddingBottom = context.resources.getDimensionPixelSize(R.dimen.dimen_p_1)
            )
        )
        is SPStartViewType.SPImageViewType -> SPViewData.SPImageResourcesData(
            type.icon,
            params = SPViewData.SPViewDataParams(
                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16),
                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
            )
        )

    }?.createView(context)
}

fun getSmallCardView(context: Context): SPViewData.SPrimaryChipData =
    getSmallChipData(
        context, SPViewData.SPViewDataParams(
            paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12),
            paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12)
        )
    )