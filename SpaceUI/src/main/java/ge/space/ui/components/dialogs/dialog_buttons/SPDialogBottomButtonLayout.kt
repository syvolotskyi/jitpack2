package ge.space.ui.components.dialogs.dialog_buttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import ge.space.ui.util.extension.bottomType
import ge.space.spaceui.databinding.SpDialogBottomButtonLayoutBinding
import ge.space.ui.util.extension.onClick

/**
 * Helper custom view which allows to manipulate a group of bottom buttons
 */
class SPDialogBottomButtonLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding =
        SpDialogBottomButtonLayoutBinding.inflate(
            LayoutInflater.from(context), this
        )

    /**
     * Sets a group of buttons and applies their types
     */
    fun setBottomButtons(bottomButton: SPDialogBottomButton) {
        when(bottomButton) {
            is SPDialogBottomButton.SPDialogBottomButtonMultiple -> {
                handleMultiple(bottomButton)
            }
            is SPDialogBottomButton.SPDialogBottomButtonTwice -> {
                handleTwice(bottomButton)
            }
        }
    }

    private fun handleTwice(bottomButton: SPDialogBottomButton.SPDialogBottomButtonTwice) {
        visibleTwice()
        with(binding) {
            btnDialogLeft.bottomType(bottomButton.buttonLeft.type)
            btnDialogRight.bottomType(bottomButton.buttonRight.type)
            btnDialogLeft.text = bottomButton.buttonLeft.label
            btnDialogRight.text = bottomButton.buttonRight.label

            btnDialogLeft.onClick {
                bottomButton.buttonLeft.clickEvent?.invoke()
            }

            btnDialogRight.onClick {
                bottomButton.buttonRight.clickEvent?.invoke()
            }
        }
    }

    private fun visibleTwice() {
        with(binding) {
            lytTwice.isVisible = true
            vButtonTwiceDivider.isVisible = true
            lytMultiple.isGone = true
        }
    }

    private fun handleMultiple(bottomButton: SPDialogBottomButton.SPDialogBottomButtonMultiple) {
        visibleMultiple()
        bottomButton.buttons.forEach { button ->
            val dialogButton = SPDialogBottomVerticalButton(context)
            dialogButton.buttonType = button.type
            dialogButton.text = button.label
            dialogButton.onClick {
                button.clickEvent?.invoke()
            }

            binding.lytMultiple.addView(dialogButton)
        }
    }

    private fun visibleMultiple() {
        with(binding) {
            lytTwice.isGone = true
            vButtonTwiceDivider.isGone = true
            lytMultiple.isVisible = true
        }
    }

    /**
     * Contains info about the dialog button
     */
    sealed class SPDialogBottomButton {

        /**
         * Applies both for left and right button
         */
        data class SPDialogBottomButtonTwice(
            val buttonLeft: SPDialogBottomButtonModel, val buttonRight: SPDialogBottomButtonModel
        ) : SPDialogBottomButton()

        /**
         * Applies for multiple buttons as a list
         */
        data class SPDialogBottomButtonMultiple(
            val buttons: List<SPDialogBottomButtonModel>
        ) : SPDialogBottomButton()
    }

    /**
     * Applies info about a button model
     */
    data class SPDialogBottomButtonModel(
        val label: String,
        val type: SPDialogBottomVerticalButton.BottomButtonType = SPDialogBottomVerticalButton.BottomButtonType.Default,
        val clickEvent: (() -> Unit?)? = null
    )
}