package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldDropdownBinding
import ge.space.ui.base.SPBaseView

abstract class SPBaseTextFieldDropdown<T> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPTextFieldBaseView<SpTextFieldDropdownBinding>(context, attrs, defStyleAttr) {

    /**
     * Sets a image resource
     */
    @IdRes
    var src = 0
        set(value) {
            field = value

            inputTextBinding.ivLeftImage.setImageResource(src)
        }

    /**
     * Sets a image visible state
     */
    var isIconVisible = true
        set(value) {
            field = value

            inputTextBinding.ivLeftImage.isVisible = field
        }

    /**
     * Sets a default text
     */
    var defaultText = ""
        set(value) {
            field = value

            inputTextBinding.etInputField.text = defaultText
        }


    /**
     * Sets a default image
     */
    @IdRes
    var defaultIcon = 0
        set(value) {
            field = value

            inputTextBinding.ivLeftImage.setImageResource(src)
        }

    abstract fun bindViewValue(view: TextView, item: T)

    override var text: String = SPBaseView.EMPTY_TEXT
        get() = inputTextBinding.etInputField.text.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.text = value
        }

    override var hint: String = SPBaseView.EMPTY_TEXT
        get() = inputTextBinding.etInputField.hint.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.hint = value
        }

    override fun getChildViewBinding(): SpTextFieldDropdownBinding {
        return SpTextFieldDropdownBinding.inflate(
            LayoutInflater.from(context),
            this,
            false
        )
    }

    override fun setTextFieldStyle(defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.sp_text_field_dropdown)

        styleAttrs.run {
            isIconVisible = getBoolean(R.styleable.sp_text_field_dropdown_isIconVisible, false)
            defaultText =
                getString(R.styleable.sp_text_field_dropdown_defaultText) ?: ""
            defaultIcon =
                getResourceId(R.styleable.sp_text_field_dropdown_defaultIcon, 0)

            recycle()
        }
    }

    override fun handleImeOption() {
        inputTextBinding.etInputField.imeOptions = imeOption
    }

    override fun updateTextAppearance(textAppearance: Int) {
        TextViewCompat.setTextAppearance(inputTextBinding.etInputField, textAppearance)
    }
}
