package ge.space.ui.components.dropdowns.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBottomsheetLayoutBinding
import ge.space.ui.components.dropdowns.strategy.SPBottomSheetStrategy
import ge.space.ui.util.extension.*

/**
 * [SPBottomSheetFragment] is a custom implementation of [BottomSheetDialogFragment]
 * Sets a strategy [setBottomStrategy] for adding content in bottom sheet container
 * Data is onResult return type
 */
class SPBottomSheetFragment<Data> : BottomSheetDialogFragment() {

    private val titleStyle: Int? by argument(KEY_TITLE_STYLE, null)
    private val descriptionStyle: Int? by argument(KEY_DESCRIPTION_STYLE, null)
    private val dialogTitleIcon: Int? by argument(KEY_ICON, null)
    private val dialogTitleMessage: String by nonNullArgument(KEY_TITLE, EMPTY_TEXT)
    private val dialogDescriptionMessage: String? by argument(KEY_DESCRIPTION, null)
    private lateinit var bottomStrategy: SPBottomSheetStrategy<Data>
    private var onResult: (Data?) -> Unit = {}

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
                descriptionStyle?.let { binding.descriptionMessageLabel.setTextStyle(it) }
            }

            dialogTitleIcon?.let {
                titleImage.setImageResource(it)
                titleImage.show()
            }

            bottomStrategy.onCreate(childFragmentManager, standardBottomSheet) {
                runDelayed(300, action = {
                    onResult(it)
                    dismiss()
                })
            }
        }
        handleTitleStyle()
    }

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

    private fun handleTitleStyle() {
        titleStyle?.let { binding.titleMessageLabel.setTextStyle(it) }
    }

    companion object {
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        const val KEY_DESCRIPTION_STYLE = "KEY_DESCRIPTION_STYLE"
        const val KEY_ICON = "KEY_ICON"
        const val KEY_TITLE_STYLE = "KEY_TITLE_STYLE"


        const val DIALOG_FRAGMENT_TAG: String = "DIALOG_FRAGMENT_TAG"

    }
}