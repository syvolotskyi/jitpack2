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
import ge.space.extensions.*
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPOnDistractiveInterface
import ge.space.ui.components.text_fields.input.utils.extension.setTextLength
import ge.space.ui.util.extension.SPSetViewStyleInterface
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
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), SPSetViewStyleInterface, SPOnDistractiveInterface {

    private var borderWidth: Float =
        context.resources.getDimensionPixelSize(R.dimen.dimen_p_0_5).toFloat()
    private val emptyLeadingView = FrameLayout(context).apply {
        setPadding(resources.getDimensionPixelSize(R.dimen.dimen_p_8))
    }
    private val emptyTrailView = FrameLayout(context).apply {
        setPadding(resources.getDimensionPixelSize(R.dimen.dimen_p_8))
    }

    /**
     * Sets a button title.
     */
    var text: String = EMPTY_TEXT
        get() = contextView.text.toString()
        set(value) {
            field = value

            contextView.setText(value)
        }


    /**
     * Sets a button hint.
     */
    var hint: String = EMPTY_TEXT
        get() = contextView?.hint.toString()
        set(value) {
            field = value

            contextView?.hint = value
        }

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

    var onFocusChangeListener: (Boolean) -> Unit = { }

    var leadingView: View? = emptyLeadingView
        set(value) {
            field = value

            binding.flLeading.removeAllViews()
            if (leadingView != null) {
                binding.flLeading.addView(leadingView)
            } else {
                binding.flLeading.addView(emptyLeadingView)
            }
            binding.flInputFieldContainer.invalidate()
        }

    var trailView: View? = emptyTrailView
        set(value) {
            field = value

            binding.flTrail.removeAllViews()
            if (trailView != null) {
                binding.flTrail.addView(trailView)
            } else {
                binding.flTrail.addView(emptyTrailView)
            }
            binding.flInputFieldContainer.invalidate()
        }

    var contextView: EditText = EditText(context)
        set(value) {
            field = value

            binding.flInputFieldContainer.removeAllViews()
            binding.flInputFieldContainer.addView(contextView)
            binding.flInputFieldContainer.invalidate()
        }


    override var isDistractive: Boolean = false
        set(value) {
            field = value

            handleDistractiveState()
        }


    /**
     * Inflates and returns [SpTextFieldLayoutBinding] value
     */
    protected val binding by lazy {
        SpTextFieldLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }


    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextFieldBaseView,
            defStyleAttr
        ) {
            applyAttributes()
            setOnFocusChangeListener { _, focused ->
                /*   binding.flContainer.changeBorder(
                       if (focused) {
                           R.drawable.bg_text_field_focused
                       } else {
                           R.drawable.bg_text_field
                       }
                   )*/
                onFocusChangeListener(focused)
            }
            binding.flContainer.changeBorder(
                context.getColorFromAttribute(R.attr.brand_primary),
                borderWidth
            )
            leadingView = emptyLeadingView
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

    fun setTextFieldStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPTextFieldInput)

        styleAttrs.run {
            contextView.setTextLength(
                getInt(R.styleable.SPTextFieldInput_inputTextLength, DEFAULT_TEXT_LENGTH)
            )

            recycle()
        }
    }

    private fun TypedArray.applyAttributes() {
        labelText = getString(R.styleable.SPTextFieldBaseView_titleText).orEmpty()
        imeOption = getInt(R.styleable.SPTextFieldBaseView_android_imeOptions, ID_NEXT)
        inputMandatory = getBoolean(R.styleable.SPTextFieldBaseView_inputMandatory, false)

        getString(R.styleable.SPTextFieldBaseView_android_hint).orEmpty()
            .handleAttributeAction(
                EMPTY_TEXT
            ) {
                hint = it
            }

        descriptionText = getString(R.styleable.SPTextFieldBaseView_descriptionText).orEmpty()

        textAppearance = getResourceId(
            R.styleable.SPTextFieldBaseView_android_textAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )

        val descTextAppearance = getResourceId(
            R.styleable.SPTextFieldBaseView_descriptionTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )

        updateDescriptionTextAppearance(descTextAppearance)

        val labelTextAppearance = getResourceId(
            R.styleable.SPTextFieldBaseView_labelTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )

        updateLabelTextAppearance(labelTextAppearance)

    }

    override fun setViewStyle(newStyle: Int) {
        with(newStyle) {
            setStyle(this)
            setTextFieldStyle(this)
        }
    }

    /**
     * Allows to update a text appearance by styles
     */
    private fun updateTextAppearance(textAppearance: Int) =
        contextView.setTextStyle(textAppearance)

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
        if (isDistractive) {
            binding.flContainer.changeBorder(
                context.getColorFromAttribute(R.attr.accent_magenta),
                borderWidth
            )
        } else {
            binding.flContainer.changeBorder(
                context.getColorFromAttribute(R.attr.brand_primary),
                borderWidth
            )

        }
        binding.flContainer.invalidate()
    }

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        contextView.setOnEditorActionListener(listener)
    }

    protected fun handleImeOption() {
        contextView.imeOptions = imeOption
    }

    fun addTextChangedListener(watcher: TextWatcher) {
        contextView.addTextChangedListener(watcher)
    }

    fun removeTextChangedListener(watcher: TextWatcher) {
         contextView.addTextChangedListener(watcher)
    }

    companion object {
        const val ID_NEXT = 5
        const val DEFAULT_INT = 0
        const val DEFAULT_TEXT_LENGTH = -1 //no borders
    }
}