package ge.space.ui.components.dialogs.dialog_buttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.core.view.isVisible
import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpDialogBottomVerticalButtonLayoutBinding
import ge.space.ui.components.dialogs.base.SPBaseDialog
import ge.space.ui.util.extension.bottomType

/**
 * Helper view which allows to manipulate [SPBaseDialog] bottom buttons easily. The view
 * applies one bottom button.
 */
class SPDialogBottomVerticalButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding =
        SpDialogBottomVerticalButtonLayoutBinding.inflate(
            LayoutInflater.from(context), this
        )

    /**
     * Sets a bottom divider visibility
     */
    var isDividerVisible = true
        set(value) {
            field = value
            binding.vDivider.isVisible = value
        }

    /**
     * Applies a button text
     */
    var text = EMPTY_TEXT
        set(value) {
            field = value
            binding.btnDialog.text = value
        }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.btnDialog.setOnClickListener(l)
    }

    /**
     * Applies a button type. [BottomButtonType.Default] by default.
     *
     * [BottomButtonType.Default] applies default style [R.style.SPButton.Dialog.Transparent]
     * [BottomButtonType.Cancel] applies cancel button style [R.style.SPButton.Dialog.Default]
     * [BottomButtonType.Remove] applies remove button style [R.style.SPButton.Dialog.Deny]
     */
    var buttonType = BottomButtonType.Default
        set(value) {
            field = value

            binding.btnDialog.bottomType(value)
        }

    /**
     * Applies a bottom button style by its type
     */
    enum class BottomButtonType {
        /**
         * Applies default style [R.style.SPButton.Dialog.Transparent]
         */
        Default,

        /**
         * Applies remove button style [R.style.SPButton.Dialog.Deny]
         */
        Remove,

        /**
         * Applies cancel button style [R.style.SPButton.Dialog.Default]
         */
        Cancel
    }
}