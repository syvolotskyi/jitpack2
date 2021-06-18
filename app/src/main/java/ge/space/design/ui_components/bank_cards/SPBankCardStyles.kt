package ge.space.design.ui_components.bank_cards

import android.graphics.Color
import ge.space.ui.components.bank_cards.data.*

data class SPBankCardSupport(
    val accountNumber: String,
    val bankLogo: String,
    val bankName: String,
    val amount: String = "",
    val paySystemUrl: String = "",
    val cardBackground: SPBankCardGradient = SPBankCardGradient.SPNoneGradient(),
    val payWaveType: SPPayWaveType = SPPayWaveType.Light,
    val bankCardStatus: SPBankCardStatus = SPBankCardStatus.Available,
    val accountNumberStyle: SPAccountNumberStyle = SPAccountNumberStyle.White,
    val cardType: SPBankCardType = SPBankCardType.SPPhysical,
    val isCredit: Boolean = false,
    val hasPayWave: Boolean = true,
    val hasChip: Boolean = true,
    val isFavorite: Boolean = false,
    val balanceVisible: Boolean = true,
    val accountVisible: Boolean = true
)

object SPButtonStyles {
    private val BRAND_PRIMARY_COLOR =  Color.parseColor("#00A2B7")
    private val BLACK_COLOR =  Color.parseColor("#000000")

    private val GRADIENT_WHITE_1 = Color.parseColor("#E3E9F0")
    private val GRADIENT_WHITE_2 = Color.parseColor("#FFFFFF")

    private val GRADIENT_BLUE_1 = Color.parseColor("#2998FF")
    private val GRADIENT_BLUE_2 = Color.parseColor("#31C1FF")

    private val GRADIENT_GREEN_1 = Color.parseColor("#00C2CE")
    private val GRADIENT_GREEN_2 = Color.parseColor("#36EFAC")

    private val GRADIENT_VIOLET_1 = Color.parseColor("#7987FF")
    private val GRADIENT_VIOLET_2 = Color.parseColor("#BB87F8")

    private val GRADIENT_LIGHT_GREEN_1 = Color.parseColor("#31C396")
    private val GRADIENT_LIGHT_GREEN_2 = Color.parseColor("#4AF693")

    private val LOGO_TBC = "https://hh.ru/employer-logo/3190953.png"
    private val LOGO_SPACE = "https://media-exp1.licdn.com/dms/image/C560BAQFRDYOjrYnlMA/company-logo_200_200/0/1525704139391?e=2159024400&v=beta&t=oiF05kfz6n6F0BWRb1jSMLbPpLmfkgo7VNZ3aDJtflA"
    private val LOGO_MIB = "https://i1.wp.com/zanimaem.uz/wp-content/uploads/2019/04/Madadinvestbank.jpg?fit=235%2C150&ssl=1"
    private val LOGO_GEORGIA_BANK = "https://media.glassdoor.com/sqll/727708/bank-of-georgia-squarelogo-1504165558877.png"

    private val VARIANT_UNION_PAY_LOGO = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/aceccc45-5630-4074-a262-e8cc35a2e8ed/VariantUnionPay__UZ_Pay_Alt2.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210617%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210617T131811Z&X-Amz-Expires=86400&X-Amz-Signature=b7bb22e1ca293c5906245d650be6a56dd1e7da078254189184e478f134a92b2c&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantUnionPay__UZ_Pay_Alt2.png%22"
    private val MASTER_CARD_LOGO = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/100e7b99-efe0-4b95-b670-38c20463ba87/VariantMasterCard.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210617%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210617T132122Z&X-Amz-Expires=86400&X-Amz-Signature=a5f617233750b765717a5b6fc5e5487dde1d7be9cc0e593c05383b5f1bcd9353&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantMasterCard.png%22"
    private val UNION_LOGO = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/381464c0-057b-47e1-8c86-eb498f881da6/VariantUZ_Pay.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210617%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210617T132132Z&X-Amz-Expires=86400&X-Amz-Signature=2eed9bb200abeb1ebb800fc2875e9dddc25f016798b1a67c2034838696e5c286&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantUZ_Pay.png%22"
    private val UNION_PAY_LOGO = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/9d6f9892-fbb0-4c53-bfcd-1ce406cde207/VariantUnionPay.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210617%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210617T132143Z&X-Amz-Expires=86400&X-Amz-Signature=d5fce7ba65323befcb0b1b54b048cce87e7a13528d3116b888ed007de6769093&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantUnionPay.png%22"
    private val HUMO_LOGO = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6d62646e-356b-4d61-ad40-a1c473075664/VariantHumo.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210617%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210617T132157Z&X-Amz-Expires=86400&X-Amz-Signature=5e0285a2366d5af791a63e6dde82ba4c52a6a072e0f6f3bb3457582df4d9d036&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantHumo.png%22"
    private val VISA_LOGO = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/bf1f41a6-80e7-4e07-81c3-e30b2d435b29/VariantVisa.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210617%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210617T132208Z&X-Amz-Expires=86400&X-Amz-Signature=726d3bfa41bb5d4fb5f6c17153beb9744bdaa5008e33669c2b52dfd6cf611e9a&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22VariantVisa.png%22"

    val list = listOf(
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            amount = "2 000 750 UZS",
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            balanceVisible = false,
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            accountNumber = "**** 3232",
            isFavorite = true,
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            amount = "2 000 750 UZS",
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            bankCardStatus = SPBankCardStatus.Blocked,
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            amount = "2 000 750 UZS",
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            bankCardStatus = SPBankCardStatus.Pending,
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            amount = "2 000 750 UZS",
            accountVisible = false,
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 ₾",
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            balanceVisible = false,
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            accountNumber = "**** 3232",
            isFavorite = true,
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 ₾",
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            bankCardStatus = SPBankCardStatus.Blocked,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 ₾",
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            bankCardStatus = SPBankCardStatus.Pending,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 ₾",
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_WHITE_1,
                    GRADIENT_WHITE_2
                ), degrees = 90f
            ),
            payWaveType = SPPayWaveType.Dark,
            accountNumberStyle = SPAccountNumberStyle.Dark,
            accountNumber = "**** 3232",
            bankLogo = LOGO_MIB,
            bankName = "Madad Invest Bank",
            amount = "2 000 750 UZS",
            cardType = SPBankCardType.SPNone,
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_WHITE_1,
                    GRADIENT_WHITE_2
                ), degrees = 90f
            ),
            payWaveType = SPPayWaveType.Dark,
            accountNumberStyle = SPAccountNumberStyle.Dark,
            accountNumber = "**** 3232",
            bankLogo = LOGO_MIB,
            bankName = "Madad Invest Bank",
            amount = "2 000 750 UZS",
            cardType = SPBankCardType.SPNone,
            isCredit = true,
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_WHITE_1,
                    GRADIENT_WHITE_2
                ), degrees = 90f
            ),
            payWaveType = SPPayWaveType.Dark,
            accountNumberStyle = SPAccountNumberStyle.Dark,
            accountNumber = "**** 3232",
            bankLogo = LOGO_MIB,
            bankName = "Madad Invest Bank",
            balanceVisible = false,
            cardType = SPBankCardType.SPNone,
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_WHITE_1,
                    GRADIENT_WHITE_2
                ), degrees = 90f
            ),
            payWaveType = SPPayWaveType.Dark,
            accountNumberStyle = SPAccountNumberStyle.Dark,
            accountNumber = "**** 3232",
            isFavorite = true,
            bankLogo = LOGO_MIB,
            bankName = "Madad Invest Bank",
            amount = "2 000 750 UZS",
            cardType = SPBankCardType.SPNone,
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_WHITE_1,
                    GRADIENT_WHITE_2
                ), degrees = 90f
            ),
            payWaveType = SPPayWaveType.Dark,
            bankCardStatus = SPBankCardStatus.Blocked,
            accountNumberStyle = SPAccountNumberStyle.Dark,
            accountNumber = "**** 3232",
            bankLogo = LOGO_MIB,
            bankName = "Madad Invest Bank",
            amount = "2 000 750 UZS",
            cardType = SPBankCardType.SPNone,
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_WHITE_1,
                    GRADIENT_WHITE_2
                ), degrees = 90f
            ),
            payWaveType = SPPayWaveType.Dark,
            bankCardStatus = SPBankCardStatus.Pending,
            accountNumberStyle = SPAccountNumberStyle.Dark,
            accountNumber = "**** 3232",
            bankLogo = LOGO_MIB,
            bankName = "Madad Invest Bank",
            amount = "2 000 750 UZS",
            cardType = SPBankCardType.SPNone,
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_BLUE_1,
                    GRADIENT_BLUE_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            bankLogo = LOGO_GEORGIA_BANK,
            bankName = "Bank of Georgia",
            balanceVisible = false,
            cardType = SPBankCardType.SPNone,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_BLUE_1,
                    GRADIENT_BLUE_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            bankLogo = LOGO_GEORGIA_BANK,
            bankName = "Bank of Georgia",
            balanceVisible = false,
            cardType = SPBankCardType.SPNone,
            isCredit = true,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_BLUE_1,
                    GRADIENT_BLUE_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            isFavorite = true,
            bankLogo = LOGO_GEORGIA_BANK,
            bankName = "Bank of Georgia",
            balanceVisible = false,
            cardType = SPBankCardType.SPNone,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_BLUE_1,
                    GRADIENT_BLUE_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            bankCardStatus = SPBankCardStatus.Blocked,
            accountNumber = "**** 3232",
            bankLogo = LOGO_GEORGIA_BANK,
            bankName = "Bank of Georgia",
            balanceVisible = false,
            cardType = SPBankCardType.SPNone,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_BLUE_1,
                    GRADIENT_BLUE_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            bankCardStatus = SPBankCardStatus.Pending,
            accountNumber = "**** 3232",
            bankLogo = LOGO_GEORGIA_BANK,
            bankName = "Bank of Georgia",
            balanceVisible = false,
            cardType = SPBankCardType.SPNone,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_GREEN_1,
                    GRADIENT_GREEN_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 ₾",
            cardType = SPBankCardType.SPDigital("GEL"),
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_GREEN_1,
                    GRADIENT_GREEN_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            balanceVisible = false,
            cardType = SPBankCardType.SPDigital("GEL"),
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_GREEN_1,
                    GRADIENT_GREEN_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            isFavorite = true,
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 ₾",
            cardType = SPBankCardType.SPDigital("GEL"),
            paySystemUrl = UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_GREEN_1,
                    GRADIENT_GREEN_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            bankCardStatus = SPBankCardStatus.Blocked,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 ₾",
            cardType = SPBankCardType.SPDigital("GEL"),
            paySystemUrl = UNION_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_GREEN_1,
                    GRADIENT_GREEN_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            bankCardStatus = SPBankCardStatus.Pending,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 ₾",
            cardType = SPBankCardType.SPDigital("GEL")
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_VIOLET_1,
                    GRADIENT_VIOLET_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 $",
            cardType = SPBankCardType.SPDigital("USD"),
            paySystemUrl = UNION_LOGO
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_VIOLET_1,
                    GRADIENT_VIOLET_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            balanceVisible = false,
            cardType = SPBankCardType.SPDigital("USD")
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_VIOLET_1,
                    GRADIENT_VIOLET_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            isFavorite = true,
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 $",
            cardType = SPBankCardType.SPDigital("USD")
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_VIOLET_1,
                    GRADIENT_VIOLET_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            bankCardStatus = SPBankCardStatus.Blocked,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 $",
            cardType = SPBankCardType.SPDigital("USD")
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPRadial(
                colors = arrayListOf(
                    GRADIENT_VIOLET_1,
                    GRADIENT_VIOLET_2
                )
            ),
            hasChip = false,
            hasPayWave = false,
            bankCardStatus = SPBankCardStatus.Pending,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            bankName = "Space",
            amount = "2,000 $",
            cardType = SPBankCardType.SPDigital("USD")
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_LIGHT_GREEN_1,
                    GRADIENT_LIGHT_GREEN_2
                ), 33f
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            amount = "2,000 €",
            cardType = SPBankCardType.SPDigital("EUR")
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_LIGHT_GREEN_1,
                    GRADIENT_LIGHT_GREEN_2
                ), 33f
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            balanceVisible = false,
            cardType = SPBankCardType.SPDigital("EUR")
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_LIGHT_GREEN_1,
                    GRADIENT_LIGHT_GREEN_2
                ), 33f
            ),
            hasChip = false,
            hasPayWave = false,
            accountNumber = "**** 3232",
            isFavorite = true,
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            amount = "2,000 €",
            cardType = SPBankCardType.SPDigital("EUR")
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_LIGHT_GREEN_1,
                    GRADIENT_LIGHT_GREEN_2
                ), 33f
            ),
            hasChip = false,
            hasPayWave = false,
            bankCardStatus = SPBankCardStatus.Blocked,
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            amount = "2,000 €",
            cardType = SPBankCardType.SPDigital("EUR")
        ),
        SPBankCardSupport(
            cardBackground = SPBankCardGradient.SPLinear(
                colors = arrayListOf(
                    GRADIENT_LIGHT_GREEN_1,
                    GRADIENT_LIGHT_GREEN_2
                ), 33f
            ),
            hasChip = false,
            hasPayWave = false,
            bankCardStatus = SPBankCardStatus.Pending,
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            bankName = "TBC",
            amount = "2,000 €",
            cardType = SPBankCardType.SPDigital("EUR")
        ),
    )
}