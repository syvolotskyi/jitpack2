package ge.space.ui.components.bank_cards.chip.credit_new_card

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpNewCreditCardLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.util.extension.visibleOrGone

/**
 * A simple static card view which is for creating a new credit card
 */
class SPNewCreditCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    /**
     * Applies a size of the view
     */
    var size : SPChipSize = SPChipSize.Big
        set(value) {
            field = value

            handleVisibility()
        }

    /**
     * Checks if [size] == [SPChipSize.Big]
     */
    private val isBig
        get() = size == SPChipSize.Big

    /**
     * Binds a view
     */
    private val binding =
        SpNewCreditCardLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.sp_credit_card_style,
            defStyleAttr
        ) {
            withStyledResource()
        }
    }

    private fun TypedArray.withStyledResource() {
        val styleRes = getResourceId(
            R.styleable.sp_credit_card_style_creditCardStyle,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
        if (styleRes > SPBaseView.DEFAULT_OBTAIN_VAL) {
            handleAttributesByStyleRes(styleRes)
        }
    }

    private fun handleAttributesByStyleRes(styleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(styleRes, R.styleable.sp_credit_card)

        styleAttrs.run {
            size = SPChipSize.values()[
                getInt(R.styleable.sp_credit_card_cardSize, SPBaseView.DEFAULT_OBTAIN_VAL)
            ]
        }
    }

    private fun handleVisibility() {
        with(binding) {
            frameBig.visibleOrGone(isBig)
            frameSmall.visibleOrGone(!isBig)
        }
    }
}