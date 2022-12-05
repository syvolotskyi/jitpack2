package ge.space.ui.components.bottomsheet.core

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBottomsheetLayoutBinding
import ge.space.ui.components.bottomsheet.builder.SPBottomSheetDsl
import ge.space.ui.components.bottomsheet.builder.SPBottomSheetBuilder
import ge.space.ui.components.bottomsheet.strategy.SPBottomSheetStrategy
import ge.space.ui.components.bottomsheet.strategy.SPEmptyStateStrategy
import ge.space.ui.components.buttons.SPButton
import ge.space.ui.components.dialogs.base.SPBaseDialog
import ge.space.ui.util.extension.*


/**
 * [SPBottomSheetFragment] is a custom implementation of [BottomSheetDialogFragment]
 * Bottomsheet always need to has strategy,
 * and currently it support two type of strategy ([SPFragmentSheetStrategy<Data>] and [SPListSheetStrategy<Data>])
 * Data is onResult return type
 */
class SPBottomSheetFragment<Data>(
    private val titleStyle: Int?,
    private val descriptionStyle: Int?,
    private val dialogTitleIcon: Int?,
    private val dialogTitleMessage: String,
    private val dialogButtonMessage: String,
    private val initialState: Int,
    private val dialogDescriptionMessage: String?,
    private var bottomStrategy: SPBottomSheetStrategy<Data> = SPEmptyStateStrategy(),
    private var onResult: (Data?) -> Unit = {},
    private var onBottomClickListenerResult: () -> Unit = {},
    private val dismissDelayTime: Long = 500L
) : BottomSheetDialogFragment() {


    private val binding by lazy {
        SpBottomsheetLayoutBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            titleMessageLabel.text = dialogTitleMessage
            dialogDescriptionMessage?.let {
                descriptionMessageLabel.show()
                descriptionMessageLabel.text = it
                descriptionStyle?.let { style -> binding.descriptionMessageLabel.setTextStyle(style) }
            }

            dialogTitleIcon?.let {
                titleImage.setImageResource(it)
                titleImage.show()
            }

            bottomStrategy.onCreate(childFragmentManager, standardBottomSheet) {
                onResult(it)
                runDelayed(dismissDelayTime) {
                    dismiss()
                }
            }

            handleStartState()
            handleTitleStyle()
            handleBottomButton()
        }
    }

    private fun handleStartState() {
        // set initial state
        getBehavior()?.state = initialState

        // handle bottomSheet behaviour by it's state
        when (initialState) {
            STATE_EXPANDED -> {
                getBehavior()?.skipCollapsed = true
            }
            STATE_COLLAPSED -> {
                binding.standardBottomSheet.viewTreeObserver.addOnGlobalLayoutListener {
                    val displayMetrics = DisplayMetrics()
                    requireActivity().windowManager?.defaultDisplay?.getMetrics(displayMetrics)
                    if (binding.standardBottomSheet.measuredHeight > displayMetrics.heightPixels - getTitleHeight()) {
                        getBehavior()?.skipCollapsed = true
                        getBehavior()?.state = STATE_EXPANDED
                    }
                }
            }
        }
    }

    /**
     * Returns a sum of status bar heights and title
     */
    private fun getTitleHeight() =
        getStatusBarHeight(requireActivity()) + resources.getDimensionPixelSize(R.dimen.dimen_p_24)


    /**
     * Sets a bottom button click listener
     *
     * @param listener [() -> Unit] calls when button is clicked
     */
    fun setButtonClickListener(listener: () -> Unit) {
        onBottomClickListenerResult = listener
    }

    /**
     * Sets a Result listener
     *
     * @param listener [Data] calls when bottom sheet is dismissed
     */
    fun setResultListener(onResult: (Data?) -> Unit) {
        this.onResult = onResult
    }

    /**
     * Show popup dialog
     */
    fun show(fragmentActivity: FragmentActivity, tag: String = SPBaseDialog.DIALOG_FRAGMENT_TAG) {
        try {
            if (fragmentActivity.supportFragmentManager.findFragmentByTag(tag) == null) {
                show(fragmentActivity.supportFragmentManager, tag)
            }
        } catch (ignored: IllegalStateException) {
            ignored.printStackTrace()
        }
    }

    private fun handleTitleStyle() {
        titleStyle?.let { binding.titleMessageLabel.setTextStyle(it) }
    }

    private fun handleBottomButton() {
        if (dialogButtonMessage.isNotEmpty())
            with(binding.bottomButtonStub.inflate() as SPButton) {
                onClick {
                    onBottomClickListenerResult()
                    dismiss()
                }
                this.text = dialogButtonMessage
            }
    }

    private fun getBehavior(): BottomSheetBehavior<*>? =
        (dialog as? BottomSheetDialog)?.behavior

    internal constructor(builder: SPBottomSheetBuilder<Data>) : this(
        builder.titleStyle,
        builder.descriptionStyle,
        builder.icon,
        builder.title ?: EMPTY_TEXT,
        builder.buttonText,
        builder.initialState,
        builder.description,
        builder.strategy,
        builder.resultListener,
        builder.buttonClickListener,
        builder.dismissDelayTime
    )

    companion object {
        inline fun <reified Data> bottomSheet(block: @SPBottomSheetDsl SPBottomSheetBuilder<Data>.() -> Unit) =
            SPBottomSheetBuilder<Data>().apply(block).build()
    }
}