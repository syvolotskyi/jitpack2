package ge.space.design.ui_components.bank_cards.card

import android.graphics.Color
import ge.space.extensions.EMPTY_TEXT
import ge.space.ui.components.bank_cards.data.*

data class SPBankCardSupport(
    val cardModel: SPBankCardModel,
    val accountNumber: String,
    val bankLogo: String,
    val amount: String = EMPTY_TEXT,
    val paySystemUrl: String = EMPTY_TEXT,
    val cardBackground: SPBankCardGradient = SPBankCardGradient.SPNoneGradient(),
    val payWaveType: SPPayWaveType = SPPayWaveType.Light,
    val bankCardStatus: SPBankCardStatus = SPBankCardStatus.Available,
    val accountNumberStyle: SPAccountNumberStyle = SPAccountNumberStyle.White,
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

    val GRADIENT_WHITE_1 = Color.parseColor("#E3E9F0")
    val GRADIENT_WHITE_2 = Color.parseColor("#FFFFFF")

    val GRADIENT_BLUE_1 = Color.parseColor("#2998FF")
    val GRADIENT_BLUE_2 = Color.parseColor("#31C1FF")

    val GRADIENT_GREEN_1 = Color.parseColor("#00C2CE")
    val GRADIENT_GREEN_2 = Color.parseColor("#36EFAC")

    val GRADIENT_VIOLET_1 = Color.parseColor("#7987FF")
    val GRADIENT_VIOLET_2 = Color.parseColor("#BB87F8")

    val GRADIENT_LIGHT_GREEN_1 = Color.parseColor("#31C396")
    val GRADIENT_LIGHT_GREEN_2 = Color.parseColor("#4AF693")

    val LOGO_SPACE = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/389473e6-c29e-4cab-a28d-d9e862e4f739/space.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20211015%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211015T081049Z&X-Amz-Expires=86400&X-Amz-Signature=e043f6902944824be6044ca5e8971be3126f0dfd84138659b793642de12e4b19&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22space.png%22"
    val LOGO_TBC = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/5bfe8263-8682-4a8c-b976-f3fb7e4ec064/tbc.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20211015%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211015T081204Z&X-Amz-Expires=86400&X-Amz-Signature=ab5cc56242905efadc2455a7606b0cbc092d829653155c88438234b136fbc5d2&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22tbc.png%22"
    val LOGO_MIB = "https://i1.wp.com/zanimaem.uz/wp-content/uploads/2019/04/Madadinvestbank.jpg?fit=235%2C150&ssl=1"
    val LOGO_GEORGIA_BANK = "https://media.glassdoor.com/sqll/727708/bank-of-georgia-squarelogo-1504165558877.png"

    val VARIANT_UNION_PAY_LOGO = "https://tinyurl.com/ygqxkhnm"
    val MASTER_CARD_LOGO = "https://tinyurl.com/yfejlew9"
    val UNION_LOGO = "https://tinyurl.com/ygpkj57w"
    val UNION_PAY_LOGO = "https://tinyurl.com/ygla7fk3"
    val HUMO_LOGO = "https://tinyurl.com/yj7pprsp"
    val VISA_LOGO = "https://tinyurl.com/ygtc4qmf"

    val list = listOf(
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("TBC"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            amount = "2 000 750 UZS",
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("TBC"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            balanceVisible = false,
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("TBC"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            accountNumber = "**** 3232",
            isFavorite = true,
            bankLogo = LOGO_TBC,
            amount = "2 000 750 UZS",
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("TBC"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            bankCardStatus = SPBankCardStatus.Blocked,
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            amount = "2 000 750 UZS",
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("TBC"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BRAND_PRIMARY_COLOR),
            bankCardStatus = SPBankCardStatus.Pending,
            accountNumber = "**** 3232",
            bankLogo = LOGO_TBC,
            amount = "2 000 750 UZS",
            accountVisible = false,
            paySystemUrl = VARIANT_UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("Space"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            amount = "2,000 ₾",
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("Space"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            balanceVisible = false,
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("Space"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            accountNumber = "**** 3232",
            isFavorite = true,
            bankLogo = LOGO_SPACE,
            amount = "2,000 ₾",
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("Space"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            bankCardStatus = SPBankCardStatus.Blocked,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            amount = "2,000 ₾",
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPPhysical("Space"),
            cardBackground = SPBankCardGradient.SPNoneGradient(BLACK_COLOR),
            bankCardStatus = SPBankCardStatus.Pending,
            accountNumber = "**** 3232",
            bankLogo = LOGO_SPACE,
            amount = "2,000 ₾",
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Madad Invest Bank"),
            cardBackground = SPBankCardGradient
                .SPLinear(
                colors = arrayListOf(
                    GRADIENT_WHITE_1,
                    GRADIENT_WHITE_2
                ), degrees = 90f
            ),
            payWaveType = SPPayWaveType.Dark,
            accountNumberStyle = SPAccountNumberStyle.Dark,
            accountNumber = "**** 3232",
            bankLogo = LOGO_MIB,
            amount = "2 000 750 UZS",
            paySystemUrl = HUMO_LOGO,
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Madad Invest Bank"),
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
            amount = "2 000 750 UZS",
            isCredit = true,
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Madad Invest Bank"),
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
            balanceVisible = false,
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Madad Invest Bank"),
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
            amount = "2 000 750 UZS",
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Madad Invest Bank"),
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
            amount = "2 000 750 UZS",
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Madad Invest Bank"),
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
            amount = "2 000 750 UZS",
            paySystemUrl = HUMO_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Bank of Georgia"),
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
            balanceVisible = false,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Bank of Georgia"),
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
            balanceVisible = false,
            isCredit = true,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Bank of Georgia"),
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
            balanceVisible = false,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Bank of Georgia"),
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
            balanceVisible = false,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDefault("Bank of Georgia"),
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
            balanceVisible = false,
            paySystemUrl = MASTER_CARD_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "GEL"),
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
            amount = "2,000 ₾",
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "GEL"),
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
            balanceVisible = false,
            paySystemUrl = VISA_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "GEL"),
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
            amount = "2,000 ₾",
            paySystemUrl = UNION_PAY_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "GEL"),
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
            amount = "2,000 ₾",
            paySystemUrl = UNION_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "GEL"),
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
            amount = "2,000 ₾",
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "USD"),
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
            amount = "2,000 $",
            paySystemUrl = UNION_LOGO
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "USD"),
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
            balanceVisible = false,
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "USD"),
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
            amount = "2,000 $",
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "USD"),
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
            amount = "2,000 $",
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("Space", "USD"),
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
            amount = "2,000 $",
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("TBC", "EUR"),
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
            amount = "2,000 €",
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("TBC", "EUR"),
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
            balanceVisible = false,
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("TBC", "EUR"),
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
            amount = "2,000 €",
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("TBC", "EUR"),
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
            amount = "2,000 €",
        ),
        SPBankCardSupport(
            cardModel = SPBankCardModel.SPDigital("TBC", "EUR"),
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
            amount = "2,000 €",
        ),
    )
}