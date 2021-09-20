package ge.space.ui.components.buttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import ge.space.extensions.setHeight
import ge.space.extensions.setTextStyle
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonIconicLayoutBinding
import ge.space.ui.components.buttons.base.SPButtonBaseView

open class SPButtonIconic@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPButtonBaseView<SpButtonIconicLayoutBinding>(context, attrs, defStyleAttr)  {

    /**
     * Sets a image resource
     */
    @IdRes
    var src = 0
        set(value) {
            field = value

            binding.image.setImageResource(src)
        }


    private var iconPadding = resources.getDimensionPixelSize(R.dimen.dimen_p_15)
        set(value) {
            field = value

            binding.btnContainer.setPadding(
                    iconPadding,
                    iconPadding,
                    iconPadding,
                    iconPadding
            )
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_base_view,
            defStyleAttr
        ) {
            setButtonStyle(
                getResourceId(R.styleable.sp_base_view_style, R.style.SPButton_Iconic_Size24)
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_button_iconic,
            defStyleAttr
        ) {
            src = getResourceId(R.styleable.sp_button_iconic_android_src, 0)
            isEnabled = getBoolean(R.styleable.sp_button_iconic_android_enabled, true)
        }
    }

    /**
     * Inflates and returns [SpButtonVerticalLayoutBinding] value
     */
    override fun getViewBinding(): SpButtonIconicLayoutBinding =
        SpButtonIconicLayoutBinding.inflate(LayoutInflater.from(context), this)

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

            val buttonHeight = getResourceId(
                    R.styleable.sp_button_view_style_buttonHeight,
                    DEFAULT_OBTAIN_VAL
            )
            iconPadding = resources.getDimensionPixelSize(
                    getResourceId(
                            R.styleable.sp_button_view_style_btnIconPadding, DEFAULT_ICON_PADDING
                    )
            )
            binding.image.setHeight(resources.getDimensionPixelSize(buttonHeight))
            binding.image.setWidth(resources.getDimensionPixelSize(buttonHeight))
            recycle()
        }

    }

    override fun handleDistractiveState() {
        //TODO should be implemented
    }
    companion object {
        private const val DEFAULT_ICON_PADDING = 0
    }
}