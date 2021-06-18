package ge.space.ui.components.buttons.base

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat
import androidx.viewbinding.ViewBinding
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView

/**
 * Abstract base Button view extended from [SPBaseView] that allows to change its configuration.
 * It has to be extended to apply styled properties.
 *
 * @property textColor [Int] value which applies a button label color
 * @property text [String] value which applies a button label text
 * @property fontFamilyId [Int] value which applies a button label font
 * @property textSize [Int] value which applies a button label size
 */
abstract class SPButtonBaseView<VB : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr) {

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
     * Sets a button title.
     */
    var text: String = EMPTY_TEXT
        set(value) {
            field = value

            updateText(value)
        }


    init {
        binding = _binding
    }

    /**
     * Allows to update button style and BaseViewStyle programmatically
     */
    fun style(@StyleRes newStyle: Int) {
        with(newStyle) {
            setStyle(this)
            setButtonStyle(this)
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        alpha = if (enabled) {
            DEFAULT_ALPHA
        } else {
            DISABLED_ALPHA
        }
    }

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB

    /**
     * Allows to update a text using ViewBinding
     */
    protected abstract fun updateText(text: String)

    /**
     * Allows to update a text appearance by styles
     */
    abstract fun updateTextAppearance(@StyleRes textAppearance: Int)

    /**
     * Allows to update button style using ViewBinding
     */
    abstract fun setButtonStyle(@StyleRes defStyleRes: Int)

    companion object {
        private const val FLOAT_ZERO = 0f
        private const val DEFAULT_ALPHA = 1f
        private const val DISABLED_ALPHA = 0.25f
    }
}