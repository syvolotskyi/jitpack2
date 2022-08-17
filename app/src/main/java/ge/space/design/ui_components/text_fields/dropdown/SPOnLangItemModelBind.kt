package ge.space.design.ui_components.text_fields.dropdown

import ge.space.ui.components.text_fields.input.dropdown.SPTextFieldDropdown
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnDropdownBind
import ge.space.ui.util.extension.setHeight
import ge.space.ui.util.extension.setWidth
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

class SPOnLangItemModelBind : SPOnDropdownBind<SPDropdownItemModel> {
    override fun getBindItemModel(): (SPTextFieldDropdown<SPDropdownItemModel>, SPDropdownItemModel) -> Unit =
        { dropdown, item ->
            item.viewData?.let {
                val image = it.createView(dropdown.context).apply {
                    setHeight(context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.sp_bank_chip_height_small))
                    setWidth(context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.sp_bank_chip_height_small))
                    setPadding(
                        context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.dimen_p_6),
                        0,
                        context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.dimen_p_6),
                        0
                    )
                }
                dropdown.setImage(
                    image,
                    dropdown.context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.dimen_p_40),
                    dropdown.context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.sp_bank_chip_height_small)
                )
            }
            dropdown.text = item.value
        }
}