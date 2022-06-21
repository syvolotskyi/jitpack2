package ge.space.ui.components.dropdowns.builder

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import ge.space.spaceui.R
import ge.space.ui.components.dialogs.base.SPBaseDialogBuilder
import ge.space.ui.components.dropdowns.SpBottomSheetFragment
import ge.space.ui.components.dropdowns.strategy.SpBottomSheetStrategy

class SPBottomSheetBuilder(activity: FragmentActivity) :
    SPBaseDialogBuilder<SpBottomSheetFragment>(activity) {

    private var title: String? = null
    private var titleStyle: Int? = null
    private var icon: Int? = null
    private var description: String? = null
    private var descriptionStyle: Int? = null
    private var strategy: SpBottomSheetStrategy? = null

    fun setIcon(icon: Int?): SPBottomSheetBuilder {
        this.icon = icon

        return this
    }

    fun setStrategy(strategy: SpBottomSheetStrategy?): SPBottomSheetBuilder {
        this.strategy = strategy

        return this
    }

    fun setTitle(
        message: String,
        style: Int = R.style.h700_bold_caps_primary
    ): SPBottomSheetBuilder {
        title = message
        titleStyle = style
        return this
    }

    fun setDialogDesc(
        message: String,
        style: Int = R.style.h800_medium_label_secondary
    ): SPBottomSheetBuilder {
        description = message
        descriptionStyle = style
        return this
    }

    override fun build(): SpBottomSheetFragment =
        SpBottomSheetFragment().apply {
            arguments = bundleOf(
                SpBottomSheetFragment.KEY_TITLE to title,
                SpBottomSheetFragment.KEY_TITLE_STYLE to titleStyle,
                SpBottomSheetFragment.KEY_ICON to icon,
                SpBottomSheetFragment.KEY_DESCRIPTION to description,
                SpBottomSheetFragment.KEY_DESCRIPTION_STYLE to descriptionStyle
            )
           bottomStrategy =  strategy
    }
}