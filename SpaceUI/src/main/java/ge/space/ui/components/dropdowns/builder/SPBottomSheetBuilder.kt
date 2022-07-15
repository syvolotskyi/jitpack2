package ge.space.ui.components.dropdowns.builder

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import ge.space.spaceui.R
import ge.space.ui.components.dialogs.base.SPBaseDialogBuilder
import ge.space.ui.components.dropdowns.core.SPBottomSheetFragment
import ge.space.ui.components.dropdowns.strategy.SPBottomSheetStrategy

/**
 * Builder class which allows to create [SPBottomSheetFragment]. Data is onResult return type
 */
class SPBottomSheetBuilder<Data>(activity: FragmentActivity) :
    SPBaseDialogBuilder<SPBottomSheetFragment<Data>>(activity) {

    private var title: String? = null
    private var titleStyle: Int? = null
    private var icon: Int? = null
    private var description: String? = null
    private var descriptionStyle: Int? = null
    private var strategy: SPBottomSheetStrategy<Data>? = null
    private var resultListener : (Data?) -> Unit = {}

    /**
     * Setting an icon
     *
     * @param icon applies a resInt icon to title text view
     */
    fun setIcon(icon: Int?): SPBottomSheetBuilder<Data> {
        this.icon = icon

        return this
    }

    /**
     * Sets a bottom sheet strategy
     *
     * @param strategy [SPBottomSheetStrategy] applies strategy
     */
    fun setStrategy(strategy: SPBottomSheetStrategy<Data>?): SPBottomSheetBuilder<Data> {
        this.strategy = strategy

        return this
    }

    /**
     * Sets a Result listener
     *
     * @param listener [Data] calls when bottom sheet is dismissed
     */
    fun setResultListener(listener: (Data?) -> Unit): SPBottomSheetBuilder<Data> {
        this.resultListener = listener

        return this
    }

    /**
     * BottomSheet initializing by passing text and style
     *
     * @param message adding a text message
     * @param style applies a text style for title TextView
     */
    fun setTitle(
        message: String,
        style: Int = R.style.h700_bold_caps_primary
    ): SPBottomSheetBuilder<Data> {
        title = message
        titleStyle = style
        return this
    }

    /**
     * Adding description by passing text and style
     *
     * @param message adding a text message
     * @param style applies a text style for title TextView
     */
    fun setDialogDesc(
        message: String,
        style: Int = R.style.h800_medium_label_secondary
    ): SPBottomSheetBuilder<Data> {
        description = message
        descriptionStyle = style
        return this
    }

    /**
     * Builds [SPBottomSheetFragment] by using properties with keys
     */
    override fun build(): SPBottomSheetFragment<Data> =
        SPBottomSheetFragment<Data>().apply {
            arguments = bundleOf(
                SPBottomSheetFragment.KEY_TITLE to title,
                SPBottomSheetFragment.KEY_TITLE_STYLE to titleStyle,
                SPBottomSheetFragment.KEY_ICON to icon,
                SPBottomSheetFragment.KEY_DESCRIPTION to description,
                SPBottomSheetFragment.KEY_DESCRIPTION_STYLE to descriptionStyle
            )
            strategy?.let { setBottomStrategy(it) }
            this.setResultListener(resultListener)
        }
}