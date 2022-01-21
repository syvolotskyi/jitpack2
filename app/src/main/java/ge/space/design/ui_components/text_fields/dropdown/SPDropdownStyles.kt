package ge.space.design.ui_components.text_fields.dropdown

import android.content.Context
import ge.space.spaceui.R
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.component_type.chip.empty.SPDefaultEmptyChipData.Companion.getSmallEmptyChipData
import ge.space.ui.util.view_factory.component_type.chip.primary.SPDefaultPrimaryChipData

object SPTextFieldsDropdownItems {
    fun getList(context: Context) = listOf(
        SPDropdownItemModel(
            1,
            "Default chip",
            getSmallEmptyChipData(context)

        ),
        SPDropdownItemModel(
            2,
            "Primary chip",
            SPDefaultPrimaryChipData.getSmallChipData(
                context, params = SPViewData.SPViewDataParams(
                    paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12),
                    paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12)
                )
            )
        )
    )
}
