package ge.space.ui.components.dialogs.dialog_types

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import ge.space.spaceui.databinding.SpDialogLayoutBinding
import ge.space.ui.components.dialogs.base.SPBaseDialog
import ge.space.ui.components.dialogs.data.SPDialogDismissHandler
import ge.space.ui.components.dialogs.data.SPDialogIcon
import ge.space.ui.components.dialogs.data.SPDialogInfoHolder
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomButtonLayout
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import ge.space.ui.util.extension.*

/**
 * Dialog for info show which allows to manipulate next parameters:
 *
 * @property title allows to set top title
 * @property label allows to set the second title
 * @property iconVisible describes a dialog icon visibility
 * @property labelVisible describes the dialog second title visibility
 * @property buttonObjects describes dialog bottom buttons
 * @property isButtonsMultiple sets the dialog bottom buttons multiple flag
 * @property buttonsVisible describes the dialog bottom buttons visibility
 */
class SPDialog : SPBaseDialog<SpDialogLayoutBinding, SPDialogInfoHolder>() {

    private val title: String? by argument(KEY_TITLE, null)

    private val label: String? by argument(KEY_LABEL, null)

    private val iconVisible: Boolean by nonNullArgument(KEY_INFO_ICON_VISIBLE, true)

    private val titleVisible: Boolean by nonNullArgument(KEY_TITLE_VISIBLE, true)

    private val labelVisible: Boolean by nonNullArgument(KEY_LABEL_VISIBLE, true)

    private val buttonsVisible: Boolean by nonNullArgument(KEY_BUTTONS_VISIBLE, true)

    private val dialogIcon: SPDialogIcon by nonNullArgument(
        KEY_DIALOG_ICON,
        SPDialogIcon.Info()
    )

    override val buttonObjects: Array<SPDialogInfoHolder> by nonNullArgument(
        KEY_BUTTON_OBJECT,
        arrayOf()
    )

    override val isButtonsMultiple: Boolean by nonNullArgument(KEY_MULTIPLE, false)

    override val dismissHandler: SPDialogDismissHandler? by argument(KEY_DISMISS, null)

    override fun getViewBinding(): SpDialogLayoutBinding =
            SpDialogLayoutBinding.inflate(LayoutInflater.from(context))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleDialogTitles()
        handleVisibility()
        setIconWithColor()
        setButtons()
    }

    private fun handleDialogTitles() {
        with(binding.lytDialogLinear) {
            tvDialogTitle.text = title
            tvDialogTitle.isGone = title == null
            tvDialogLabel.text = label
            tvDialogLabel.isGone = label == null
        }
    }

    private fun handleVisibility() {
        with(binding.lytDialogLinear) {
            ivDialogType.visibleOrGone(iconVisible)
            lytButtons.visibleOrGone(buttonsVisible)
            tvDialogLabel.visibleOrGone(labelVisible)
            dividerDialogLabel.visibleOrGone(titleVisible)
            tvDialogTitle.visibleOrGone(titleVisible)
            vIconDivider.visibleOrGone(iconVisible)
            vIconSpace.visibleOrGone(iconVisible)
            vTitleDivider.visibleOrGone(!iconVisible)
            vTitleSpace.visibleOrGone(!iconVisible)
        }
    }

    private fun setButtons() {
        if (buttonsVisible) {
            with(binding.lytDialogLinear) {
                lytButtons.setBottomButtons(
                    convertDialogButtonsType()
                )
            }
        }
    }

    private fun setIconWithColor() {
        with(binding) {
            lytDialogLinear.ivDialogType.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), dialogIcon.icon)
            )
            lytDialogLinear.ivDialogType.setColorFilter(
                resolveColor(), PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun resolveColor(): Int =
        requireContext().resolveColorByAttr(dialogIcon.colorAttr)

    override fun createDialogButtonModel(buttonObj: SPDialogInfoHolder) =
        SPDialogBottomButtonLayout.SPDialogBottomButtonModel(
            buttonObj.labelTxt,
            SPDialogBottomVerticalButton.BottomButtonType.valueOf(buttonObj.buttonType.toString())
        ) {
            buttonObj.clickEvent?.invoke()
            dismiss()
        }

    override fun setDismissAction() {
        binding.lytRoot.onClick {
            dismiss()
        }
    }
}