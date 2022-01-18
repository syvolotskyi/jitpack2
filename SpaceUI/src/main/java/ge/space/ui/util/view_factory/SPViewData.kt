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
import ge.space.ui.components.buttons.SPButton.IconDirection.Right
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class SPViewData : Parcelable {

    /**
     * Create an image view with resource
     *
     * @property params helps to setup view params for view
     */
    @Parcelize
    data class SPImageResourcesData(
        @DrawableRes val res: Int,
        var params: SPViewDataParams? = null,
        var backgroundColor: Int? = null,
        var tintColor: Int? = null
    ) :
        SPViewData()

    /**
     * Create an image view form url
     *
     * @property styleRes applies a style for view
     */
    @Parcelize
    data class SPImageUrlData(
        val url: String,
        var roundedCorners: Float = 0f
    ) : SPViewData()

    /**
     * Create a text view with style
     *
     * @property params helps to setup view params for view
     * @property styleRes applies a style for view
     */
    @Parcelize
    data class SPTextData(
        val initials: String,
        @StyleRes var textStyle: Int? = null,
        var params: SPViewDataParams? = null,
        var backgroundColor: Int? = null
    ) :
        SPViewData()

    /**
     * Create an edit text view
     *
     * @property params helps to setup view params for view
     * @property styleRes applies a style for view
     */
    @Parcelize
    data class SPEditTextData(
        @StyleRes var style: Int? = null,
        var hint: String? = null,
        var inputType: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD,
        var lines: Int? = null,
        var params: SPViewDataParams? = null,
    ) :
        SPViewData()

    /**
     * Create a masked edit text (for example phone or date)
     *
     * @property params helps to setup view params for view
     * @property styleRes applies a style for view
     */
    @Parcelize
    data class SPMaskedEditTextData(
        @StyleRes var textAppereance: Int? = null,
        var mask: String,
        var hint: String? = null,
        var params: SPViewDataParams? = null,
    ) :
        SPViewData()

    /**
     * Create an empty chip
     *
     * @property styleRes applies a style for view, default is R.style.SPBankCardView_Chip_Base
     */
    @Parcelize
    data class SPEmptyChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        val chipStyle: SPEmptyChipStyle,
        @StyleRes val styleRes: Int = R.style.SPBankCardView_Chip_Base
    ) : SPViewData()

    /**
     * Create an chip with drawable
     *
     * @property styleRes applies a style for view,
     */
    @Parcelize
    data class SPChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        @DrawableRes val drawableRes: Int,
        @StyleRes val styleRes: Int
    ) : SPViewData()

    /**
     * Create a primary chip
     *
     * @property params helps to setup view params for view
     * @property styleRes applies a style for view, default is R.style.SPBankCardView_Chip_Base
     */
    @Parcelize
    data class SPrimaryChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        @StyleRes val styleRes: Int = R.style.SPBankCardView_Chip_Base,
        var params: SPViewDataParams? = null,
    ) : SPViewData()

    /**
     * Create a secondary chip
     *
     * @property styleRes applies a style for view
     */
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

    /**
     * Create a digital chip
     *
     * @property styleRes applies a style for view
     */
    @Parcelize
    data class SPDigitalChipData(
        val chipHeight: Int,
        val chipWidth: Int,
        val gradient: @RawValue SPBankCardGradient,
        @StyleRes val styleRes: Int
    ) :
        SPViewData()

    /**
     * Create a credit card
     *
     * @property chipSize it's SPChipSize enum, can either Big, Medium or Small
     */
    @Parcelize
    data class SPNewCreditCards(val chipSize: SPChipSize) : SPViewData()

    /**
     * View params which is used in others data class, helps to setup view params for view
     */
    @Parcelize
    data class SPViewDataParams(
        var height: Int? = null,
        var width: Int? = null,
        var gravity: Int = Gravity.LEFT or Gravity.CENTER_VERTICAL,
        var paddingStart: Int = 0,
        var paddingEnd: Int = 0,
        var paddingTop: Int = 0,
        var paddingBottom: Int = 0,
    ) :
        Parcelable
}