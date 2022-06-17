package ge.space.ui.components.dropdowns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ge.space.spaceui.R
import ge.space.ui.components.dropdowns.strategy.SpBottomSheetStrategy
import ge.space.ui.util.extension.argument
import ge.space.ui.util.extension.setTextStyle
import ge.space.ui.util.extension.show

class SpBottomSheetFragment : BottomSheetDialogFragment() {

    val dismissOnItemClick: Boolean? by argument(KEY_DISMISS_ON_ITEM, false)
    val titleStyle: Int? by argument(KEY_TITLE_STYLE, null)
    val descriptionStyle: Int? by argument(KEY_DESCRIPTION_STYLE, null)
    val dialogTitleIcon: Int? by argument(KEY_ICON, null)
    val dialogTitleMessage: String? by argument(KEY_TITLE, null)
    val dialogDescriptionMessage: String? by argument(KEY_DESCRIPTION, null)
    var bottomStrategy: SpBottomSheetStrategy? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.sp_bottomsheet, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textViewMessage = view.findViewById<TextView>(R.id.titleMessageLabel)
        val descViewMessage = view.findViewById<TextView>(R.id.descriptionMessageLabel)
        val linearLayout = view.findViewById<LinearLayout>(R.id.standard_bottom_sheet)

        dialogTitleMessage?.let {
            textViewMessage.show()
            textViewMessage.text = it
            titleStyle?.let { textViewMessage.setTextStyle(it) }
        }

        dialogDescriptionMessage?.let {
            descViewMessage.show()
            descViewMessage.text = it
            descriptionStyle?.let { descViewMessage.setTextStyle(it) }
        }

        bottomStrategy?.onAddCreate(linearLayout)

    }

    companion object {
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        const val KEY_DESCRIPTION_STYLE = "KEY_DESCRIPTION_STYLE"
        const val KEY_ICON = "KEY_ICON"
        const val KEY_TITLE_STYLE = "KEY_TITLE_STYLE"
        const val KEY_DISMISS_ON_ITEM = "KEY_DISMISS_ON_ITEM"
        const val KEY_STRATEGY = "KEY_STRATEGY"


        const val DIALOG_FRAGMENT_TAG: String = "DIALOG_FRAGMENT_TAG"

    }
}