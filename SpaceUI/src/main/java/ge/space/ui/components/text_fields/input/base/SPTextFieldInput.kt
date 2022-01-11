package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.content.res.TypedArray
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.appendAsterisk
import ge.space.extensions.onClick
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPDistractiveMode
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.text_fields.input.utils.extension.setTextLength
import ge.space.ui.components.text_fields.input.utils.masked_helper.SPEditTextMasked
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.handleAttributeAction

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
) : LinearLayout(context, attrs, defStyleAttr), SPViewStyling, SPDistractiveMode {

    private var borderWidth: Float =
        context.resources.getDimensionPixelSize(R.dimen.dimen_p_0_5).toFloat()

    private val emptyStartView = FrameLayout(context).apply {
        setPadding(resources.getDimensionPixelSize(R.dimen.dimen_p_8))
    }
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
     * Sets a input mandatory red star at the and of label text
     */
    var inputMandatory = false
        set(value) {
            field = value

            handleShowingLabelText()
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
     * Sets a start view.
     */
    var startView: View? = emptyStartView
        set(value) {
            field = value

            binding.flLeading.addContentView(startView, emptyStartView)
        }

    /**
     * Sets a end view.
     */
    var endView: View? = emptyEndView
        set(value) {
            field = value

            binding.flTrail.addContentView(endView, emptyEndView)
        }

    open var contentInputView: EditText = EditText(context)
        set(value) {
            field = value

            handleContentInputView()
        }


    override var isDistractive: Boolean = false
        set(value) {
            field = value

            handleDistractiveState()
        }

    /**
     * Inflates and returns [SpTextFieldLayoutBinding] value
     */
    protected open val binding by lazy {
        SpTextFieldLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextFieldBaseView,
            defStyleAttr
        ) {
            setViewStyle(
                getResourceId(
                    R.styleable.SPTextFieldBaseView_style,
                    defStyleRes
                )
            )
            applyAttributes()

            startView = emptyStartView
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
                R.styleable.SPTextFieldBaseView
            )
        ) {
            applyAttributes()
            recycle()
        }
    }

    private fun TypedArray.applyAttributes() {
        getString(R.styleable.SPTextFieldBaseView_titleText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                labelText = it
            }
        imeOption = getInt(R.styleable.SPTextFieldBaseView_android_imeOptions, ID_NEXT)
        inputMandatory = getBoolean(R.styleable.SPTextFieldBaseView_inputMandatory, false)

        getInt(
            R.styleable.SPTextFieldBaseView_inputTextLength,
            DEFAULT_TEXT_LENGTH
        ).handleAttributeAction(
            DEFAULT_TEXT_LENGTH
        ) {
            contentInputView.setTextLength(it)
        }


        getString(R.styleable.SPTextFieldBaseView_android_hint).orEmpty()
            .handleAttributeAction(
                EMPTY_TEXT
            ) {
                hint = it
            }

        getString(R.styleable.SPTextFieldBaseView_descriptionText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                descriptionText = it
            }

        getResourceId(
            R.styleable.SPTextFieldBaseView_textAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            textAppearance = it
        }

        getResourceId(
            R.styleable.SPTextFieldBaseView_descriptionTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            updateDescriptionTextAppearance(it)
        }

        getResourceId(
            R.styleable.SPTextFieldBaseView_labelTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            updateLabelTextAppearance(it)
        }

        getInt(R.styleable.SPTextFieldBaseView_startView, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(
                SPBaseView.NO_OBTAIN_VAL
            ) {
                handleStartView(it)
            }

        getInt(R.styleable.SPTextFieldBaseView_endView, SPBaseView.NO_OBTAIN_VAL)
            .handleAttributeAction(
                SPBaseView.NO_OBTAIN_VAL
            ) {
                handleEndView(it)
            }

        getInt(R.styleable.SPTextFieldBaseView_contentInputView, SPBaseView.NO_OBTAIN_VAL)
            .handleAttributeAction(
                SPBaseView.NO_OBTAIN_VAL
            ) {
                when (it) {
                    SPTextInputViewType.MASKED -> {
                        mask = context.getString(R.string.day_mask)
                        setupContentInputViewByType(SPTextInputViewType.SPMaskViewType(mask))
                    }
                    SPTextInputViewType.TEXT -> setupContentInputViewByType(SPTextInputViewType.SPTextViewType())
                    SPTextInputViewType.NUMBER -> setupContentInputViewByType(
                        SPTextInputViewType.SPNumberViewType()
                    )
                }
            }
        handleBorderColor()
    }

    private fun TypedArray.handleStartView(it: Int) {
        val type: SPStartViewType = when (it) {
            SPStartViewType.PHONE_PREFIX -> {
                if (getPhonePrefixFromAttr() != EMPTY_TEXT) {
                    SPStartViewType.SPPhonePrefixViewType(getPhonePrefixFromAttr())
                } else SPStartViewType.SPNoneViewType
            }
            SPStartViewType.IMAGE -> SPStartViewType.SPImageViewType()
            SPStartViewType.CARD -> SPStartViewType.SPCardViewType
            else -> SPStartViewType.SPNoneViewType
        }
        setupStartViewByType(type)
    }

    private fun TypedArray.getPhonePrefixFromAttr() =
        (getString(R.styleable.SPTextFieldBaseView_startViewText)
            ?: context.getString(R.string.default_phone_prefix))

    private fun TypedArray.getCurrencyFromAttr() =
        (getString(R.styleable.SPTextFieldBaseView_endViewText)
            ?: context.getString(R.string.default_currency))

    private fun TypedArray.handleEndView(it: Int) {
        val endType = when (it) {
            SPEndViewType.CURRENCY -> if (getCurrencyFromAttr() != EMPTY_TEXT) {
                SPEndViewType.SPCurrencyViewType(getCurrencyFromAttr())
            } else {
                SPEndViewType.SPNoneViewType
            }
            SPEndViewType.CARD -> SPEndViewType.SPCardViewType
            SPEndViewType.IMAGE -> SPEndViewType.SPImageViewType()
            SPEndViewType.REMOVABLE -> SPEndViewType.SPRemovableViewType
            else -> SPEndViewType.SPNoneViewType
        }
        setupEndViewByType(endType)
    }

    override fun setViewStyle(newStyle: Int) {
        with(newStyle) {
            setStyle(this)
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        contentInputView.isEnabled = enabled
        endView?.isEnabled = enabled
        startView?.isEnabled = enabled
        handleBorderColor()
    }

    /**
     * Sets a end click listener.
     */
    fun setTrailClickListener(onClickListener: () -> Unit? = {}) {
        endView?.onClick { onClickListener() }
    }

    /**
     * Allows to update a text appearance by styles
     */
    protected fun updateTextAppearance(textAppearance: Int) =
        contentInputView.setTextStyle(textAppearance)

    private fun updateLabelTextAppearance(textAppearance: Int) =
        binding.textLabel.setTextStyle(textAppearance)

    private fun updateDescriptionTextAppearance(textAppearance: Int) =
        binding.textDesc.setTextStyle(textAppearance)

    private fun handleShowingLabelText() {
        binding.textLabel.isVisible = labelText.isNotEmpty()
        if (inputMandatory) {
            binding.textLabel.setText(labelText.appendAsterisk(), TextView.BufferType.SPANNABLE)
        } else {
            binding.textLabel.text = labelText
        }
    }

    private fun handleShowingDescriptionText() {
        binding.textDesc.isVisible = descriptionText.isNotEmpty()
        binding.textDesc.text = descriptionText
    }

    override fun handleDistractiveState() {
        handleBorderColor()
    }

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        contentInputView.setOnEditorActionListener(listener)
    }

    private fun handleImeOption() {
        contentInputView.imeOptions = imeOption
    }

    fun addTextChangedListener(watcher: TextWatcher) {
        contentInputView.addTextChangedListener(watcher)
    }

    fun removeTextChangedListener(watcher: TextWatcher) {
        contentInputView.addTextChangedListener(watcher)
    }

    fun focus() = contentInputView.requestFocus()

    fun removeAllText() {
        val view = contentInputView
        if (view is SPEditTextMasked)
            view.mask = view.mask
        else
           text = EMPTY_TEXT
    }

    protected fun FrameLayout.addContentView(view: View?, defaultView: View? = null) {
        removeAllViews()
        if (view != null) {
            addView(view)
        } else if (defaultView != null) {
            addView(defaultView)
        }
        binding.flInputFieldContainer.invalidate()
    }

    private fun handleContentInputView() {
        binding.flInputFieldContainer.addContentView(contentInputView)
        contentInputView.setOnFocusChangeListener { _, focused ->
            handleBorderColor()
            onFocusChangeListener(focused)
        }
    }

    protected fun handleBorderColor() {
        binding.flContainer.changeBorder(
            when {
                !isEnabled ->
                    context.getColorFromAttribute(R.attr.separator_opaque)
                isDistractive ->
                    context.getColorFromAttribute(R.attr.accent_magenta)
                contentInputView.isFocused ->
                    context.getColorFromAttribute(R.attr.brand_primary)
                else ->
                    context.getColorFromAttribute(R.attr.separator_opaque)
            }, borderWidth
        )
    }

    companion object {
        const val ID_NEXT = 5
        const val DEFAULT_TEXT_LENGTH = -1 //no borders
    }
}