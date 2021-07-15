package ge.space.ui.components.text_fields.input.dropdown

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.core.widget.TextViewCompat
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldDropdownBinding
import ge.space.spaceui.databinding.SpTextFieldDropdownSpaceBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.image.SPIconFactory
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.components.text_fields.input.utils.extension.SPDropdownItemModel

class SPTextFieldDropdown @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPTextFieldBaseView<SpTextFieldDropdownBinding>(context, attrs, defStyleAttr) {

    /**
     * Binding a item view after selecting
     */
    var bindViewValue: (item: SPDropdownItemModel) -> Unit = { _ -> }

    var items: List<SPDropdownItemModel> = emptyList()

    lateinit var iconData: SPIconFactory.SPIconData

    /**
     * Sets a default text
     */
    private var defaultText = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            inputTextBinding.etInputField.text = defaultText
        }


    /**
     * Sets a default image
     */
    var inflateType: InflateType = InflateType.None
        set(value) {
            field = value
            handleInflateType()
        }


    override var text: String = SPBaseView.EMPTY_TEXT
        get() = inputTextBinding.etInputField.text.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.text = value
        }

    fun setImage(view: View) {
        if (inflateType == InflateType.WithIcon) {
            inputTextBinding.ivLeftContainer.removeAllViews()
            inputTextBinding.ivLeftContainer.addView(view)
        }
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
            val inflateId = getInt(
                R.styleable.sp_text_field_dropdown_inflateType,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
            inflateType = InflateType.values()[inflateId]

            defaultText =
                getString(R.styleable.sp_text_field_dropdown_defaultText).orEmpty()

            recycle()
        }

        setOnClickListener { onDropDownClick() }
    }

    fun setDefault(text: String, iconType: SPIconFactory.SPIconData) {
        defaultText = text
        iconData = iconType
    }

    /**
     * While bottom sheets not implemented yet just show toast
     */
    private fun onDropDownClick() {
        Toast.makeText(inputTextBinding.root.context, "Dropdown click", Toast.LENGTH_SHORT).show()

        onSelectedItem(items.first())
    }

    private fun onSelectedItem(item: SPDropdownItemModel) {
        bindViewValue(item)
    }

    override fun handleImeOption() {
        inputTextBinding.etInputField.imeOptions = imeOption
    }

    override fun updateTextAppearance(textAppearance: Int) =
        TextViewCompat.setTextAppearance(inputTextBinding.etInputField, textAppearance)

    override fun addTextChangedListener(watcher: TextWatcher) =
        inputTextBinding.etInputField.addTextChangedListener(watcher)

    override fun removeTextChangedListener(watcher: TextWatcher) =
        inputTextBinding.etInputField.addTextChangedListener(watcher)

    private fun handleInflateType() {
        if (inflateType == InflateType.None) {
            val view = SpTextFieldDropdownSpaceBinding.inflate(
                LayoutInflater.from(context),
                this,
                false
            )
            inputTextBinding.ivLeftContainer.addView(view.root)
        }
    }

    enum class InflateType {
        None,
        WithIcon
    }
}

