package ge.space.design.ui_components.text_fields.dropdown

import ge.space.spaceui.R
import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnDropdownBind
import ge.space.ui.util.extension.setHeight
import ge.space.ui.util.extension.setWidth
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

class SPOnChipItemModelBind : SPOnDropdownBind<SPDropdownItemModel> {
    override fun getBindItemModel(): (view: SPTextFieldDropdown<SPDropdownItemModel>, item: SPDropdownItemModel) -> Unit =
        { view, item ->
            item.viewData?.let {
                val image = it.createView(view.context).apply {
                    setHeight(context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height_small))
                    setWidth(context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height_small))
                }

                view.setImageWithPadding(
                    image,
                    left = view.context.resources.getDimensionPixelSize(R.dimen.dimen_p_12),
                    right = view.context.resources.getDimensionPixelSize(R.dimen.dimen_p_12)
                )
            }
            view.text = item.value
        }
}