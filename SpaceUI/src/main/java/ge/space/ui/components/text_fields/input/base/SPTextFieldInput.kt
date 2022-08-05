package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.content.res.TypedArray
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.space.formatter.extensions.addFormattingTextWatcher
import com.space.formatter.format.SPDefaultFormatterFactory
import com.space.formatter.format.StringFormatter
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPDistractiveMode
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.text_fields.input.utils.extension.setTextLength
import ge.space.ui.components.text_fields.input.utils.masked_helper.SPEditTextMasked
import ge.space.ui.util.extension.*
import java.lang.NullPointerException

/**
 * Field view extended from [LinearLayout] that allows to change its configuration.
 *
 * @property text [String] value which sets a text.
 * @property labelText [String] value which sets a label text.
 * @property imeOption [Int] value which sets a ime Option.
 * @property inputMandatory [Boolean] value which sets a input mandatory.
 * @property descriptionText [String] value which sets a description text.
 */
open class SPTextFieldInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextField_Base
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling, SPDistractiveMode {

    /**
     * [emptyStartView] is an empty start View
     */
    private val emptyStartView = FrameLayout(context).apply {
        setPadding(resources.getDimensionPixelSize(R.dimen.dimen_p_8))
    }

    /**
     * [emptyEndView] is an empty end View
     */
    private val emptyEndView = FrameLayout(context).apply {
        setPadding(resources.getDimensionPixelSize(R.dimen.dimen_p_8))
    }

    /**
     * Sets a button title.
     */
    open var text: String = EMPTY_TEXT
        get() = contentInputView.text.toString()
        set(value) {
            field = value

            contentInputView.setText(value)
        }


    /**
     * Sets a button hint.
     */
    open var hint: String = EMPTY_TEXT
        set(value) {
            field = value

            contentInputView.hint = value
        }

    /**
     * Sets a text mask.
     */
    var mask: String = EMPTY_TEXT

    /**
     * Sets a label text.
     */
    var labelText: String = EMPTY_TEXT
        set(value) {
            field = value

            handleShowingLabelText()
        }

    /**
     * Sets a text max input length.
     */
    open var maxLength: Int = 0
        set(value) {
            field = value

            setTextLength(value)
        }

    /**
     * Sets a input mandatory red star at the and of label text
     */
    var inputMandatory = false
        set(value) {
            field = value

            handleShowingLabelText()
        }

    /**
     * Sets a visibility for info button
     */
    var showInfoButton = false
        set(value) {
            field = value

            binding.infoImage.isVisible = field
        }

    /**
     * Sets a imeOption.
     */
    var imeOption: Int = 0
        set(value) {
            field = value

            handleImeOption()
        }

    /**
     * Sets a text appearance.
     */
    @StyleRes
    var textAppearance: Int = 0
        set(value) {
            field = value

            updateTextAppearance(value)
        }

    /**
     * Sets a description text.
     */
    var descriptionText: String = EMPTY_TEXT
        set(value) {
            field = value

            handleShowingDescriptionText()
        }

    /**
     * Sets a focus listener text.
     */
    var onFocusChangeListener: (Boolean) -> Unit = { }

    /**
     * [startView] is a start view of [SPTextFieldInput]
     */
    var startView: View? = emptyStartView
        set(value) {
            field = value

            binding.flStart.addContentView(startView, emptyStartView)
        }

    /**
     * [endView] is a end view of [SPTextFieldInput]
     */
    var endView: View? = emptyEndView
        set(value) {
            field = value

            binding.flEndView.addContentView(endView, emptyEndView)
        }

    /**
     * [contentInputView] is a content(input field) view
     */
    open var contentInputView: EditText = EditText(context)
        set(value) {
            field = value

            handleContentInputView()
        }

    /**
     * [isDistractive] changes border color as a warning state
     */
    override var isDistractive: Boolean = false
        set(value) {
            field = value

            handleDistractiveState()
        }

    /**
     * Input field input type
     */
    var inputType: Int = SPTextInputViewType.TEXT

    /**
     * Input field watcher
     */
    private var watcher: TextWatcher? = null

    /**
     * Inflates and returns [SpTextFieldLayoutBinding] value
     */
    protected open val binding by lazy {
        SpTextFieldLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextFieldInput,
            defStyleAttr,
            defStyleRes
        ) {

            applyAttributes()
        }
    }

    /**
     * Sets a style for the view.
     *
     * <p>
     * Default style theme is SBBaseView style. A style has to implement SPView styleable
     * attributes. Separate SPBaseView styleable attributes have higher priority han styles.
     * <p>
     *
     * @param defStyleRes [Int] style resource id
     */
    protected fun setStyle(@StyleRes defStyleRes: Int) {
        with(
            context.theme.obtainStyledAttributes(
                defStyleRes,
                R.styleable.SPTextFieldInput
            )
        ) {
            applyAttributes()
            recycle()
        }
    }

    /**
     * Applying main [SPTextFieldInput] attributes
     */
    private fun TypedArray.applyAttributes() {
        getString(R.styleable.SPTextFieldInput_titleText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                labelText = it
            }
        imeOption = getInt(R.styleable.SPTextFieldInput_android_imeOptions, ID_NEXT)
        inputMandatory = getBoolean(R.styleable.SPTextFieldInput_inputMandatory, false)

        getResourceId(
            R.styleable.SPTextFieldInput_textAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            textAppearance = it
        }

        getInt(R.styleable.SPTextFieldInput_startView, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(
                SPBaseView.NO_OBTAIN_VAL
            ) {
                handleStartView(it)
            }

        getInt(R.styleable.SPTextFieldInput_endView, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(
                SPBaseView.NO_OBTAIN_VAL
            ) {
                handleEndView(it)
            }

        getInt(R.styleable.SPTextFieldInput_contentInputView, SPBaseView.NO_OBTAIN_VAL)
            .handleAttributeAction(
                SPBaseView.NO_OBTAIN_VAL
            ) {
                handleContentType(it)
            }
        getInt(
            R.styleable.SPTextFieldInput_inputTextLength,
            DEFAULT_TEXT_LENGTH
        ).handleAttributeAction(
            DEFAULT_TEXT_LENGTH
        ) {
            maxLength = it
        }

        getInt(R.styleable.SPTextFieldInput_contentInputView, SPBaseView.NO_OBTAIN_VAL)
            .handleAttributeAction(
                SPBaseView.NO_OBTAIN_VAL
            ) {
                inputType = it
                handleContentType(it)
            }

        getString(R.styleable.SPTextFieldInput_android_hint).orEmpty()
            .handleAttributeAction(
                EMPTY_TEXT
            ) {
                hint = it
            }

        getString(R.styleable.SPTextFieldInput_android_text).orEmpty()
            .handleAttributeAction(
                EMPTY_TEXT
            ) {
                text = it
            }

        getString(R.styleable.SPTextFieldInput_descriptionText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                descriptionText = it
            }

        getResourceId(
            R.styleable.SPTextFieldInput_descriptionTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            updateDescriptionTextAppearance(it)
        }

        getResourceId(
            R.styleable.SPTextFieldInput_contentHeight,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            binding.flContainer.setHeight(resources.getDimensionPixelSize(it))
        }

        getResourceId(
            R.styleable.SPTextFieldInput_labelTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            updateLabelTextAppearance(it)
        }

        changeBorderColor()
    }

    /**
     * [handleContentType] handles input field type
     * @param contentType is a type of input
     */
    private fun handleContentType(contentType: Int) {
        when (contentType) {
            SPTextInputViewType.DATE_MASKED -> {
                mask = resources.getString(R.string.day_mask)
                setupContentInputViewByType(SPTextInputViewType.SPMaskViewType(mask, hint))
            }
            SPTextInputViewType.CARD_MASKED -> {
                mask = resources.getString(R.string.card_mask)
                setupContentInputViewByType(SPTextInputViewType.SPMaskViewType(mask, hint))
            }
            SPTextInputViewType.TEXT -> setupContentInputViewByType(getContentEditText())
            SPTextInputViewType.NUMBER -> setupContentInputViewByType(
                SPTextInputViewType.SPNumberViewType(hint)
            )
            SPTextInputViewType.EMAIL -> setupContentInputViewByType(
                SPTextInputViewType.SPEditTextViewType(
                    hint,
                    inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                )
            )
            SPTextInputViewType.AMOUNT_INTEGER -> setupContentInputViewByType(
                SPTextInputViewType.SPNumberViewType(hint)
            )
            SPTextInputViewType.AMOUNT_DECIMAL -> setupContentInputViewByType(
                SPTextInputViewType.SPNumberViewType(
                    hint,
                    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                )
            )
        }
    }

    /**
     * [handleStartView] handles start view
     */
    private fun TypedArray.handleStartView(startViewType: Int) {
        when (startViewType) {
            SPStartViewType.PHONE_PREFIX -> {
                if (getPhonePrefixFromAttr() != EMPTY_TEXT) {
                    SPStartViewType.SPPhonePrefixViewType(getPhonePrefixFromAttr())
                } else
                    SPStartViewType.SPNoneViewType
            }
            SPStartViewType.IMAGE -> {
                if (getStartIconFromAttr() != SPBaseView.DEFAULT_OBTAIN_VAL)
                    SPStartViewType.SPImageViewType(getStartIconFromAttr())
                else
                    SPStartViewType.SPImageViewType()
            }

            SPStartViewType.CARD -> SPStartViewType.SPCardViewType
            else -> SPStartViewType.SPNoneViewType
        }.also { startView ->
            setupStartViewByType(startView)
        }
    }

    /**
     * [handleEndView] handles end view
     */
    private fun TypedArray.handleEndView(endViewType: Int) {
        when (endViewType) {
            SPEndViewType.CURRENCY -> if (getCurrencyFromAttr() != EMPTY_TEXT) {
                SPEndViewType.SPCurrencyViewType(getCurrencyFromAttr())
            } else {
                SPEndViewType.SPNoneViewType
            }
            SPEndViewType.CARD -> SPEndViewType.SPCardViewType
            SPEndViewType.IMAGE -> {
                if (getEndIconFromAttr() != SPBaseView.DEFAULT_OBTAIN_VAL)
                    SPEndViewType.SPImageViewType(getEndIconFromAttr())
                else
                    SPEndViewType.SPImageViewType()
            }
            SPEndViewType.REMOVABLE -> SPEndViewType.SPRemovableViewType
            else -> SPEndViewType.SPNoneViewType
        }.also { endView ->
            setupEndViewByType(endView)
        }
    }

    /**
     * [getContentEditText] returns content of default editText
     */
    protected open fun getContentEditText() =
        SPTextInputViewType.SPEditTextViewType(lines = DEFAULT_TEXT_LINE_LENGTH)

    /**
     * [getPhonePrefixFromAttr] returns phones prefix from attr
     */
    private fun TypedArray.getPhonePrefixFromAttr() =
        (getString(R.styleable.SPTextFieldInput_startViewText)
            ?: context.getString(R.string.default_phone_prefix))

    /**
     * [getCurrencyFromAttr] returns currency from attr
     */
    private fun TypedArray.getCurrencyFromAttr() =
        (getString(R.styleable.SPTextFieldInput_endViewText)
            ?: context.getString(R.string.default_currency))

    /**
     * [getEndIconFromAttr] returns end icon from attr
     */
    private fun TypedArray.getEndIconFromAttr() =
        (getResourceId(R.styleable.SPTextFieldInput_endViewIcon, SPBaseView.DEFAULT_OBTAIN_VAL))

    /**
     * [getEndIconFromAttr] returns start icon from attr
     */
    private fun TypedArray.getStartIconFromAttr() =
        (getResourceId(R.styleable.SPTextFieldInput_startViewIcon, SPBaseView.DEFAULT_OBTAIN_VAL))

    /**
     * Sets view style
     */
    override fun setViewStyle(@StyleRes newStyle: Int) {
        setStyle(newStyle)
    }

    /**
     * Enable or disable [SPTextFieldInput]
     */
    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        contentInputView.isEnabled = enabled
        endView?.isEnabled = enabled
        startView?.isEnabled = enabled
        changeBorderColor()
    }

    /**
     * Sets a end click listener.
     */
    fun setEndViewClickListener(onClickListener: () -> Unit? = {}) {
        endView?.onClick { onClickListener() }
    }

    /**
     * Sets a info click listener.
     */
    fun setInfoListener(onClickListener: () -> Unit? = {}) {
        showInfoButton = true
        binding.infoImage.onClick { onClickListener() }
    }

    /**
     * Allows to update a text appearance by styles
     */
    protected fun updateTextAppearance(textAppearance: Int) {
        contentInputView.setTextStyle(textAppearance)
    }

    /**
     * [updateLabelTextAppearance] updates label text ppearance
     */
    private fun updateLabelTextAppearance(textAppearance: Int) =
        binding.textLabel.setTextStyle(textAppearance)

    /**
     * [updateLabelTextAppearance] updates descriptions text ppearance
     */
    private fun updateDescriptionTextAppearance(textAppearance: Int) =
        binding.textDesc.setTextStyle(textAppearance)

    /**
     * [handleShowingLabelText] show label text if it is not empty
     */
    private fun handleShowingLabelText() {
        binding.textLabel.isVisible = labelText.isNotEmpty()
        if (inputMandatory) {
            binding.textLabel.setText(labelText.appendAsterisk(), TextView.BufferType.SPANNABLE)
        } else {
            binding.textLabel.text = labelText
        }
    }

    /**
     * [handleShowingDescriptionText] show description text if it is not empty
     */
    private fun handleShowingDescriptionText() {
        binding.textDesc.isVisible = descriptionText.isNotEmpty()
        binding.textDesc.text = descriptionText
    }

    /**
     * [handleDistractiveState] changes state to distractive
     */
    override fun handleDistractiveState() = changeBorderColor()

    /**
     * Sets editor action listener
     */
    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        contentInputView.setOnEditorActionListener(listener)
    }

    /**
     * Sets ime option
     */
    private fun handleImeOption() {
        contentInputView.imeOptions = imeOption
    }

    /**
     * Adds text change listener to edit text
     */
    fun addTextChangedListener(watcher: TextWatcher) {
        contentInputView.addTextChangedListener(watcher)
    }

    /**
     * Removes text change listener
     */
    fun removeTextChangedListener(watcher: TextWatcher) {
        contentInputView.addTextChangedListener(watcher)
    }

    /**
     * Possibility to add own formatter
     */
    fun setFormatter(inputAmountFormatter: StringFormatter) {
        contentInputView.apply {
            removeTextChangedListener(watcher)
            addFormattingTextWatcher(inputAmountFormatter)
        }
    }

    /**
     * [focus] functions focuses on edit text [contentInputView]
     */
    fun focus() = contentInputView.requestFocus()

    /**
     * [removeAllText] clears text of the input field
     */
    fun removeAllText() {
        when (contentInputView) {
            is SPEditTextMasked -> setupContentInputViewByType(
                SPTextInputViewType.SPMaskViewType(
                    mask,
                    hint
                )
            )
            else -> contentInputView.setText(EMPTY_TEXT)
        }
    }

    /**
     * [addContentView] Through this function
     * We add: [startView], [endView] or [contentInputView]
     */
    private fun ViewGroup.addContentView(
        view: View?,
        defaultView: View? = null
    ) {
        removeAllViews().also {
            when {
                view != null -> addView(view)
                defaultView != null -> addView(defaultView)
                else -> throw NullPointerException("View and default view are null")
            }
            invalidate()
        }
    }

    /**
     * [handleContentInputView] is invoked after [contentInputView] set
     * * function executes [addContentView]
     * * function set up [setupFocusChangeListener]
     * * function adds formatter if it is necessary [addNumberFormatter]
     */
    protected open fun handleContentInputView() {
        binding.flInputFieldContainer.addContentView(contentInputView)
        setupFocusChangeListener()
        addNumberFormatter()
    }

    /**
     * Adds number formatter if [inputType] equals to AMOUNT_INTEGER or AMOUNT_DECIMAL
     */
    private fun addNumberFormatter() {
        if (inputType == SPTextInputViewType.AMOUNT_INTEGER || inputType == SPTextInputViewType.AMOUNT_DECIMAL) {
            setFormatter(
                if (maxLength > 0)
                    SPDefaultFormatterFactory.produceInputAmountFormatter(maxLength)
                else
                    SPDefaultFormatterFactory.produceInputAmountFormatter()
            )
            // reset length filter because the filter is already added in formatter
            contentInputView.filters = arrayOf<InputFilter>()
        }
    }

    /**
     * [setupFocusChangeListener] sets on focus changes listener
     */
    protected fun setupFocusChangeListener() {
        contentInputView.setOnFocusChangeListener { _, focused ->
            changeBorderColor()
            onFocusChangeListener(focused)
        }
    }

    /**
     * [setTextLength] sets max length of [contentInputView]
     */
    protected open fun setTextLength(value: Int) =
        contentInputView.setTextLength(value)

    /**
     * [changeBorderColor] updates border colors
     */
    private fun changeBorderColor() {
        binding.flContainer.post {
            binding.flContainer.changeBorder(
                when {
                    !isEnabled ->
                        context.getColorFromAttribute(R.attr.separator_opaque)
                    isDistractive ->
                        context.getColorFromAttribute(R.attr.status_primary_distractive)
                    contentInputView.isFocused ->
                        context.getColorFromAttribute(R.attr.brand_primary)
                    else ->
                        context.getColorFromAttribute(R.attr.separator_opaque)
                }, borderWidth
            )
        }
    }

    /**
     * [borderWidth] is a border width of [SPTextFieldInput]
     */
    private var borderWidth: Float =
        context.resources.getDimensionPixelSize(R.dimen.dimen_p_0_5).toFloat()

    companion object {
        private const val ID_NEXT = 5
        private const val DEFAULT_TEXT_LENGTH = -1 //no borders
        private const val DEFAULT_TEXT_LINE_LENGTH = 100
    }
}