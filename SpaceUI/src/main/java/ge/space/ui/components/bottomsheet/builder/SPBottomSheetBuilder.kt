package ge.space.ui.components.bottomsheet.builder

import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import ge.space.spaceui.R
import ge.space.ui.components.dialogs.base.SPBaseDialogBuilder
import ge.space.ui.components.bottomsheet.core.SPBottomSheetFragment
import ge.space.ui.components.bottomsheet.strategy.SPBottomSheetStrategy
import ge.space.ui.components.bottomsheet.strategy.SPEmptyStateStrategy
import ge.space.ui.components.bottomsheet.strategy.SPListSheetStrategy
import ge.space.ui.util.extension.EMPTY_TEXT

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class BottomSheetDsl

/**
 * Builder class which allows to create [SPBottomSheetFragment]. Data is onResult return type
 */
@BottomSheetDsl
class SPBottomSheetBuilder<Data> : SPBaseDialogBuilder<SPBottomSheetFragment<Data>>() {

    internal var title: String? = null
    internal var titleStyle: Int? = null
    internal var icon: Int? = null
    internal var description: String? = null
    internal var initialState: Int = STATE_COLLAPSED
    internal var descriptionStyle: Int? = null
    internal var resultListener: (Data?) -> Unit = {}
    internal var buttonText: String = EMPTY_TEXT
    internal var buttonClickListener: () -> Unit = {}
    internal var dismissDelayTime: Long = 500L
    internal var strategy: SPBottomSheetStrategy<Data> = SPEmptyStateStrategy()

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
    fun setStrategy(strategy: SPBottomSheetStrategy<Data>): SPBottomSheetBuilder<Data> {
        this.strategy = strategy

        return this
    }

    /**
     * Sets a dismiss delay time
     *
     * @param millis [Long] millis to delay a bottom sheet
     */
    fun setDismissDelayTime(millis: Long): SPBottomSheetBuilder<Data> {
        this.dismissDelayTime = millis

        return this
    }

    /**
     * Sets Initial bottomSheet state
     *
     * @param initialState [Int] sets start state int to bottom sheet
     */
    fun setInitialState(initialState: Int): SPBottomSheetBuilder<Data> {
        this.initialState = initialState

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
     * Sets a bottom button visibility
     *
     * @param text [String] is text of the button
     * @param listener [() -> Unit] calls when button is clicked
     */
    fun setBottomButton(text: String, listener: () -> Unit): SPBottomSheetBuilder<Data> {
        this.buttonText = text
        this.buttonClickListener = listener

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
    fun setDescription(
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
        SPBottomSheetFragment(this)
}
