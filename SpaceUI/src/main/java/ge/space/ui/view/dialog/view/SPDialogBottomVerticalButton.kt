package ge.space.ui.view.dialog.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.ui.util.extension.bottomType
import ge.space.spaceui.databinding.SpDialogBottomVerticalButtonLayoutBinding
import ge.space.ui.base.SPBaseView.Companion.EMPTY_TEXT
import ge.space.ui.view.dialog.base.SPBaseDialog

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

    /**
     * Applies a button type. [BottomButtonType.Default] by default.
     *
     * [BottomButtonType.Default] applies default style [R.style.SPBaseView_SPBaseButton_Medium_Transparent]
     * [BottomButtonType.Cancel] applies cancel button style [R.style.SPBaseView_SPBaseButton_Medium_Transparent_Cancel]
     * [BottomButtonType.Remove] applies remove button style [R.style.SPBaseView_SPBaseButton_Medium_Transparent_Remove]
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
         * Applies default style [R.style.SPBaseView_SPBaseButton_Medium_Transparent]
         */
        Default,

        /**
         * Applies remove button style [R.style.SPBaseView_SPBaseButton_Medium_Transparent_Remove]
         */
        Remove,

        /**
         * Applies cancel button style [R.style.SPBaseView_SPBaseButton_Medium_Transparent_Cancel]
         */
        Cancel
    }
}