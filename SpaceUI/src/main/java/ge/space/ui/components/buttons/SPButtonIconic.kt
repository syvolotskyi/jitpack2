package ge.space.ui.components.buttons

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.goAway
import ge.space.ui.util.extension.handleAttributeAction

/**
 * Button view extended from [SPButtonVertical] that allows to change its configuration.
 *
 */
open class SPButtonIconic @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPButton_Iconic_Secondary_Size32
) : SPButtonVertical(context, attrs, defStyleAttr, defStyleRes) {

    private var bubbleColor: Int =
        context.getColorFromAttribute(R.attr.brand_primary)
    private var iconColor: Int = Color.WHITE
    private var distractiveBackgroundColor: Int =
        context.getColorFromAttribute(R.attr.accent_primary_magenta)
    private var distractiveIconColor: Int = Color.WHITE
    private var borderWidth: Int = context.resources.getDimensionPixelSize(R.dimen.dimen_p_1)

    private var borderColor: Int =
        ContextCompat.getColor(context,R.color.white)
        set(value) {
            field = value

            handleBorder(value)
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPButtonIconic,
            defStyleAttr,
            defStyleRes
        ) {
            applyButtonStyledAttrs()
        }

        binding.buttonLabel.visibility = View.GONE
    }

    /**
     * Sets a style for the SPButton view.
     *
     * <p>
     * Default style theme is SPBaseView.SPBaseButton style.
     * <p>
     *
     * @param styleRes [Int] style resource id
     */
    override fun setButtonStyle(@StyleRes styleRes: Int) {
        super.setButtonStyle(styleRes)

        context.withStyledAttributes(styleRes, R.styleable.SPButtonIconic) {
            applyButtonStyledAttrs()
        }
    }

    private fun TypedArray.applyButtonStyledAttrs() {
        bubbleColor =
            getColor(
                R.styleable.SPButtonIconic_iconBackgroundColor,
                context.getColorFromAttribute(R.attr.brand_primary)
            )

        distractiveIconColor =
            getColor(
                R.styleable.SPButtonIconic_distractiveIconColor,
                Color.WHITE
            )

        distractiveBackgroundColor =
            getColor(
                R.styleable.SPButtonIconic_distractiveBorderColor,
                context.getColorFromAttribute(R.attr.accent_primary_magenta)
            )

        bubbleLayoutBinding.btnContainer.color = bubbleColor
        bubbleLayoutBinding.btnContainer.isCircle = true

        getColor(
            R.styleable.SPButtonIconic_borderButtonColor,
            Color.WHITE
        ).handleAttributeAction(Color.TRANSPARENT) {
            borderColor = it
        }

        iconColor = getColor(
            R.styleable.SPButtonIconic_iconColor,
            Color.WHITE
        )

        bubbleLayoutBinding.image.setColorFilter(iconColor)
        binding.buttonLabel.goAway()
        color = Color.TRANSPARENT
    }

    override fun handleDistractiveState() {
        if (isDistractive) {
            bubbleLayoutBinding.btnContainer.color = distractiveColor
            handleBorder(distractiveBackgroundColor)
            bubbleLayoutBinding.image.setColorFilter(distractiveIconColor)
        } else {
            handleBorder(iconColor)
            bubbleLayoutBinding.btnContainer.color = bubbleColor
            bubbleLayoutBinding.image.setColorFilter(iconColor)
        }
        bubbleLayoutBinding.btnContainer.invalidate()
    }

    private fun handleBorder(value: Int) {
        if (value != Color.TRANSPARENT)
        bubbleLayoutBinding.btnContainer.changeBorder(value, borderWidth.toFloat())
    }
}