package ge.space.design.ui_components.bank_cards.chip.secondary_chip

import com.example.spacedesignsystem.R

import ge.space.ui.util.extension.EMPTY_STRING

data class SPSecondaryChipSupport(
    val resId: Int = R.style.SPBankCardView_ChipSecondary,
    val bankLogoUrl: String = EMPTY_STRING,
    val paymentSystemUrl: String = EMPTY_STRING
)

object SPSecondaryChipStyles {
    private val BANK =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e909c220-389c-4522-b0ba-bf4b55749697/BrandTBC_Size20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210901%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210901T090855Z&X-Amz-Expires=86400&X-Amz-Signature=eca63dd98c72e5ff88e6a670e8cedb7bb98636e234c2c66852ff1970882ced80&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22BrandTBC_Size20.png%22"
    private val VISA =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/100e7b99-efe0-4b95-b670-38c20463ba87/VariantMasterCard.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210901%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210901T090931Z&X-Amz-Expires=86400&X-Amz-Signature=32c1e711f08e0979af1bdd2056f926d714ddab649e1edb6438240e7c508f4ee8&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantMasterCard.png%22"
    val list = listOf(
        SPSecondaryChipSupport(
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_WithBorder,
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium,
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium_WithBorder,
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Small,
            bankLogoUrl = BANK,
            paymentSystemUrl = VISA
        )
    )
}