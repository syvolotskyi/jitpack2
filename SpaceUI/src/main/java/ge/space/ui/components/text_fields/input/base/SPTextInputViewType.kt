package ge.space.ui.components.text_fields.input.base

import android.text.InputType
import ge.space.ui.util.view_factory.SPViewData

sealed class SPTextInputViewType {
    data class SPEditTextViewType(
        var hint: String? = null,
        var lines: Int? = 1,
        var inputType: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD,
        var params: SPViewData.SPViewDataParams? = null
    ) : SPTextInputViewType()

    data class SPTextViewType(
        var text: String? = null,
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
        const val EDIT_TEXT = 0
        const val DATE_MASKED = 1
        const val CARD_MASKED = 2
        const val NUMBER = 3
        const val TEXT = 4
    }
}
