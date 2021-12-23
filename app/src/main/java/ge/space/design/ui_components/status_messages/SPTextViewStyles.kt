package ge.space.design.ui_components.status_messages

import com.example.spacedesignsystem.R

data class SPTextViewSupportsLoading(
    val styleId: Int
)

object SPTextViewStyles {
    val list = listOf(
        SPTextViewSupportsLoading(R.style.SPTextView_Success),
        SPTextViewSupportsLoading(R.style.SPTextView_Error),
        SPTextViewSupportsLoading(R.style.SPTextView_Pending),
        SPTextViewSupportsLoading(R.style.SPTextView_Info)
    )
}