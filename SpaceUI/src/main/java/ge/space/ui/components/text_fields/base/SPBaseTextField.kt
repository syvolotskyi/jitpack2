package ge.space.ui.components.text_fields.base

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.util.extension.handleAttributeAction

open class SPBaseTextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    /**
     * Sets a button title.
     */
    var text: String = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            _binding.etInputField.setText(value)
        }

    /**
     * Sets a label text.
     */
    var labelText: String = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            _binding.textLabel.text = value
        }

    /**
     * Sets a description text.
     */
    var descText: String = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            _binding.textDesc.text = value
        }


    /**
     * Sets a description text.
     */
    var maxLength: Int = 0
        set(value) {
            field = value

            setEditTextMaxLength(value)
        }


    /**
     * Inflates and returns [SpTextFieldLayoutBinding] value
     */
    protected val _binding by lazy {
        SpTextFieldLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
    /*    getContext().withStyledAttributes(
            attrs,
            R.styleable.SPBaseView,
            defStyleAttr
        ) {
                setButtonStyle(
                    getResourceId(R.styleable.SPBaseView_sp_viewStyle, R.style.SPButtonBaseView)
                )
        }*/

         getContext().withStyledAttributes(
             attrs,
             R.styleable.SPBaseTextView,
             defStyleAttr
         ) {
             getString(R.styleable.SPBaseTextView_sp_titleText).orEmpty().handleAttributeAction(
                 SPBaseView.EMPTY_TEXT
             ) {
                 labelText = it
             }

             getString(R.styleable.SPBaseTextView_sp_descText).orEmpty().handleAttributeAction(
                 SPBaseView.EMPTY_TEXT
             ) {
                 descText = it
             }
         }
        _binding.textLabel.text = "Label"
    }

    fun setEditTextMaxLength(length: Int) {
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = InputFilter.LengthFilter(length)
        _binding.etInputField.filters = filterArray
    }
}