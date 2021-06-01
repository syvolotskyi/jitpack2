package ge.space.ui.view.dialog.data

import android.os.Parcelable
import ge.space.ui.view.dialog.view.SPDialogBottomVerticalButton
import kotlinx.android.parcel.Parcelize

@Parcelize
class SPDialogInfoHolder(
    val labelTxt: String,
    val buttonType: SPDialogBottomVerticalButton.BottomButtonType,
    val clickEvent: (() -> Unit?)? = null
) : Parcelable
