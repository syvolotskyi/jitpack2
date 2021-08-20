package ge.space.design.ui_components.bank_cards.chip.chip_icon

import ge.space.spaceui.R
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.bank_cards.data.SPChipIconStyle

data class SPChipIconSupport(
    val resId: Int = R.style.SPBankCardView_Chip,
    val iconStyle: SPChipIconStyle = SPChipIconStyle.Accent,
    val icon: Int = R.drawable.ic_bank_24_regular,
    val photoUrl: String? = null
)

object SPChipIconStyles {
    private const val PHOTO_URL =
        "https://images.ctfassets.net/hrltx12pl8hq/3hkYVkjAgg0vIPv52JXRxU/9418da2d548684a4eec491114762f3b6/Summer_jpg.jpg?fit=fill&w=480&h=270"

    val list = listOf(
        SPChipIconSupport(),
        SPChipIconSupport(
           R.style.SPBankCardView_Chip_Medium
        ),
        SPChipIconSupport(
            iconStyle = SPChipIconStyle.Dark
        ),
        SPChipIconSupport(
            R.style.SPBankCardView_Chip_Small,
            iconStyle = SPChipIconStyle.Dark
        ),
        SPChipIconSupport(
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            R.style.SPBankCardView_Chip_Medium,
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            iconStyle = SPChipIconStyle.Dark,
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            R.style.SPBankCardView_Chip_Small,
            iconStyle = SPChipIconStyle.Dark,
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            photoUrl = PHOTO_URL
        ),
        SPChipIconSupport(
            R.style.SPBankCardView_Chip_Medium,
            photoUrl = PHOTO_URL
        ),
    )
}