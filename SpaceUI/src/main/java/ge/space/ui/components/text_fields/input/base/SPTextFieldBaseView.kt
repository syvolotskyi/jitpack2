package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.content.res.TypedArray
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.appendAsterisk
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldLayoutBinding
import ge.space.ui.base.SPBaseView
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
abstract class SPTextFieldBaseView<VB : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), SPSetViewStyleInterface {

    /**
     * Sets a button title.
     */
    abstract var text: String


    /**
     * Sets a button hint.
     */
    abstract var hint: String

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

    var leadingView: View? = null
        set(value) {
            field = value

            binding.flLeading.removeAllViews()
            binding.flLeading.addView(leadingView)
            binding.flInputFieldContainer.invalidate()
        }

    var trailView: View? = null
        set(value) {
            field = value

            binding.flTrail.removeAllViews()
            binding.flTrail.addView(trailView)
        }

    /**
     * Lazy property for initialize ViewBinding in constructor
     */
    protected val inputTextBinding by lazy {
        getChildViewBinding()
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
            includeInputFieldContainer()
            setOnFocusChangeListener { _, focused ->
                binding.flContainer.setBackgroundResource(
                    if (focused) {
                        R.drawable.bg_text_field_focused
                    } else {
                        R.drawable.bg_text_field
                    }
                )

                onFocusChangeListener(focused)
            }
            binding.flContainer.changeBorder(context.getColorFromAttribute(R.attr.brand_primary),
                resources.getDimensionPixelSize(R.dimen.dimen_p_1).toFloat())
        }
    }

    private fun includeInputFieldContainer() {
        binding.flInputFieldContainer.addView(inputTextBinding.root)
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

    abstract fun setTextFieldStyle(@StyleRes defStyleRes: Int)

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
    protected abstract fun updateTextAppearance(@StyleRes textAppearance: Int)

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

    protected abstract fun handleImeOption()

    abstract fun addTextChangedListener(watcher: TextWatcher)

    abstract fun removeTextChangedListener(watcher: TextWatcher)

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getChildViewBinding(): VB

    companion object {
        const val ID_NEXT = 5
        const val DEFAULT_INT = 0
        const val DEFAULT_TEXT_LENGTH = -1 //no borders
    }
}