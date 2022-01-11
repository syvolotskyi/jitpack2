package ge.space.ui.components.text_fields.input.base

import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R

sealed class SPStartViewType{
    object SPNoneViewType : SPStartViewType()
    object SPCardViewType : SPStartViewType()
    data class SPPhonePrefixViewType(val phonePrefix: String = EMPTY_TEXT) : SPStartViewType()
    data class SPImageViewType(val icon: Int = R.drawable.ic_chat_message_24_regular) :
        SPStartViewType()

    companion object{
        const val CARD = 1
        const val PHONE_PREFIX = 2
        const val IMAGE = 3
    }
}