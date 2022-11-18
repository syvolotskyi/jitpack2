package ge.space.ui.components.pager_indicator.helper

/**
 * [SPPageIndicatorStateHelper] interface which help to store and handle information of pager for Page Indicator
 */
interface SPPageIndicatorStateHelper {

    /**
     * Returns [Int] page indicator dots count
     */
    fun getItemCount(): Int

    /**
     * Remove listening selected page
     */
    fun removeListener()

}
