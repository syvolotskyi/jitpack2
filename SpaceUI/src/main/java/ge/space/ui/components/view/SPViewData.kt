package ge.space.ui.components.view

import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle

sealed class SPViewData {
    data class SPImageResourcesData(@DrawableRes val res: Int) :SPViewData()
    data class SPImageDefaultResourcesData(@DrawableRes val res: Int) :SPViewData()
    data class SPImageUrlData(val url: String) : SPViewData()

    data class SPEmptyChip(val chipStyle: SPEmptyChipStyle) : SPViewData()
    data class SPrimaryChip(val chipSize: SPChipSize) : SPViewData()
    data class SPSecondaryChip(val bankLogoUrl: String, @StyleRes val styleRes: Int) :
        SPViewData()

    data class SPDigitalChip(val gradient: SPBankCardGradient, @StyleRes val styleRes: Int) :
        SPViewData()
}