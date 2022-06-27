package ge.space.design.ui_components.text_fields.dropdown

import android.content.Context
import ge.space.spaceui.R
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.component_type.chip.empty.SPDefaultEmptyChipData.Companion.getSmallEmptyChipData
import ge.space.ui.util.view_factory.component_type.chip.primary.SPDefaultPrimaryChipData

object SPTextFieldsDropdownItems {

    fun getList(context: Context) = listOf(
        SPDropdownItemModel(
            1,
            "Default chip",
            iconData = getSmallEmptyChipData(context)

        ),
        SPDropdownItemModel(
            2,
            "Primary chip",
            iconData = SPDefaultPrimaryChipData.getSmallChipData(
                context, params = SPViewData.SPViewDataParams(
                    paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12),
                    paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12)
                )
            )
        )
    )

    fun getDefaultLangItem(context: Context) = getLanguagesList(context)[0]

    fun getLanguagesList(context: Context) = listOf(
        SPDropdownItemModel(
            1,
            "English",
            SPViewData.SPCircleImageUrlData(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Flag_of_England.svg/2560px-Flag_of_England.svg.png",
                context.resources.getDimensionPixelSize(R.dimen.dimen_p_0_5),
                context.getColorFromAttribute(R.attr.separator_opaque),
                SPViewData.SPViewDataParams(
                    context.resources.getDimensionPixelSize(R.dimen.dimen_p_38),
                    context.resources.getDimensionPixelSize(R.dimen.dimen_p_38)
                )

            )
        ),
        SPDropdownItemModel(
            2,
            "Georgian",
            SPViewData.SPCircleImageUrlData(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/Flag_of_Georgia_rounded.svg/1200px-Flag_of_Georgia_rounded.svg.png",
                context.resources.getDimensionPixelSize(R.dimen.dimen_p_0_5),
                context.getColorFromAttribute(R.attr.separator_opaque),
                SPViewData.SPViewDataParams(
                    context.resources.getDimensionPixelSize(R.dimen.dimen_p_38),
                    context.resources.getDimensionPixelSize(R.dimen.dimen_p_38)
                )
            )


        ),
        SPDropdownItemModel(
            3,
            "Ukraine",
            SPViewData.SPCircleImageUrlData(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Flag_of_Ukraine.svg/800px-Flag_of_Ukraine.svg.png",
                context.resources.getDimensionPixelSize(R.dimen.dimen_p_0_5),
                context.getColorFromAttribute(R.attr.separator_opaque),
                SPViewData.SPViewDataParams(
                    context.resources.getDimensionPixelSize(R.dimen.dimen_p_38),
                    context.resources.getDimensionPixelSize(R.dimen.dimen_p_38)
                )
            )
        )
    )
}
