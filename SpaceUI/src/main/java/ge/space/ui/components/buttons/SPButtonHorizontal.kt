package ge.space.ui.components.buttons

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonHorizontalLayoutBinding
import ge.space.ui.components.buttons.base.SPButtonBaseView

/**
 * Button view extended from [SPButtonBaseView] that allows to change its configuration.
 *
 * @property src [Int] value which applies a button image using a resource ID.
 */
class SPButtonHorizontal @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPButtonBaseView<SpButtonHorizontalLayoutBinding>(context, attrs, defStyleAttr) {

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
                getResourceId(R.styleable.SPBaseView_sp_viewStyle, R.style.SPButtonBaseView)
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPButtonHorizontal,
            defStyleAttr
        ) {
            src = getResourceId(R.styleable.SPButtonHorizontal_android_src, 0)
            text = getString(R.styleable.SPButtonHorizontal_android_text).orEmpty()
        }
    }

    /**
     * Inflates and returns [SpButtonHorizontalLayoutBinding] value
     */
    override fun getViewBinding(): SpButtonHorizontalLayoutBinding =
            SpButtonHorizontalLayoutBinding.inflate(LayoutInflater.from(context), this)



    override fun updateText(text: String) {
        binding.buttonLabel.text = text
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

            recycle()
        }
    }

    companion object {
        private const val FLOAT_ZERO = 0f
    }

    override fun updateTextAppearance(textAppearance: Int) {
        TODO("Not yet implemented")
    }
}