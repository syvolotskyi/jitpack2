package ge.space.ui.components.controls.radio.list_item.extentions

import ge.space.spaceui.R
import ge.space.ui.components.controls.radio.list_item.SPListItemButton
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView


/**
 * Setup data to list item view
 *
 * @param title [String] sets a title to list item.
 * @param url [String] sets a default circle SPFrameLayout with uploaded image in start view
 */
fun SPListItemButton.setData(title: String, url: String) {
    val size = resources.getDimensionPixelSize(R.dimen.dimen_p_38)
    this.title = title
    this.setStartView(
        SPViewData.SPCircleImageUrlData(
            size,
            size,
            url,
            context.resources.getDimensionPixelSize(R.dimen.dimen_p_0_5),
            context.getColorFromAttribute(R.attr.separator_opaque_ui)
        ).createView(context)
    )
}