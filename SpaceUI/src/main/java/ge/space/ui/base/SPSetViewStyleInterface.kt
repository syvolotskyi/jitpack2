package ge.space.ui.base

import androidx.annotation.StyleRes

/**
 * Interface marker to add possibility set Style Resource programmatically.
 *
 */
interface SPSetViewStyleInterface {
    /**
     * Set style programmatically
     *
     * @param newStyle sets a new style res
     */
    fun setViewStyle(@StyleRes newStyle: Int)
}