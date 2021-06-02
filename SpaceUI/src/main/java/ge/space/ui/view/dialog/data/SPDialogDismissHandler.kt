package ge.space.ui.view.dialog.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SPDialogDismissHandler(
    val onDismissed: (() -> Unit?)? = null
): Parcelable