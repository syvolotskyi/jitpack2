package ge.space.ui.base

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

/**
 * Abstract base Button view extended from [SPBaseView] that allows to change its configuration.
 * It has to be extended to apply styled properties.
 *
 * @property textColor [Int] value which applies a button label color
 * @property text [String] value which applies a button label text
 * @property fontFamilyId [Int] value which applies a button label font
 * @property textSize [Int] value which applies a button label size
 */
abstract class SPBaseButton<VB : ViewBinding> @JvmOverloads constructor(
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

    /**
     * Makes a colored text.
     */
    protected var textColor = Color.WHITE
        set(value) {
            field = value

            updateTextColor(color)
        }

    /**
     * Sets a button title font.
     */
    @FontRes
    protected var fontFamilyId: Int = R.font.myriad_geo_bold
        set(value) {
            field = value

            changeFontFace()
        }

    /**
     * Sets a button title size.
     */
    protected var textSize: Float = FLOAT_ZERO
        set(value) {
            field = value

            updateTextSize(value)
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

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB

    /**
     * Allows to update text color using ViewBinding
     */
    protected abstract fun updateTextColor(@ColorInt color: Int)

    /**
     * Allows to update text size using ViewBinding
     */
    protected abstract fun updateTextSize(textSize: Float)

    /**
     * Allows to update font face using ViewBinding
     */
    protected abstract fun updateFontFace(face: Typeface?)

    /**
     * Allows to update a text using ViewBinding
     */
    protected abstract fun updateText(text: String)

    /**
     * Allows to update button style using ViewBinding
     */
    protected abstract fun setButtonStyle(@StyleRes defStyleRes: Int)

    private fun changeFontFace() {
        if (!isInEditMode) {
            val face = ResourcesCompat.getFont(context, fontFamilyId)
            updateFontFace(face)
        }
    }

    companion object {
        private const val FLOAT_ZERO = 0f
    }
}