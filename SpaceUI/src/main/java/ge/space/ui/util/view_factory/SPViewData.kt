package ge.space.ui.util.view_factory

import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle

sealed class SPViewData {
    data class SPImageResourcesData(@DrawableRes val res: Int) : SPViewData()
    data class SPImageDefaultResourcesData(@DrawableRes val res: Int) : SPViewData()
    data class SPImageUrlData(val url: String) : SPViewData()

    data class SPEmptyChipData(
        val chipSize: SPChipSize,
        val chipStyle: SPEmptyChipStyle
    ) : SPViewData()

    data class SPChipData(
        val chipSize: SPChipSize,
        @DrawableRes val drawableRes: Int
    ) : SPViewData()

    data class SPrimaryChipData(val chipSize: SPChipSize) : SPViewData()
    data class SPSecondaryChipData(
        val chipSize: SPChipSize,
        val bankLogoUrl: String,
        val paymentSystemUrl: String,
        val hasBorder: Boolean,
        @StyleRes val styleRes: Int
    ) :
        SPViewData()

    data class SPDigitalChipData(
        val chipSize: SPChipSize,
        val gradient: SPBankCardGradient,
        @StyleRes val styleRes: Int
    ) :
        SPViewData()
}