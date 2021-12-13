package ge.space.ui.base

import androidx.annotation.StyleRes

/**
 * Adds possibility to set Style Resource programmatically
 */
interface SPViewStyling {
    /**
     * Set style programmatically
     *
     * @param newStyle sets a new style res
     */
    fun setViewStyle(@StyleRes newStyle: Int)
}