package ge.space.ui.view.button

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpHorizontalButtonLayoutBinding
import ge.space.ui.base.SPBaseButton

/**
 * Button view extended from [SPBaseButton] that allows to change its configuration.
 *
 * @property src [Int] value which applies a button image using a resource ID.
 */
class SPHorizontalButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseButton<SpHorizontalButtonLayoutBinding>(context, attrs, defStyleAttr) {

    /**
     * Sets a image resource
     */
    @IdRes
    var src = 0
        set(value) {
            field = value

            binding.ivRight.setImageResource(src)
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPBaseView,
            defStyleAttr
        ) {
            setButtonStyle(
                getResourceId(R.styleable.SPBaseView_sp_viewStyle, R.style.SPBaseView_SPBaseButton)
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPHorizontalButton,
            defStyleAttr
        ) {
            src = getResourceId(R.styleable.SPHorizontalButton_android_src, 0)
            text = getString(R.styleable.SPHorizontalButton_android_text).orEmpty()
        }
    }

    /**
     * Inflates and returns [SpHorizontalButtonLayoutBinding] value
     */
    override fun getViewBinding(): SpHorizontalButtonLayoutBinding =
        SpHorizontalButtonLayoutBinding.inflate(LayoutInflater.from(context), this)

    override fun updateTextColor(color: Int) {
        binding.buttonLabel.setTextColor(color)
    }

    override fun updateFontFace(face: Typeface?) {
        binding.buttonLabel.typeface = face
    }

    override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    override fun updateTextSize(textSize: Float) {
        binding.buttonLabel.setTextSize(
            TypedValue.COMPLEX_UNIT_PX, textSize
        )
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
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPViewStyle)

        styleAttrs.run {
            text = getString(R.styleable.SPButton_android_text).orEmpty()
            fontFamilyId = getResourceId(
                R.styleable.SPViewStyle_android_fontFamily,
                R.font.myriad_geo_bold
            )
            textSize = getDimension(R.styleable.SPViewStyle_android_textSize, FLOAT_ZERO)

            recycle()
        }
    }

    companion object {
        private const val FLOAT_ZERO = 0f
    }
}