package ge.space.ui.components.buttons

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import ge.space.extensions.setHeight
import ge.space.extensions.setTextStyle
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonVerticalBubbleLayoutBinding
import ge.space.spaceui.databinding.SpButtonVerticalLayoutBinding
import ge.space.ui.components.buttons.base.SPButtonBaseView

/**
 * Button view extended from [LinearLayout] that allows to change its configuration.
 *
 * @property src [Int] value which applies a button image using a resource ID.
 * @property iconPadding [Int] value which applies a drawable padding.
 * @property distractiveColor [Int] value sets button color in distractive state
 *
 */
open class SPButtonVertical @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPButtonBaseView<SpButtonVerticalLayoutBinding>(context, attrs, defStyleAttr) {

    /**
     * Inflates and returns [SpButtonVerticalLayoutBinding] value
     */
    override fun getViewBinding(): SpButtonVerticalLayoutBinding =
            SpButtonVerticalLayoutBinding.inflate(LayoutInflater.from(context), this)

    private val bubbleLayoutBinding by lazy {
        SpButtonVerticalBubbleLayoutBinding.bind(binding.root)
    }

    /**
     * Sets a image resource
     */
    @IdRes
    var src = 0
        set(value) {
            field = value

            bubbleLayoutBinding.image.setImageResource(src)
        }

    private var iconPadding = resources.getDimensionPixelSize(R.dimen.dimen_p_15)
        set(value) {
            field = value

            bubbleLayoutBinding.btnContainer.setPadding(
                iconPadding,
                iconPadding,
                iconPadding,
                iconPadding
            )
        }

    /**
     *  it is a distractive color for image color
     */
    private var distractiveColor: Int = bubbleLayoutBinding.btnContainer.color

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_base_view,
            defStyleAttr
        ) {
            setButtonStyle(
                getResourceId(
                    R.styleable.sp_base_view_style, R.style.SPButton_Vertical_Size48
                )
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_button_vertical,
            defStyleAttr
        ) {
            src = getResourceId(R.styleable.sp_button_vertical_android_src, 0)
            text = getString(R.styleable.sp_button_vertical_android_text).orEmpty()
            isEnabled = getBoolean(R.styleable.sp_button_vertical_android_enabled, true)
        }
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
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.sp_button_view_style)

        styleAttrs.run {
            val textAppearance = getResourceId(
                R.styleable.sp_button_view_style_android_textAppearance,
                DEFAULT_OBTAIN_VAL
            )
            val buttonHeight = getResourceId(
                R.styleable.sp_button_view_style_buttonHeight,
                DEFAULT_OBTAIN_VAL
            )
            iconPadding = resources.getDimensionPixelSize(
                getResourceId(
                    R.styleable.sp_button_view_style_btnIconPadding, DEFAULT_ICON_PADDING
                )
            )
            distractiveColor =
                getColor(R.styleable.sp_button_view_style_distractiveColor, Color.WHITE)

            updateTextAppearance(textAppearance)
            handleImageSize(resources.getDimensionPixelSize(buttonHeight))
            recycle()
        }

    }

     override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        bubbleLayoutBinding.btnContainer.children.forEach { it.isEnabled = enabled }
    }

     fun updateTextAppearance(textAppearance: Int) =
        binding.buttonLabel.setTextStyle(textAppearance)

    override fun handleDistractiveState() {
        bubbleLayoutBinding.btnContainer.color =
            if (isDistractive)
                distractiveColor
            else
                ContextCompat.getColor(
                    context,
                    R.color.appPrimaryColor
                )
        bubbleLayoutBinding.btnContainer.invalidate()
    }

    private fun handleImageSize(iconPixelSize: Int) {
        bubbleLayoutBinding.image.setHeight(iconPixelSize)
        bubbleLayoutBinding.image.setWidth(iconPixelSize)
    }

    companion object {
        private const val FLOAT_ZERO = 0f
        private const val DEFAULT_ICON_PADDING = 0
    }
}