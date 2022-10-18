package ge.space.ui.components.accordion

/**
 * Call initAccordionData and set SPAccordionData to change title text, expanded text and divider visibility
 * @property titleText [String] is title text.
 * @property expandedText [String] is text which can be hiden.
 * @property showDivider [Boolean] is divider line visibility.
 *
 */
data class SPAccordionData(
    val titleText: String,
    val expandedText: String,
    val showDivider: Boolean = true
)
