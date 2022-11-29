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
import ge.space.ui.components.dialogs.builder.SPEditTextDialogBuilder
import ge.space.ui.components.dialogs.builder.SPInfoDialogBuilder
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
class SPDialog(
    val title: String?,
    val label: String?,
    private val iconVisible: Boolean,
    private val titleVisible: Boolean,
    private val labelVisible: Boolean,
    private val buttonsVisible: Boolean,
    private val dialogIcon: SPDialogIcon,
    override val buttonObjects: Array<SPDialogInfoHolder>,
    override val isButtonsMultiple: Boolean,
    override val dismissHandler: SPDialogDismissHandler?
) : SPBaseDialog<SpDialogLayoutBinding, SPDialogInfoHolder>() {

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
        requireContext().getColorFromAttribute(dialogIcon.colorAttr)

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

    internal constructor(builder: SPInfoDialogBuilder) : this(
        builder.title,
        builder.label,
        builder.infoIconVisible,
        builder.titleVisible,
        builder.labelVisible,
        builder.buttonsVisible,
        builder.dialogIcon,
        builder.buttons,
        builder.isMultiple,
        builder.dismissHandler
    )

    companion object {
        inline fun dialog(block: SPInfoDialogBuilder.() -> Unit) =
            SPInfoDialogBuilder().apply(block).build()
    }
}