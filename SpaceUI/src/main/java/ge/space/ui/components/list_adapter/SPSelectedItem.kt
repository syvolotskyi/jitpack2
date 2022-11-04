package ge.space.ui.components.list_adapter

/**
 * [SPSelectedItem] contains
 *
 * @param item [Data] is a model type
 * @param isSelected [Boolean] is true when item is selected
 */
data class SPSelectedItem<Data>(
    val item: Data,
    var isSelected: Boolean
)