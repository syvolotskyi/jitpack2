package ge.space.ui.components.text_fields.input.base

import android.text.InputType

sealed class SPTextInputViewType {
    data class SPTextViewType(
        var hint: String? = null,
        var inputType: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    ) : SPTextInputViewType()

    data class SPTextAreaViewType(
        var hint: String? = null,
        var inputType: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    ) : SPTextInputViewType()

    data class SPMaskViewType(
        var mask: String,
        var hint: String? = null
    ) : SPTextInputViewType()

    data class SPNumberViewType(
        var hint: String? = null
    ) : SPTextInputViewType()

    companion object {
        const val TEXT = 0
        const val DATE_MASKED = 1
        const val CARD_MASKED = 2
        const val NUMBER = 3
        const val TEXT_AREA = 4
    }
}
