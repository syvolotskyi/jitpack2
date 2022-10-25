package ge.space.ui.components.feature_list

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpFeatureListLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.tooltips.SPTooltipView
import ge.space.ui.components.tooltips.SPTooltipView.ArrowDirection.*
import ge.space.ui.util.DisposableTask
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setTextStyle
import ge.space.ui.util.path.SPMaskPath
import ge.space.ui.util.path.SPMaskPathRoundedCorners

class SPFeatureList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPFeatureList
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {
    private val binding by lazy {
        SpFeatureListLayoutBinding.inflate(LayoutInflater.from(context), this, true)
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
     * Sets a component title.
     */
    var description: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.descTV.text = value
        }

    /**
     * Sets a arrow direction.
     */
    var orientation: Orientation = Orientation.Vertical
        set(value) {
            field = value

          if (orientation == Orientation.Horizontal)

              binding.root.setConstraintSet( ConstraintSet().apply {  })
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = 0
        set(value) {
            field = value
            updateTextAppearance()
        }


    /**
     * Sets a text appearance
     */
    @StyleRes
    private var descTextAppearance: Int = 0
        set(value) {
            field = value
            updateTextAppearance()
        }


    init {

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPFeatureList,
            defStyleAttr,
            defStyleRes
        ) {
            applyTooltipStyledAttrs()
        }
    }

    private fun TypedArray.applyTooltipStyledAttrs() {
        text = getString(R.styleable.SPFeatureList_title).orEmpty()

        getResourceId(
            R.styleable.SPFeatureList_titleTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            textAppearance = it
        }

        description = getString(R.styleable.SPFeatureList_title).orEmpty()

        getResourceId(
            R.styleable.SPFeatureList_descriptionTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            descTextAppearance = it
        }

     /*   getColor(
            R.styleable.SPFeatureList_backgroundColor,
            context.getColorFromAttribute(R.attr.background_secondary)
        ).handleAttributeAction(context.getColorFromAttribute(R.attr.background_secondary)) {
            binding.root.setBackgroundColor(
                it
            )
        }*/

    }


    /**
     * Sets title text appearance
     */

    fun updateTextAppearance() {
        binding.titleTV.setTextStyle(textAppearance)
        binding.descTV.setTextStyle(textAppearance)
    }


    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPFeatureList
        ) {
            applyTooltipStyledAttrs()
        }
    }

    /**
     * Enum class which is for arrow direction.
     *
     * @property Vertical applies a box without an arrow.
     * @property Horizontal applies an arrow top center from the view
     */
    enum class Orientation {
        Vertical,
        Horizontal}
}
