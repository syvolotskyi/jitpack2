package ge.space.design.ui_components.status_messages

import com.example.spacedesignsystem.R

data class SPStatusTextViewSupportsLoading(
    val styleId: Int
)

object SPStatusTextViewStyles {
    val list = listOf(
        SPStatusTextViewSupportsLoading(R.style.SPStatusTextView_Success),
        SPStatusTextViewSupportsLoading(R.style.SPStatusTextView_Error),
        SPStatusTextViewSupportsLoading(R.style.SPStatusTextView_Pending),
        SPStatusTextViewSupportsLoading(R.style.SPStatusTextView_Info)
    )
}