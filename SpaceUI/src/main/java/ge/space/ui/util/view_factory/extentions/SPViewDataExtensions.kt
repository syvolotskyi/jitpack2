package ge.space.ui.util.view_factory.extentions

import android.content.Context
import android.text.InputType
import android.view.Gravity
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.util.view_factory.SPViewData

/**
 * Returns a SPViewData object with preset styles and paddings which are using for currency fields.
 */
fun getCurrencyViewData(
    context: Context,
    initials: String,
    @StyleRes textStyle: Int = R.style.h600_medium_label_secondary
): SPViewData =
    SPViewData.SPTextData(
        initials, textStyle,
        SPViewData.SPViewDataParams(
            paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16),
            paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
        )
    )

/**
 * Returns a SPViewData object with preset styles and paddings which are using for number input fields.
 */
fun getNumberEditTextViewData(
    context: Context,
    hint: String,
    @StyleRes textStyle: Int = R.style.h600_bold_caps_brand_primary
): SPViewData =
    SPViewData.SPEditTextData(
        style = textStyle,
        hint = hint,
        inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL,
        lines = 1,
        params = SPViewData.SPViewDataParams(
            paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16),
            paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16),
            gravity = Gravity.LEFT
        )
    )
