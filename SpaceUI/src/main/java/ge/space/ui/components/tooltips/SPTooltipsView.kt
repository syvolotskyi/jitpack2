package ge.space.ui.components.tooltips

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTooltipLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.*

class SPTooltipsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTooltipBase
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy {
        SpTooltipLayoutBinding.inflate(LayoutInflater.from(context), this,true)
    }

    /**
     * Sets a component title.
     */
    var text: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.titleTV.text = value
        }

    /**
     * Sets a arrow direction.
     */
    var arrowDirection: ArrowDirection = ArrowDirection.None
        set(value) {
            field = value
            binding.titleTV.setBackgroundResource(getBackgroundDrawable().apply {
                PorterDuffColorFilter(
                    backgroundBoxColor,
                    PorterDuff.Mode.LIGHTEN
                )
            })

        }

    /**
     * Sets a box background color.
     */
    private var backgroundBoxColor: Int = 0

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = R.style.h700_bold_caps_brand_primary

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTooltipsView,
            defStyleAttr,
            defStyleRes
        ) {
            applyTooltipStyledAttrs()
        }
    }

    private fun TypedArray.applyTooltipStyledAttrs() {
        text = getString(R.styleable.SPTooltipsView_android_text).orEmpty()

        getResourceId(
            R.styleable.SPTooltipsView_android_textAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            textAppearance = it
        }

        getResourceId(
            R.styleable.SPTooltipsView_backgroundBoxColor,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { backgroundBoxColor = it }

        val direction = getInt(
            R.styleable.SPTooltipsView_directionArrow,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )

        arrowDirection = ArrowDirection.values()[direction]
    }

    /**
     * Sets title text appearance
     */
    fun updateTextAppearance() =
        binding.titleTV.setTextStyle(textAppearance)

    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPEmptyStyleView
        ) {
            applyTooltipStyledAttrs()
        }
    }

    private fun getBackgroundDrawable() =
        when (arrowDirection) {
            ArrowDirection.None -> R.drawable.bg_tooltip_
            ArrowDirection.TopLeft -> R.drawable.bg_tooltip_top_left
            ArrowDirection.TopRight -> R.drawable.bg_tooltip_top_right
            ArrowDirection.Left -> R.drawable.bg_tooltip_left
            ArrowDirection.Right -> R.drawable.bg_tooltip_right
            ArrowDirection.BottomLeft -> R.drawable.bg_tooltip_bottom_left
            ArrowDirection.BottomCenter -> R.drawable.bg_tooltip_bottom_center
            ArrowDirection.BottomRight -> R.drawable.bg_tooltip_bottom_right
            else -> R.drawable.bg_tooltip_
        }

    /**
     * Enum class which is for arrow direction.
     *
     * @property None applies a box without an arrow.
     * @property TopCenter applies an arrow top center from the view
     * @property TopLeft applies a box with an arrow top left from the view.
     * @property TopRight applies a box with an arrow top right from the view.
     * @property BottomCenter applies a box with an arrow bottom center from the view.
     * @property BottomLeft applies a box with an arrow bottom left from the view.
     * @property BottomRight applies a box with an arrow bottom right from the view.
     * @property Right applies a box with an arrow right from the view.
     * @property Left applies a box with an arrow left from the view.
     */
    enum class ArrowDirection {
        None,
        TopCenter,
        TopLeft,
        TopRight,
        BottomCenter,
        BottomLeft,
        BottomRight,
        Left,
        Right,
    }
}