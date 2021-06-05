package ge.space.ui.util.extension

import ge.space.spaceui.R
import ge.space.ui.components.button.SPButton
import ge.space.ui.components.dialog.dialog_buttons.SPDialogBottomVerticalButton

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
                R.style.SPMediumTransparentButtonView
            )
        SPDialogBottomVerticalButton.BottomButtonType.Remove ->
            setButtonStyle(
                R.style.SPRemoveMediumTransparentButtonView
            )
        SPDialogBottomVerticalButton.BottomButtonType.Cancel ->
            setButtonStyle(
                R.style.SPCancelMediumTransparentButtonView
            )
    }
}