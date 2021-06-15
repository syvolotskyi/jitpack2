package ge.space.ui.components.text_fields.input.base

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

/**
 * Field view extended from [LinearLayout] that allows to change its configuration.
 *
 * @property text [String] value which sets a text.
 * @property labelText [String] value which sets a label text.
 * @property descText [String] value which sets a description text.
 * @property maxLength [Int] value which applies a max Length.
 */
abstract class SPTextFieldBaseView @JvmOverloads constructor(
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

            binding.etInputField.setText(value)
        }
        get() {
            return binding.etInputField.text.toString()
        }

    /**
     * Sets a label text.
     */
    var labelText: String = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            binding.textLabel.text = value
        }

    /**
     * Sets a description text.
     */
    var descText: String = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            binding.textDesc.text = value
        }


    /**
     * Sets a max length.
     */
    var maxLength: Int = 0
        set(value) {
            field = value

            setEditTextMaxLength(value)
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
            getString(R.styleable.sp_text_field_base_view_titleText).orEmpty().handleAttributeAction(
                SPBaseView.EMPTY_TEXT
            ) {
                labelText = it
            }

            val index = getInt(R.styleable.sp_text_field_base_view_android_imeOptions, 0)
            binding.etInputField.imeOptions = index
            getString(R.styleable.sp_text_field_base_view_descriptionText).orEmpty().handleAttributeAction(
                SPBaseView.EMPTY_TEXT
            ) {
                descText = it
            }
        }
    }

    private fun setEditTextMaxLength(length: Int) {
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = InputFilter.LengthFilter(length)
        binding.etInputField.filters = filterArray
    }
}