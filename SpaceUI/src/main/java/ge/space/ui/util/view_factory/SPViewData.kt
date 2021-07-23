package ge.space.ui.util.view_factory

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

sealed class SPViewData : Parcelable{
    @Parcelize
    data class SPImageResourcesData(@DrawableRes val res: Int) : SPViewData()
    @Parcelize
    data class SPImageDefaultResourcesData(@DrawableRes val res: Int) : SPViewData()
    @Parcelize
    data class SPImageUrlData(val url: String) : SPViewData()

    @Parcelize
    data class SPEmptyChip(val chipStyle: SPEmptyChipStyle) : SPViewData()
    @Parcelize
    data class SPrimaryChip(val chipSize: SPChipSize) : SPViewData()
    @Parcelize
    data class SPSecondaryChip(val bankLogoUrl: String, @StyleRes val styleRes: Int) : SPViewData()

    @Parcelize
    data class SPDigitalChip(val gradient: @RawValue SPBankCardGradient, @StyleRes val styleRes: Int) : SPViewData()
    @Parcelize
    data class SPNewCreditCards(val chipSize: SPChipSize): SPViewData()
}