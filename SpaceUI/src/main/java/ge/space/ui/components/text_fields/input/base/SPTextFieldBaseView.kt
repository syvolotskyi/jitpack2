package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import androidx.viewbinding.ViewBinding
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.util.extension.handleAttributeAction

/**
 * Field view extended from [LinearLayout] that allows to change its configuration.
 *
 * @property text [String] value which sets a text.
 * @property labelText [String] value which sets a label text.
 * @property labelText [String] value which sets a label text.
 * @property descText [String] value which sets a description text.
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
     * Lazy property for initialize ViewBinding in constructor
     */
    protected val inputTextBinding by lazy {
        getViewBinding()
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

            /*  val index = getInt(R.styleable.sp_text_field_base_view_android_imeOptions, 0)
              binding.etInputField.imeOptions = index
            */
            getString(R.styleable.sp_text_field_base_view_android_hint).orEmpty().handleAttributeAction(
                SPBaseView.EMPTY_TEXT
            ) {
                hint = it
            }

            getString(R.styleable.sp_text_field_base_view_descriptionText).orEmpty().handleAttributeAction(
                SPBaseView.EMPTY_TEXT
            ) {
                descText = it
            }

            includeInputText()

            setOnFocusChangeListener { _, focused ->
                binding.flContent.setBackgroundResource(
                    if (focused) {
                        R.drawable.bkg_text_field_focused
                    } else {
                        R.drawable.bkg_text_field
                    }
                )
            }
        }
    }

    private fun includeInputText() {
        binding.flContent.addView(inputTextBinding.root)
    }

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB
}