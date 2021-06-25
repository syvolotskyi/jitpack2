package ge.space.ui.components.buttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.widget.TextViewCompat
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpButtonLayoutBinding
import ge.space.ui.components.buttons.SPButton.ArrowDirection
import ge.space.ui.components.buttons.SPButton.ArrowDirection.*
import ge.space.ui.components.buttons.base.SPButtonBaseView
import ge.space.ui.util.extension.getColorFromTextAppearance
import ge.space.ui.util.extension.handleAttributeAction

/**
 * Button view extended from abstract [SPButtonBaseView] generic that allows to change its configuration.
 * There are 4 realized styles which can be applied to the view:
 *
 * <p>
 *     1. SPBaseView.SPBaseButton.White
 *     2. SPBaseView.SPBaseButton.Accent
 *     3. SPBaseView.SPBaseButton.Dark
 *     4. SPBaseView.SPBaseButton.Transparent
 * <p>
 *
 * @property directionArrow [ArrowDirection] value which applies a button arrow direction.
 *  This property can have a value from [ArrowDirection.Right], [ArrowDirection.Left],
 *  [ArrowDirection.None].
 */
class SPButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPButtonBaseView<SpButtonLayoutBinding>(context, attrs, defStyleAttr) {

    /**
     * Makes a button arrow direction.
     */
    private var directionArrow = None
        set(value) {
            field = value

            handleDirectionArrow()
        }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_base_view,
            defStyleAttr
        ) {
            setButtonStyle(
                getResourceId(R.styleable.sp_base_view_style, R.style.SPButton_BaseView)
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_button,
            defStyleAttr
        ) {
            getString(R.styleable.sp_button_android_text).orEmpty()
                .handleAttributeAction(EMPTY_TEXT) {
                    text = it
                }
        }
    }

    /**
     * Inflates and returns [SpButtonLayoutBinding] value
     */
    override fun getViewBinding(): SpButtonLayoutBinding =
        SpButtonLayoutBinding.inflate(LayoutInflater.from(context), this)

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
        val styleAttrs = context.theme.obtainStyledAttributes(defStyleRes, R.styleable.sp_view_style)

        styleAttrs.run {
            val directionArrowInd = styleAttrs.getInt(R.styleable.sp_view_style_directionArrow, DEFAULT_OBTAIN_VAL)
            val textAppearance = getResourceId(R.styleable.sp_view_style_android_textAppearance, DEFAULT_OBTAIN_VAL)

            directionArrow = ArrowDirection.values()[directionArrowInd]
            updateTextAppearance(textAppearance)

            recycle()
        }
    }

    override fun updateTextAppearance(textAppearance: Int) {
        TextViewCompat.setTextAppearance(binding.buttonLabel, textAppearance)
        updateDrawableColor(context.getColorFromTextAppearance(textAppearance))
    }


    private fun updateDrawableColor(color: Int) {
        binding.buttonLabel.compoundDrawables.forEach {
            it?.setTint(color)
        }
    }

    override fun updateText(text: String) {
        binding.buttonLabel.text = text
    }

    private fun handleDirectionArrow() {
        when (directionArrow) {
            None -> directNone()
            Left -> directLeft()
            Right -> directRight()
        }
    }

    private fun directNone() {
        //remove all drawables
        binding.buttonLabel.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
    }

    private fun directLeft() {
        //sets left drawable only
        binding.buttonLabel.setCompoundDrawablesWithIntrinsicBounds(
            ContextCompat.getDrawable(context, R.drawable.bg_arrow_left_inset),
            null,
            null,
            null
        )
    }

    private fun directRight() {
        //sets right drawable only
        binding.buttonLabel.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(context, R.drawable.bg_arrow_right_inset),
            null
        )
    }

    /**
     * Enum class which is for Button arrow direction.
     *
     * @property None removes all arrows. Just to show only text.
     * @property Left applies an arrow left from the text.
     * @property Right applies an arrow right from the text.
     */
    enum class ArrowDirection {
        None,
        Left,
        Right
    }
}