package ge.space.ui.components.text_fields.input.base

import android.content.Context
import android.util.AttributeSet
import android.util.Range
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextAreaLayoutBinding
import ge.space.spaceui.databinding.SpTextFieldLayoutBinding
import ge.space.ui.components.layout.SPFrameLayout

class SPTextAreaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextField_Base
) : SPFrameLayout(context, attrs), SPTextWatcher {

    /**
     * Inflates and returns [SpTextFieldLayoutBinding] value
     */
    private val contentBinding by lazy {
        SpTextAreaLayoutBinding.inflate(LayoutInflater.from(context), this)
    }

    init {
        contentBinding.root
    }

    /**
     * Sets a button title.
     */
    override var text: String = EMPTY_TEXT
        get() = contentBinding.textInput.toString()
        set(value) {
            field = value
            contentBinding.textInput.setText(value)
        }



    override fun setTextAppearance(styleRes: Int) {
        contentBinding.textInput.setTextStyle(styleRes)
    }


}