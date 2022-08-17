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
import ge.space.ui.components.bottomsheet.strategy.SPBottomSheetStrategy
import ge.space.ui.components.dialogs.base.SPBaseDialog
import ge.space.ui.util.extension.*


/**
 * [SPBottomSheetFragment] is a custom implementation of [BottomSheetDialogFragment]
 * Sets a strategy [setBottomStrategy] Bottomsheet always need to has strategy,
 * and currently it support two type of strategy ([SPFragmentSheetStrategy<Data>] and [SPListSheetStrategy<Data>])
 * Data is onResult return type
 */
class SPBottomSheetFragment<Data> : BottomSheetDialogFragment() {

    private val titleStyle: Int? by argument(KEY_TITLE_STYLE, null)
    private val descriptionStyle: Int? by argument(KEY_DESCRIPTION_STYLE, null)
    private val dialogTitleIcon: Int? by argument(KEY_ICON, null)
    private val dialogTitleMessage: String by nonNullArgument(KEY_TITLE, EMPTY_TEXT)
    private val initialState: Int by nonNullArgument(KEY_INITIAL_STATE, STATE_COLLAPSED)
    private val dialogDescriptionMessage: String? by argument(KEY_DESCRIPTION, null)
    private lateinit var bottomStrategy: SPBottomSheetStrategy<Data>
    private var onResult: (Data?) -> Unit = {}
    private val dismissDelayTime: Long by nonNullArgument(KEY_DELAY_TIME, 500L)

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
     * Sets a bottom sheet strategy
     *
     * @param value [SPBottomSheetStrategy] applies strategy
     */
    fun setBottomStrategy(value: SPBottomSheetStrategy<Data>) {
        bottomStrategy = value
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

    private fun getBehavior(): BottomSheetBehavior<*>? =
        (dialog as? BottomSheetDialog)?.behavior


    companion object {
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_INITIAL_STATE = "KEY_INITIAL_STATE"
        const val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        const val KEY_DESCRIPTION_STYLE = "KEY_DESCRIPTION_STYLE"
        const val KEY_DELAY_TIME = "KEY_DELAY_TIME"
        const val KEY_ICON = "KEY_ICON"
        const val KEY_TITLE_STYLE = "KEY_TITLE_STYLE"
    }
}