package ge.space.ui.util.view_factory
/**
 * Interface for implementing in view which can be created from SPViewFactory
 */
interface SPViewFactoryGetData {
    /**
     * Returns a view data object.
     */
    fun getViewData(): SPViewData
}