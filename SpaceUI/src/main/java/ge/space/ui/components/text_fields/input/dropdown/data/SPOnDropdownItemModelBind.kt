package ge.space.ui.components.text_fields.input.dropdown.data

import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

class SPOnDropdownItemModelBind : SPOnDropdownBind<SPDropdownItemModel> {
    override fun getBindItemModel(): (view: SPTextFieldDropdown<SPDropdownItemModel>, item: SPDropdownItemModel) -> Unit =
        { view, item ->
            item.iconData?.let {
                val image = it.createView(view.context)
                view.setImage(image)
            }
            view.text = item.value
        }
}