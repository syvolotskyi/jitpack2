package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.SuperscriptSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.widget.TextViewCompat
import androidx.viewbinding.ViewBinding
import ge.space.extensions.appendAsterisk
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldLayoutBinding
import ge.space.ui.base.SPBaseView
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
) : LinearLayout(context, attrs, defStyleAttr) {

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
    var labelText: String = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            if (inputMandatory) {
                binding.textLabel.setText(value.appendAsterisk(), TextView.BufferType.SPANNABLE)
            } else {
                binding.textLabel.text = value
            }
        }

    var inputMandatory = false
        set(value) {
            field = value

            labelText = labelText
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
     * Sets a description text.
     */
    var descriptionText: String = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            binding.textDesc.text = value
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
            R.styleable.sp_text_field_base_view,
            defStyleAttr
        ) {
            applyAttributes()
            includeInputFieldContainer()
            setOnFocusChangeListener { _, focused ->
                binding.flInputFieldContainer.setBackgroundResource(
                    if (focused) {
                        R.drawable.bg_text_field_focused
                    } else {
                        R.drawable.bg_text_field
                    }
                )
            }
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
                R.styleable.sp_text_field_base_view
            )
        ) {
            applyAttributes()
            recycle()
        }
    }

    abstract fun setTextFieldStyle(@StyleRes defStyleRes: Int)

    private fun TypedArray.applyAttributes() {
        getString(R.styleable.sp_text_field_base_view_titleText).orEmpty()
            .handleAttributeAction(
                SPBaseView.EMPTY_TEXT
            ) {
                labelText = it
            }

        imeOption = getInt(R.styleable.sp_text_field_base_view_android_imeOptions, ID_NEXT)
        inputMandatory = getBoolean(R.styleable.sp_text_field_base_view_inputMandatory, false)

        getString(R.styleable.sp_text_field_base_view_android_hint).orEmpty()
            .handleAttributeAction(
                SPBaseView.EMPTY_TEXT
            ) {
                hint = it
            }

        getString(R.styleable.sp_text_field_base_view_descriptionText).orEmpty()
            .handleAttributeAction(
                SPBaseView.EMPTY_TEXT
            ) {
                descriptionText = it
            }

        val textAppearance = getResourceId(
            R.styleable.sp_text_field_base_view_android_textAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
        updateTextAppearance(textAppearance)

        val descTextAppearance = getResourceId(
            R.styleable.sp_text_field_base_view_descriptionTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )

        updateDescriptionTextAppearance(descTextAppearance)

        val labelTextAppearance = getResourceId(
            R.styleable.sp_text_field_base_view_labelTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )

        updateLabelTextAppearance(labelTextAppearance)

    }

    /**
     * Allows to update text style and BaseViewStyle programmatically
     */
    fun style(@StyleRes newStyle: Int) {
        with(newStyle) {
            setStyle(this)
            setTextFieldStyle(this)
        }
    }

    /**
     * Allows to update a text appearance by styles
     */
    abstract fun updateTextAppearance(@StyleRes textAppearance: Int)

    private fun updateLabelTextAppearance(textAppearance: Int) {
        TextViewCompat.setTextAppearance(binding.textLabel, textAppearance)
    }

    private fun updateDescriptionTextAppearance(textAppearance: Int) {
        TextViewCompat.setTextAppearance(binding.textDesc, textAppearance)
    }

    protected abstract fun handleImeOption()

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getChildViewBinding(): VB

    companion object {
        const val ID_NEXT = 5
    }
}