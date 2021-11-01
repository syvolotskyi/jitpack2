package ge.space.ui.components.text_fields.masked.base

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import androidx.viewbinding.ViewBinding
import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.masked.password.SPPasswordEditText

abstract class SPPinEditText<VB : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
): SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Reference to [VB] instance which is related to ViewBinding
     */
    protected val binding: VB

    /**
     * Lazy property for initialize ViewBinding in constructor
     */
    private val _binding by lazy {
        getViewBinding()
    }

    /**
     * Sets a text
     */
    var text: String = EMPTY_TEXT
        set(value) {
            field = value

            updateText(value)
        }

    /**
     * Sets a labelText
     */
    var labelText: String = EMPTY_TEXT
        set(value) {
            field = value

            updateLabel(value)
        }

    /**
     * Sets a labelText
     */
    var descriptionText: String = EMPTY_TEXT
        set(value) {
            field = value

            updateDescription(value)
        }

    /**
     * Sets a error
     */
    var isError: Boolean = false
        set(hasError) {
            field = hasError
            handleError()
        }

    /**
     * Sets a maxLength
     */
    var maxLength: Int = SPPasswordEditText.DEFAULT_LENGTH
        set(value) {
            field = value

            handleMaxLength()
        }


    init {

        binding = _binding
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPPinEditText,
            defStyleAttr
        ) {
            text = getString(R.styleable.SPPinEditText_android_text).orEmpty()
            labelText = getString(R.styleable.SPPinEditText_pinLabelText).orEmpty()
            descriptionText = getString(R.styleable.SPPinEditText_pinDescriptionText).orEmpty()
            isEnabled = getBoolean(R.styleable.SPPinEditText_android_enabled, true)
            maxLength = getInt(
                R.styleable.SPPinEditText_android_maxLength,
                SPPasswordEditText.DEFAULT_LENGTH
            )
        }
    }

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB

    protected abstract fun updateText(text: String)
    protected abstract fun updateLabel(text: String)
    protected abstract fun updateDescription(text: String)
    protected abstract fun handleError()
    protected abstract fun handleMaxLength()
}