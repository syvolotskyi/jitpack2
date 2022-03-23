package ge.space.ui.components.text_fields.input.base

import android.text.InputType
import ge.space.ui.util.view_factory.SPViewData

/**
 * Sealed class helps to handle text input view type for SPTextFieldInput.
 *
 * Contains:
 * @class SPEditTextViewType [SPTextInputViewType] provides an information for edit text.
 * @class SPMaskViewType [SPTextInputViewType] provides an information for masked edit text,
 *      using for phone entering.
 * @class SPNumberViewType [SPTextInputViewType] provides an information for number input edit text.
 */

sealed class SPTextInputViewType {
    data class SPEditTextViewType(
        var hint: String? = null,
        var lines: Int? = 1,
        var inputType: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD,
        var params: SPViewData.SPViewDataParams? = null
    ) : SPTextInputViewType()

    data class SPMaskViewType(
        var mask: String,
        var hint: String? = null
    ) : SPTextInputViewType()

    data class SPNumberViewType(
        var hint: String? = null,
        var inputType: Int = InputType.TYPE_CLASS_NUMBER,
    ) : SPTextInputViewType()

    companion object {
        const val TEXT = 0
        const val DATE_MASKED = 1
        const val CARD_MASKED = 2
        const val NUMBER = 3
        const val EMAIL = 4
        const val AMOUNT_INTEGER = 5
        const val AMOUNT_DECIMAL = 6
    }
}
