package ge.space.design.ui_components.bank_cards.chip.secondary_chip

import com.example.spacedesignsystem.R
import ge.space.ui.util.extension.EMPTY_TEXT

data class SPSecondaryChipSupport(
    val resId: Int = R.style.SPBankCardView_ChipSecondary,
    val bankLogoUrl: String = EMPTY_TEXT,
    val paymentSystemUrl: String = EMPTY_TEXT
)

object SPSecondaryChipStyles {
    private val BANK =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e909c220-389c-4522-b0ba-bf4b55749697/BrandTBC_Size20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210901%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210901T090855Z&X-Amz-Expires=86400&X-Amz-Signature=eca63dd98c72e5ff88e6a670e8cedb7bb98636e234c2c66852ff1970882ced80&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22BrandTBC_Size20.png%22"
    private val VTB =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/2ef608ff-51d9-4dd3-8679-539b353d52aa/BrandVTB_Bank_Size20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T110508Z&X-Amz-Expires=86400&X-Amz-Signature=01c9d5e86f64c1bc11f720e41d3879a100a122a297fe9f4e64955605de7472dc&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22BrandVTB_Bank_Size20.png%22"
    private val BANK_OF_GEORGIA =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6db62996-9069-488a-b7db-0f279ae84346/BrandBank_of_Georgia_Size20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T110546Z&X-Amz-Expires=86400&X-Amz-Signature=7aeef226acedd416ed252575ad678b6a7445b8ab88025fa4202698950211bb8d&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22BrandBank_of_Georgia_Size20.png%22"
    private val HALYK_BANK =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/7b74c79b-3374-427f-b9a7-c1bb7dd95771/BrandHalyk_Bank_Size20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T110637Z&X-Amz-Expires=86400&X-Amz-Signature=4d31db073de451df22dddfe71ff919d05da3b5b648b3876c3e5e4f554848ce39&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22BrandHalyk_Bank_Size20.png%22"
    private val MADAD =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/5ac249ac-cfad-44ef-b47f-1aa835116c47/BrandMadad_Invest_Bank_Size20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T110727Z&X-Amz-Expires=86400&X-Amz-Signature=16b0e396800ae13370390c07f339ddecf8fcc1201bb8d521dfc5a52d1a90e9a0&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22BrandMadad_Invest_Bank_Size20.png%22"
    private val AMZ =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/d00f8f02-03e8-4193-9711-c9d599a6d14d/BrandQQB_Size20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T110814Z&X-Amz-Expires=86400&X-Amz-Signature=244654be150e2f1ecc3aa23a33a4f9c88063076177c5ad625fc239862cd12fcb&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22BrandQQB_Size20.png%22"

    private val MASTERCARD =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/100e7b99-efe0-4b95-b670-38c20463ba87/VariantMasterCard.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210901%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210901T090931Z&X-Amz-Expires=86400&X-Amz-Signature=32c1e711f08e0979af1bdd2056f926d714ddab649e1edb6438240e7c508f4ee8&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantMasterCard.png%22"
    private val VISA =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/79619553-5164-478f-abc3-3e6ba76fc63e/VariantVisa_SizeSmall_AlternativeTrue.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T105943Z&X-Amz-Expires=86400&X-Amz-Signature=b8345a7120fbab98395a115358990c72e35d2393a005b493f77561a18d0fc5aa&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantVisa_SizeSmall_AlternativeTrue.png%22"
    private val UZ_PAY =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/da1cfbb8-835a-41a7-a5aa-1423792684ed/VariantUZ_Pay_SizeSmall_AlternativeFalse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T105830Z&X-Amz-Expires=86400&X-Amz-Signature=06d24b793908b121a58989fbbe5e275620d8b454e37933dcb76e91697dd37cec&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantUZ_Pay_SizeSmall_AlternativeFalse.png%22"
    private val UNION_PAY =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/941654bc-ee46-421c-aaa1-a7c40c36d033/VariantUnionPay_SizeSmall_AlternativeFalse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T110100Z&X-Amz-Expires=86400&X-Amz-Signature=5a008e41439b9850b24f3f01130d8a17aef9a5667a133ab9ba3393764471bbf0&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantUnionPay_SizeSmall_AlternativeFalse.png%22"
    private val HUMO =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/5075538f-1c53-4056-bef1-3f4351c2a684/VariantHumo_SizeSmall_AlternativeFalse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T110139Z&X-Amz-Expires=86400&X-Amz-Signature=d753019c4bc9fbc142a1de8084cf69c4886f086a741d86ef2677240cab147f1e&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantHumo_SizeSmall_AlternativeFalse.png%22"
    private val UNION_UZ =
        "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/843aee76-c84a-47ef-a98c-08112586bf44/VariantUnionPay__UZ_Pay_Alt_SizeSmall_AlternativeFalse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210902%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210902T110300Z&X-Amz-Expires=86400&X-Amz-Signature=288432036eb635d74c52a896ae3e46f369dbfd5c844e580500d9f1b910724193&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantUnionPay__UZ_Pay_Alt_SizeSmall_AlternativeFalse.png%22"

    val list = listOf(
        SPSecondaryChipSupport(
            bankLogoUrl = BANK_OF_GEORGIA,
            paymentSystemUrl = UNION_UZ
        ),
        SPSecondaryChipSupport(
            bankLogoUrl = BANK,
            paymentSystemUrl = MASTERCARD
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_WithBorder,
            bankLogoUrl = HALYK_BANK,
            paymentSystemUrl = HUMO
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium,
            bankLogoUrl = MADAD,
            paymentSystemUrl = UNION_PAY
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Medium_WithBorder,
            bankLogoUrl = VTB,
            paymentSystemUrl = VISA
        ),
        SPSecondaryChipSupport(
            R.style.SPBankCardView_ChipSecondary_Small,
            bankLogoUrl = AMZ,
            paymentSystemUrl = UZ_PAY
        )
    )
}