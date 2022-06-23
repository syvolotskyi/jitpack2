package ge.space.ui.components.dropdowns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpBottomsheetBinding
import ge.space.ui.components.dropdowns.strategy.SPBottomSheetStrategy
import ge.space.ui.util.extension.*

class SPBottomSheetFragment : BottomSheetDialogFragment() {

    private val titleStyle: Int? by argument(KEY_TITLE_STYLE, null)
    private val descriptionStyle: Int? by argument(KEY_DESCRIPTION_STYLE, null)
    private val dialogTitleIcon: Int? by argument(KEY_ICON, null)
    private val dialogTitleMessage: String by nonNullArgument(KEY_TITLE, EMPTY_TEXT)
    private val dialogDescriptionMessage: String? by argument(KEY_DESCRIPTION, null)
    private var bottomStrategy: SPBottomSheetStrategy? = null


    private val binding by lazy {
        SpBottomsheetBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleMessageLabel.text = dialogTitleMessage
        titleStyle?.let { binding.titleMessageLabel.setTextStyle(it) }


        dialogDescriptionMessage?.let {
            binding.descriptionMessageLabel.show()
            binding.descriptionMessageLabel.text = it
            descriptionStyle?.let { binding.descriptionMessageLabel.setTextStyle(it) }
        }

        bottomStrategy?.let { setBottomStrategy(it) }
    }

    fun setBottomStrategy(value: SPBottomSheetStrategy) {
        bottomStrategy = value

        bottomStrategy?.onCreate(binding.standardBottomSheet) { dismiss() }
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