package ge.space.ui.util.view_factory

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

sealed class SPViewData : Parcelable {
    @Parcelize
    data class SPImageResourcesData(@DrawableRes val res: Int) : SPViewData()

    @Parcelize
    data class SPImageUrlData(val url: String) : SPViewData()

    @Parcelize
    data class SPEmptyChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        val chipStyle: SPEmptyChipStyle,
        @StyleRes val styleRes: Int = R.style.SPBankCardView_Chip_Base
    ) : SPViewData()

    @Parcelize
    data class SPChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        @DrawableRes val drawableRes: Int,
        @StyleRes val styleRes: Int
    ) : SPViewData()

    @Parcelize
    data class SPrimaryChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        @StyleRes val styleRes: Int
    ) : SPViewData()

    @Parcelize
    data class SPSecondaryChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        val bankLogoUrl: String,
        val paymentSystemUrl: String,
        val hasBorder: Int,
        @StyleRes val styleRes: Int
    ) :
        SPViewData()

    @Parcelize
    data class SPDigitalChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        val gradient: @RawValue SPBankCardGradient,
        @StyleRes val styleRes: Int
    ) :
        SPViewData()

    @Parcelize
    data class SPNewCreditCards(val chipSize: SPChipSize) : SPViewData()
}