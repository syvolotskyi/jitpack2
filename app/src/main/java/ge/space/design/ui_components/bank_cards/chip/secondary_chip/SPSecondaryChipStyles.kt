package ge.space.design.ui_components.bank_cards.chip.secondary_chip

import com.example.spacedesignsystem.R

import ge.space.ui.util.extension.EMPTY_STRING

data class SPSecondaryChipSupport(
    val resId: Int = R.style.SPBankCardView_ChipSecondary,
    val bankLogoUrl: String = EMPTY_STRING,
    val paymentSystemUrl: String = EMPTY_STRING
)

object SPSecondaryChipStyles {
    private val VTB_BANK = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/cff631e7-1e6c-4f08-88ae-f5e816a545a6/VTB_Bank.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T124220Z&X-Amz-Expires=86400&X-Amz-Signature=cdf3d3c0cc46cb7fbf4d66ade434ab2705579bb9d6088b5211ae37615756b963&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VTB_Bank.png%22"
    private val GEO_BANK = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/bebe9002-ea52-44d1-91a6-14ba0e40c2c3/Bank_of_Georgia.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T124203Z&X-Amz-Expires=86400&X-Amz-Signature=3a7eac24a306d5920e4bc580a65b6302d9dff83c80749434e6e6e3b326d1cf83&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Bank_of_Georgia.png%22"
    private val HALYK_BANK = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/d0c07375-7e63-4f51-9226-9b5c62056e35/Halyk_Bank.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T125243Z&X-Amz-Expires=86400&X-Amz-Signature=be13e9db306ed3be95328c577169ade0318f2da8662b40a90abedadbceec1ed6&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Halyk_Bank.png%22"
    private val MADAD_BANK = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f769ebbd-f9f6-4ff1-bc54-2228cd9dbb58/Madad_Invest_Bank.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T125325Z&X-Amz-Expires=86400&X-Amz-Signature=3c6e9bba1d8e6c6aa28a0b8717990af8e88074e46260d2aac9394f14665689ee&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Madad_Invest_Bank.png%22"
    private val QQB_BANK = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/755c50d3-6dd2-4cb0-8786-e7e0ad65339c/QQB.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T125414Z&X-Amz-Expires=86400&X-Amz-Signature=3c1bccf36b6ec017f7e39b3a5d947eb04b74cc9105b7c5d6d40bcd4f9d33d2e4&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22QQB.png%22"

    private val MASTER_CARD = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/570de19c-cee6-43af-9956-11f02321c869/VariantMasterCard_SizeSmall.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T131001Z&X-Amz-Expires=86400&X-Amz-Signature=3028b51e75d464a1f505cacd37e50280ec553002ed5a3219763e8826d61bd26c&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantMasterCard_SizeSmall.png%22"
    private val UZ_PAY = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/9e04525a-3312-476f-990c-3d4146f68084/VariantUZ_Pay_SizeSmall.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T133050Z&X-Amz-Expires=86400&X-Amz-Signature=6d7c43a9adfecc2d8bd5f744de651e0b7648e1510fca2b95db378d1286e78f66&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantUZ_Pay_SizeSmall.png%22"
    private val UNION_PAY = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/85dac2ac-a21d-41f8-a632-58f336caef4f/VariantUnionPay_SizeSmall.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T133055Z&X-Amz-Expires=86400&X-Amz-Signature=64c85e91ece5ce6b877a4531d80147bb548e1ef5e7449373357ab172fdd7b786&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantUnionPay_SizeSmall.png%22"
    private val HUMO = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/7837b468-aa35-43e8-ac52-00eb3ca0c170/VariantHumo_SizeSmall.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210701%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210701T133100Z&X-Amz-Expires=86400&X-Amz-Signature=9ffe426debb4409986aa36fa507ff975eb90fc2b2ca9b44ed5d422a0e42bb2df&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantHumo_SizeSmall.png%22"
    private val VISA = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4b631f18-6610-45fa-af8e-50a06029fcfd/VariantVisa_SizeSmall_AlternativeFalse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210702%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210702T074141Z&X-Amz-Expires=86400&X-Amz-Signature=04f52979c3db375c0bbbfdbc7cb0aeeecf8318c755a813af3de53f7e7424e05b&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantVisa_SizeSmall_AlternativeFalse.png%22"

    val list = listOf(
        SPSecondaryChipSupport(
            bankLogoUrl = GEO_BANK,
            paymentSystemUrl = MASTER_CARD
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_WithBorder,
            bankLogoUrl = GEO_BANK,
            paymentSystemUrl = MASTER_CARD
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium,
            bankLogoUrl = VTB_BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium_WithBorder,
            bankLogoUrl = VTB_BANK,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Small,
            bankLogoUrl = HALYK_BANK,
            paymentSystemUrl = MASTER_CARD
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Small_WithBorder,
            bankLogoUrl = HALYK_BANK,
            paymentSystemUrl = MASTER_CARD
        ),
        SPSecondaryChipSupport(
            bankLogoUrl = MADAD_BANK,
            paymentSystemUrl = UNION_PAY
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_WithBorder,
            bankLogoUrl = MADAD_BANK,
            paymentSystemUrl = UNION_PAY
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium,
            bankLogoUrl = QQB_BANK,
            paymentSystemUrl = HUMO
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium_WithBorder,
            bankLogoUrl = QQB_BANK,
            paymentSystemUrl = UZ_PAY
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Small,
            bankLogoUrl = QQB_BANK,
            paymentSystemUrl = HUMO
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Small_WithBorder,
            bankLogoUrl = QQB_BANK,
            paymentSystemUrl = UZ_PAY
        )
    )
}