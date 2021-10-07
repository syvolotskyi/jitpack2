package ge.space.ui.components.buttons

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import ge.space.spaceui.R

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
        ContextCompat.getColor(context, R.color.appPrimaryColor)
    private var iconColor: Int = Color.WHITE
    private var distractiveBackgroundColor: Int = ContextCompat.getColor(context, R.color.magenta)
    private var distractiveIconColor: Int = Color.WHITE
    private var borderWidth: Int = context.resources.getDimensionPixelSize(R.dimen.dimen_p_1)

    private var borderColor: Int =
        ContextCompat.getColor(context, R.color.appPrimaryColor)
        set(value) {
            field = value
            bubbleLayoutBinding.btnContainer.changeBorder(borderColor, borderWidth)
        }


    /**
     * Sets a style for the SPButton view.
     *
     * <p>
     * Default style theme is SPBaseView.SPBaseButton style.
     * <p>
     *
     * @param defStyleRes [Int] style resource id
     */
    override fun setButtonStyle(@StyleRes defStyleRes: Int) {
        super.setButtonStyle(defStyleRes)
        binding.buttonLabel.visibility = View.GONE
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPButtonIconic)

        styleAttrs.run {

            bubbleColor =
                getColor(
                    R.styleable.SPButtonIconic_iconBackgroundColor,
                    ContextCompat.getColor(context, R.color.appPrimaryColor)
                )
            borderColor =
                getColor(
                    R.styleable.SPButtonIconic_borderColor,
                    ContextCompat.getColor(context, R.color.appPrimaryColor)
                )

            distractiveIconColor =
                getColor(
                    R.styleable.SPButtonIconic_distractiveIconColor,
                    Color.WHITE
                )

            distractiveBackgroundColor =
                getColor(
                    R.styleable.SPButtonIconic_distractiveBorderColor,
                    ContextCompat.getColor(context, R.color.magenta)
                )

            bubbleLayoutBinding.btnContainer.color = bubbleColor

            iconColor = getColor(
                R.styleable.SPButtonIconic_iconColor,
                Color.WHITE
            )

            bubbleLayoutBinding.image.setColorFilter(iconColor)
            color = Color.TRANSPARENT

            recycle()
        }
    }

    override fun handleDistractiveState() {
        if (isDistractive) {
            bubbleLayoutBinding.btnContainer.color = distractiveColor
            bubbleLayoutBinding.btnContainer.changeBorder(distractiveBackgroundColor, borderWidth)
            bubbleLayoutBinding.image.setColorFilter(distractiveIconColor)
        } else {
            bubbleLayoutBinding.btnContainer.changeBorder(borderColor, borderWidth)
            bubbleLayoutBinding.btnContainer.color = bubbleColor
            bubbleLayoutBinding.image.setColorFilter(iconColor)
        }
        bubbleLayoutBinding.btnContainer.invalidate()
    }
}