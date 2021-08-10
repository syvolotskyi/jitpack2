package ge.space.design.ui_components.text_fields.dropdown

import com.example.spacedesignsystem.R
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.util.view_factory.SPViewData

object SPTextFieldsDropdownItems {
    private const val MASTER_CARD =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/570de19c-cee6-43af-9956-11f02321c869/VariantMasterCard_SizeSmall.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T131001Z&X-Amz-Expires=86400&X-Amz-Signature=3028b51e75d464a1f505cacd37e50280ec553002ed5a3219763e8826d61bd26c&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantMasterCard_SizeSmall.png%22"

    val list = listOf(
        SPDropdownItemModel(
            1,
            "Image from resources",
            SPViewData.SPImageResourcesData(R.drawable.ic_small_empty_chip_dark)
        ),
        SPDropdownItemModel(
            2,
            "Image from url",
            SPViewData.SPImageUrlData(MASTER_CARD)
        ),
        SPDropdownItemModel(
            3,
            "Primary chip",
            SPViewData.SPrimaryChipData(SPChipSize.Small, R.style.SPBankCardView_Chip)
        ),
    )
}
