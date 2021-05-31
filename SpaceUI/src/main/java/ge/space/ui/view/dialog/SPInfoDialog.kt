package ge.space.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.FragmentActivity
import ge.space.spaceui.databinding.SpInfoDialogBinding
import ge.space.ui.util.extension.argument
import ge.space.ui.util.extension.nonNullArgument
import ge.space.ui.util.extension.visibleOrGone
import ge.space.ui.view.dialog.base.SPBaseDialog
import ge.space.ui.view.dialog.base.SPBaseDialogBuilder
import ge.space.ui.view.dialog.view.SPDialogBottomButtonLayout
import ge.space.ui.view.dialog.view.SPDialogBottomVerticalButton


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
 * @property clickAction is triggered when a button of the dialog is triggered
 */
class SPInfoDialog : SPBaseDialog<SpInfoDialogBinding>() {

    private val title: String? by argument(KEY_TITLE, null)

    private val label: String? by argument(KEY_LABEL, null)

    private val iconVisible: Boolean by nonNullArgument(KEY_INFO_ICON_VISIBLE, true)

    private val titleVisible: Boolean by nonNullArgument(KEY_TITLE_VISIBLE, true)

    private val labelVisible: Boolean by nonNullArgument(KEY_LABEL_VISIBLE, true)

    private val buttonsVisible: Boolean by nonNullArgument(KEY_BUTTONS_VISIBLE, true)

    private val isButtonsMultiple: Boolean by nonNullArgument(KEY_MULTIPLE, false)

    private val buttonObjects: Array<SPDialogInfoHolder> by nonNullArgument(BUTTON_OBJECT, arrayOf())


    private var clickAction: ((String) -> Unit)? = null

    override fun getViewBinding(): SpInfoDialogBinding =
            SpInfoDialogBinding.inflate(LayoutInflater.from(context))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleDialogTitles()
        handleVisibility()
        setClicksHandle()
        setButtons()

    }

    /**
     * Sets an action which is triggered when a button of the dialog is clicked
     */
    fun onButtonsClick(action: (String) -> Unit): SPInfoDialog {
        clickAction = action
        return this
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
            tvDialogTitle.visibleOrGone(titleVisible)
        }
    }

    private fun setClicksHandle() {
        binding.lytDialogLinear.lytButtons.setOnClick { label ->
            clickAction?.invoke(label)
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

    private fun convertDialogButtonsType(): SPDialogBottomButtonLayout.SPDialogBottomButton =
            if (isButtonsMultiple) {
                SPDialogBottomButtonLayout.SPDialogBottomButton.SPDialogBottomButtonMultiple(
                        getMultipleButtons()
                )
            } else {
                SPDialogBottomButtonLayout.SPDialogBottomButton.SPDialogBottomButtonTwice(
                        createDialogButtonModel(buttonObjects[LEFT_PAIR_INDEX]),
                        createDialogButtonModel(buttonObjects.get(RIGHT_PAIR_INDEX)),
                )
            }

    private fun getMultipleButtons(): List<SPDialogBottomButtonLayout.SPDialogBottomButtonModel> {
        val buttons = mutableListOf<SPDialogBottomButtonLayout.SPDialogBottomButtonModel>()
        buttonObjects.forEach {
            buttons.add(
                    createDialogButtonModel(it)
            )
        }

        return buttons
    }

    private fun createDialogButtonModel(buttonObj: SPDialogInfoHolder) =
            SPDialogBottomButtonLayout.SPDialogBottomButtonModel(
                    buttonObj.labelTxt,
                    SPDialogBottomVerticalButton.BottomButtonType.valueOf(buttonObj.buttonType.toString()),
                    buttonObj.clickEvent
            )

    override fun setDismissAction() {
        binding.lytRoot.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_LABEL = "KEY_LABEL"
        const val KEY_INFO_ICON_VISIBLE = "KEY_INFO_ICON_VISIBLE"
        const val KEY_TITLE_VISIBLE = "KEY_TITLE_VISIBLE"
        const val KEY_LABEL_VISIBLE = "KEY_LABEL_VISIBLE"
        const val KEY_BUTTONS_VISIBLE = "KEY_BUTTONS_VISIBLE"
        const val KEY_MULTIPLE = "KEY_MULTIPLE"
        const val KEY_BUTTONS = "KEY_BUTTONS"
        const val BUTTON_OBJECT = "BUTTON_OBJECT"

        private const val LEFT_PAIR_INDEX = 0
        private const val RIGHT_PAIR_INDEX = 1

        const val MINIMUM_TWICE_BUTTONS = 2
    }

    /**
     * Builder class which allows to create [SPInfoDialog]
     */
    class SPInfoDialogBuilder(
            activity: FragmentActivity
    ) : SPBaseDialogBuilder<SPInfoDialog>(activity) {

        private var title: String? = null
        private var label: String? = null
        private var titleVisible: Boolean = true
        private var labelVisible: Boolean = true
        private var infoIconVisible: Boolean = true
        private var buttonsVisible: Boolean = true
        private var isMultiple: Boolean = false
        private var buttons: Array<SPDialogInfoHolder> = arrayOf()


        /**
         * Defines data for the top title
         */
        fun setTitle(title: String?): SPInfoDialogBuilder {
            this.title = title
            return this
        }

        /**
         * Defines data for the second title
         */
        fun setLabel(label: String?): SPInfoDialogBuilder {
            this.label = label
            return this
        }

        /**
         * Defines data for the first title visibility
         */
        fun setTitleVisible(visible: Boolean): SPInfoDialogBuilder {
            this.titleVisible = visible
            return this
        }

        /**
         * Defines data for the second title visibility
         */
        fun setLabelVisible(visible: Boolean): SPInfoDialogBuilder {
            this.labelVisible = visible
            return this
        }

        /**
         * Defines data for the dialog icon visibility
         */
        fun setInfoIconVisible(visible: Boolean): SPInfoDialogBuilder {
            this.infoIconVisible = visible
            return this
        }

        /**
         * Defines data for the dialog bottom buttons visibility
         */
        fun setButtonsVisible(visible: Boolean): SPInfoDialogBuilder {
            this.buttonsVisible = visible
            return this
        }

        /**
         * Defines data for the dialog bottom buttons. It applies next params
         *
         * @param multiple applies if the dialog bottom buttons is multiple. By default it's false
         * and it means that the dialog bottom buttons type is twice - for the right button and
         * for the left one.
         * @param pairs applies button models
         * @throws IllegalStateException if the dialog bottom button type is twice and the [pairs]
         * count is less then [MINIMUM_TWICE_BUTTONS] the exception throws because there are no any
         * possibilities to add both right button and left one.
         */
        fun setButtons(
                multiple: Boolean = false,
                buttons: Array<SPDialogInfoHolder>
        ): SPInfoDialogBuilder {
            this.isMultiple = multiple

            if (!isMultiple && buttons.count() < MINIMUM_TWICE_BUTTONS) {
                throw IllegalStateException("Pairs parameter has to contain at least $MINIMUM_TWICE_BUTTONS elements for using Twice button type")
            }

            this.buttons = buttons
            return this
        }

        private fun convertPairToParcelable(
                vararg pairs: Pair<String, SPDialogBottomVerticalButton.BottomButtonType>
        ): Array<Pair<String, String>> =
                pairs.toList().map { Pair(it.first, it.second.toString()) }.toTypedArray()

        /**
         * Builds [SPInfoDialog] by using properties with keys
         */
        override fun build(): SPInfoDialog =
                SPInfoDialog().apply {
                    arguments = bundleOf(
                            KEY_TITLE to this@SPInfoDialogBuilder.title,
                            KEY_LABEL to this@SPInfoDialogBuilder.label,
                            KEY_INFO_ICON_VISIBLE to this@SPInfoDialogBuilder.infoIconVisible,
                            KEY_TITLE_VISIBLE to this@SPInfoDialogBuilder.titleVisible,
                            KEY_LABEL_VISIBLE to this@SPInfoDialogBuilder.labelVisible,
                            KEY_BUTTONS_VISIBLE to this@SPInfoDialogBuilder.buttonsVisible,
                            KEY_MULTIPLE to this@SPInfoDialogBuilder.isMultiple,
                            BUTTON_OBJECT to this@SPInfoDialogBuilder.buttons,
                    )
                }
    }
}