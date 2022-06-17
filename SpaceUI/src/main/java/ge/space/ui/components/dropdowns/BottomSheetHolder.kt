package ge.space.ui.components.dropdowns

import android.os.Parcelable
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import kotlinx.parcelize.Parcelize




/**
 * Marker class for buttons dialogs
 */
@Parcelize
open class BottomSheetHolder : Parcelable

/**
 * Wrapper which allows to pass texts for both button Label and button type with click action
 * for a button model.
 *
 * @property labelTxt applies a button title
 * @property buttonType applies a button type for its style
 * @property clickEvent applies a click action for a button
 */
@Parcelize
class SPBottomSheetInfoHolder(
    val labelTxt: String,
    val buttonType: SPDialogBottomVerticalButton.BottomButtonType,
    val clickEvent: (() -> Unit?)? = null
) : BottomSheetHolder()

/**
 * Wrapper which allows to pass texts for both button Label and button type with click action
 * for a button model.
 *
 * @property labelTxt applies a button title
 * @property buttonType applies a button type for its style
 * @property clickEvent applies a click action for a button
 */
@Parcelize
class SPEditTextHolder(
    val labelTxt: String,
    val buttonType: SPDialogBottomVerticalButton.BottomButtonType,
    val clickEvent: ((String?) -> Unit?)? = null
) : BottomSheetHolder()