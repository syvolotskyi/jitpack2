package ge.space.ui.components.buttons

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R

/**
 * Button view extended from [SPButtonVertical] that allows to change its configuration.
 *
 */
open class SPButtonIconic @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPButtonVertical(context, attrs, defStyleAttr) {

    private var bubbleColor: Int = Color.WHITE
    private var iconColor: Int = Color.WHITE
    private var distractiveBackgroundColor: Int = Color.WHITE
    private var distractiveIconColor: Int = Color.WHITE
    private var borderWidth: Int =  context.resources.getDimensionPixelSize(R.dimen.dimen_p_1)

    private var borderColor: Int = Color.WHITE
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
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.sp_button_iconic)

        styleAttrs.run {

            bubbleColor =
                getColor(R.styleable.sp_button_iconic_iconBackgroundColor, Color.WHITE)
            borderColor =
                getColor(R.styleable.sp_button_iconic_borderColor, Color.WHITE)

            distractiveIconColor =
                getColor(R.styleable.sp_button_iconic_distractiveIconColor, Color.WHITE)
            distractiveBackgroundColor =
                getColor(R.styleable.sp_button_iconic_distractiveBorderColor, Color.WHITE)

            bubbleLayoutBinding.btnContainer.color = bubbleColor

            iconColor = getColor(
                R.styleable.sp_button_iconic_iconColor,
                Color.WHITE
            )

            bubbleLayoutBinding.image.setColorFilter(iconColor)


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