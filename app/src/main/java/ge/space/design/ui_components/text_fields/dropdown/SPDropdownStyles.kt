package ge.space.design.ui_components.text_fields.dropdown

import android.content.Context
import ge.space.design.ui_components.bank_cards.card.SPButtonStyles
import ge.space.spaceui.R
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.component_type.chip.empty.SPDefaultEmptyChipData.Companion.getSmallEmptyChipData
import ge.space.ui.util.view_factory.component_type.chip.primary.SPDefaultPrimaryChipData.Companion.getSmallChipData

object SPTextFieldsDropdownItems {

    fun getList(context: Context) = listOf(
        SPDropdownItemModel(
            "Default chip",
            viewData = getSmallEmptyChipData(context)

        ),
        SPDropdownItemModel(
            "Primary chip",
            viewData = getSmallChipData(context)
        ),
        SPDropdownItemModel(
            "Digital chip",
            viewData = SPViewData.SPDigitalChipData(
                SPBankCardGradient.SPRadial(
                    colors = arrayListOf(
                        SPButtonStyles.GRADIENT_BLUE_1,
                        SPButtonStyles.GRADIENT_BLUE_2
                    )
                ),
                R.style.SPBankCardView_ChipDigital_Small,
            )
        ),
        SPDropdownItemModel(
            "Digital chip 2",
            viewData = SPViewData.SPDigitalChipData(
                SPBankCardGradient.SPRadial(
                    colors = arrayListOf(
                        SPButtonStyles.GRADIENT_GREEN_1,
                        SPButtonStyles.GRADIENT_GREEN_2
                    )
                ),
                R.style.SPBankCardView_ChipDigital_Small,
            )
        ),
        SPDropdownItemModel(
            "Digital chip 3",
            viewData = SPViewData.SPDigitalChipData(
                SPBankCardGradient.SPRadial(
                    colors = arrayListOf(
                        SPButtonStyles.GRADIENT_VIOLET_1,
                        SPButtonStyles.GRADIENT_VIOLET_2
                    )
                ),
                R.style.SPBankCardView_ChipDigital_Small,
            )
        )
    )

    fun getDefaultLangItem(context: Context) = getLanguagesList(context)[0]

    fun getLanguagesList(context: Context) = listOf(
        SPDropdownItemModel(
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
