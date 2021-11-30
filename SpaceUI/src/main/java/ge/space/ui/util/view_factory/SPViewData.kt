package ge.space.ui.util.view_factory

import android.os.Parcelable
import android.text.InputType
import android.view.Gravity
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class SPViewData : Parcelable {
    @Parcelize
    data class SPImageResourcesData(
        @DrawableRes val res: Int,
        var params: SPViewDataParams? = null,
        var backgroundColor: Int? = null,
        var tintColor: Int? = null
    ) :
        SPViewData()

    @Parcelize
    data class SPImageUrlData(
        val url: String,
        var roundedCorners: Float = 0f
    ) : SPViewData()

    @Parcelize
    data class SPTextData(
        val initials: String,
        @StyleRes var textStyle: Int? = null,
        var params: SPViewDataParams? = null,
        var backgroundColor: Int? = null
    ) :
        SPViewData()

    @Parcelize
    data class SPEditTextData(
        @StyleRes var style: Int? = null,
        var hint: String? = null,
        var inputType: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD,
        var lines: Int? = null,
        var params: SPViewDataParams? = null,
    ) :
        SPViewData()

    @Parcelize
    data class SPMaskedEditTextData(
        @StyleRes var style: Int? = null,
        var mask: String,
        var hint: String? = null,
        var params: SPViewDataParams? = null,
    ) :
        SPViewData()

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
        @StyleRes val styleRes: Int = R.style.SPBankCardView_Chip_Base,
        var params: SPViewDataParams? = null,
    ) : SPViewData()

    @Parcelize
    data class SPSecondaryChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        val bankLogoUrl: String,
        val paymentSystemUrl: String,
        val hasBorder: Boolean,
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

    @Parcelize
    data class SPViewDataParams(
        var height: Int? = null,
        var width: Int? = null,
        var gravity: Int = Gravity.CENTER,
        var paddingStart: Int = 0,
        var paddingEnd: Int = 0,
        var paddingTop: Int = 0,
        var paddingBottom: Int = 0,
    ) :
        Parcelable
}