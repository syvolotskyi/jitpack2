package ge.space.ui.view.button

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.res.TypedArrayUtils.getResourceId
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpVerticalButtonBubbleLayoutBinding
import ge.space.spaceui.databinding.SpVerticalButtonLayoutBinding
import ge.space.ui.base.SPBaseButton
import ge.space.ui.view.button.SPVerticalButton.IconPadding
import ge.space.ui.view.button.SPVerticalButton.IconPadding.Large
import ge.space.ui.view.button.SPVerticalButton.IconPadding.Normal

/**
 * Button view extended from [LinearLayout] that allows to change its configuration.
 *
 * @property src [Int] value which applies a button image using a resource ID.
 * @property iconPadding [IconPadding] value which applies a drawable padding.
 *  This property can have a value from [IconPadding.Normal], [IconPadding.Large].
 */
class SPVerticalButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseButton<SpVerticalButtonLayoutBinding>(context, attrs, defStyleAttr) {

    private val bubbleLayoutBinding by lazy {
        SpVerticalButtonBubbleLayoutBinding.bind(binding.root)
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

    /**
     * Sets a drawable padding.
     */
    private var iconPadding = IconPadding.Normal
        set(value) {
            field = value

            handleDirectionArrow()
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPBaseView,
            defStyleAttr
        ) {
            setButtonStyle(
                getResourceId(R.styleable.SPBaseView_sp_viewStyle, R.style.SPBaseVerticalButton)
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPVerticalButton,
            defStyleAttr
        ) {
            src = getResourceId(R.styleable.SPVerticalButton_android_src, 0)
            text = getString(R.styleable.SPVerticalButton_android_text).orEmpty()
            isEnabled = getBoolean(R.styleable.SPVerticalButton_android_enabled, true)
        }
    }

    /**
     * Inflates and returns [SpVerticalButtonLayoutBinding] value
     */
    override fun getViewBinding(): SpVerticalButtonLayoutBinding =
        SpVerticalButtonLayoutBinding.inflate(LayoutInflater.from(context),this)

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

        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPViewStyle)

        styleAttrs.run {
            textColor = getColor(R.styleable.SPViewStyle_android_textColor, Color.WHITE)
            text = getString(R.styleable.SPButton_android_text).orEmpty()
            fontFamilyId = getResourceId(
                R.styleable.SPViewStyle_android_fontFamily,
                R.font.myriad_geo_bold
            )
            textSize = getDimension(R.styleable.SPViewStyle_android_textSize, FLOAT_ZERO)

            val iconPaddingInd = getInt(
                R.styleable.SPViewStyle_sp_iconPadding, DEFAULT_ICON_PADDING
            )
            iconPadding = IconPadding.values()[iconPaddingInd]

            recycle()
        }
    }

    override fun updateFontFace(face: Typeface?) {
        binding.buttonLabel.typeface = face
    }

    override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    override fun updateTextColor(color: Int) {
        binding.buttonLabel.setTextColor(textColor)
    }

    override fun updateTextSize(textSize: Float) {
        binding.buttonLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        bubbleLayoutBinding.btnContainer.children.forEach { it.isEnabled = enabled }
    }

    private fun handleDirectionArrow() {
        val padding = when (iconPadding) {
            IconPadding.Normal -> resources.getDimension(R.dimen.dimen_p_8).toInt()
            IconPadding.Large -> resources.getDimension(R.dimen.dimen_p_16).toInt()
        }

        bubbleLayoutBinding.btnContainer.setPadding(
            padding,
            padding,
            padding,
            padding
        )
    }

    /**
     * Enum class which is for icon padding.
     *
     * @property Normal set 8dp padding.
     * @property Large set 18dp padding.
     */
    enum class IconPadding {
        Normal,
        Large
    }

    companion object {
        private const val FLOAT_ZERO = 0f
        private const val DEFAULT_ICON_PADDING = 0
    }
}