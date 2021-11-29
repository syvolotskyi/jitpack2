package ge.space.design.ui_components.status_messages

import com.example.spacedesignsystem.R
import ge.space.design.ui_components.buttons.vertical_button.SPButtonSupportsLoading

data class SPStatusTextViewSupportsLoading(
    val styleId: Int
)

object SPStatusTextViewStyles {
    val list = listOf(
        SPStatusTextViewSupportsLoading(R.style.SPStatusMessage_Success),
        SPStatusTextViewSupportsLoading(R.style.SPStatusMessage_Error),
        SPStatusTextViewSupportsLoading(R.style.SPStatusMessage_Pending),
        SPStatusTextViewSupportsLoading(R.style.SPStatusMessage_Info)
    )
}