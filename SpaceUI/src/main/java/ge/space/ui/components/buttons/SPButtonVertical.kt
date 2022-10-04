package ge.space.ui.components.buttons

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonVerticalBubbleLayoutBinding
import ge.space.spaceui.databinding.SpButtonVerticalLayoutBinding
import ge.space.ui.base.SPDistractiveMode
import ge.space.ui.components.buttons.base.SPButtonBaseView
import ge.space.ui.util.extension.*

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
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPButton_Vertical_Size48
) : SPButtonBaseView<SpButtonVerticalLayoutBinding>(context, attrs, defStyleAttr, defStyleRes),
    SPDistractiveMode {

    /**
     * Inflates and returns [SpButtonVerticalLayoutBinding] value
     */
    override fun getViewBinding(): SpButtonVerticalLayoutBinding =
        SpButtonVerticalLayoutBinding.inflate(LayoutInflater.from(context), this)

    protected val bubbleLayoutBinding by lazy {
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


    override var isDistractive: Boolean = false
        set(value) {
            field = value

            handleDistractiveState()
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

    private var imageSize = resources.getDimensionPixelSize(R.dimen.dimen_p_12)

    private var buttonSize = resources.getDimensionPixelSize(R.dimen.dimen_p_24)


    /**
     *  it is a distractive color for image color
     */
    protected var distractiveColor: Int = bubbleLayoutBinding.btnContainer.color

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_button_view_style,
            defStyleAttr,
            defStyleRes
        ) {
            applyStyledResource()
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPButtonVertical,
            defStyleAttr,
            defStyleRes
        ) {
            applyVerticalButtonAttr()
        }
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
        context.withStyledAttributes(styleRes, R.styleable.sp_button_view_style) {
            applyStyledResource()
        }

        context.withStyledAttributes(styleRes, R.styleable.SPButtonVertical) {
            applyVerticalButtonAttr()
        }
    }


    private fun TypedArray.applyVerticalButtonAttr() {
        getString(R.styleable.SPButtonVertical_android_text).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) {
                text = it
            }
        isEnabled = getBoolean(R.styleable.SPButtonVertical_android_enabled, true)
        imageSize = resources.getDimensionPixelSize(
            getResourceId(
                R.styleable.SPButtonVertical_imgSize,
                R.dimen.dimen_p_12
            )
        )
        src = getResourceId(
            R.styleable.SPButtonVertical_android_src,
            R.drawable.ic_plus_16_regular
        )

        handleImageSize()
    }

    private fun TypedArray.applyStyledResource() {
        val textAppearance = getResourceId(
            R.styleable.sp_button_view_style_android_textAppearance,
            DEFAULT_OBTAIN_VAL
        )
        buttonSize = resources.getDimensionPixelSize(
            getResourceId(
                R.styleable.sp_button_view_style_buttonHeight,
                DEFAULT_OBTAIN_VAL
            )
        )
        iconPadding = resources.getDimensionPixelSize(
            getResourceId(
                R.styleable.sp_button_view_style_btnIconPadding, DEFAULT_OBTAIN_VAL
            )
        )
        distractiveColor =
            getColor(R.styleable.sp_button_view_style_distractiveColor, Color.WHITE)

        color = Color.TRANSPARENT

        updateTextAppearance(textAppearance)
        bubbleLayoutBinding.btnContainer.isCircle = true
        handleDistractiveState()
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
                context.getColorFromAttribute(R.attr.brand_primary)
        bubbleLayoutBinding.btnContainer.invalidate()
    }

    private fun handleImageSize() {
        bubbleLayoutBinding.image.setSize(imageSize, imageSize)

        bubbleLayoutBinding.btnContainer.setPadding(
            iconPadding,
            iconPadding,
            iconPadding,
            iconPadding
        )

    }
}