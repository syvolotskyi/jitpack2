package ge.space.ui.components.controls.radio.base

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.extensions.EMPTY_TEXT
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_INT
import ge.space.ui.base.SPViewStyling

/**
 * Abstract base RadioButton view extended from [LinearLayout] that allows to change its configuration.
 * It has to be extended to apply styled properties.
 *
 * @property text [String] value which applies a button label text
 */
abstract class SpBaseRadioButton  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    /**
     * Sets a title string
     */
    var title: String = EMPTY_TEXT
        set(value) {
            field = value

            handleTitle(value)
        }

    /**
     * Sets a isChecked state to radio button
     */
    var isChecked: Boolean = false
        set(value) {
            field = value

            handleCheckingState()
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    protected var titleTextAppearance: Int = DEFAULT_INT

    protected abstract fun handleTitle(value: String)

    /**
     * Sets a textAppearance and descriptionTextAppearance to view
     */
    abstract fun updateTextAppearance( textAppearance: Int)

    protected abstract fun handleCheckingState()
}