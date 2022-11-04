package ge.space.ui.components.list_adapter

/**
 * [SPBaseAdapterListener] is interface which allow to set listener on click item in adapter,
 *  contains on Item Click Listener.
 * [Data] is an item, used in [SPBaseListAdapter]
 *
 */
interface SPBaseAdapterListener<Data> {
    /**
     * [onItemClick] calls when user clicks on an item
     *
     * @param position [Int] is position in list
     * @param data [Data] is a clicked item
     */
    fun onItemClick(position: Int, data: Data?)
}