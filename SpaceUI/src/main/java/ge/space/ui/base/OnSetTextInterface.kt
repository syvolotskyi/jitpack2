package ge.space.ui.base

import androidx.annotation.StyleRes

interface OnSetTextInterface {
    /**
     * Allows to update a text using ViewBinding
     */
    fun updateText(text: String)

    /**
     * Allows to update a text appearance by styles
     */
    fun updateTextAppearance(@StyleRes textAppearance: Int)

}