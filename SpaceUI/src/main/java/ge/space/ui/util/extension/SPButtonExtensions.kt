package ge.space.ui.util.extension

import ge.space.spaceui.R
import ge.space.ui.components.buttons.SPButton
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton

/**
 * Sets a dialog bottom button style by a specific type
 *
 * @param type [SPDialogBottomVerticalButton.BottomButtonType] keeps a type for
 * a specific style
 */
fun SPButton.bottomType(type : SPDialogBottomVerticalButton.BottomButtonType) {
    when(type) {
        SPDialogBottomVerticalButton.BottomButtonType.Default ->
            setButtonStyle(
                R.style.SPButtonTransparent
            )
        SPDialogBottomVerticalButton.BottomButtonType.Remove ->
            setButtonStyle(
                R.style.SPButtonRemoveTransparentView
            )
        SPDialogBottomVerticalButton.BottomButtonType.Cancel ->
            setButtonStyle(
                R.style.SPButtonCancelTransparentView
            )
    }
}