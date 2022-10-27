package ge.space.ui.components.feature_list

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpFeatureItemLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.feature_list.SPFeatureItem.Orientation.Horizontal
import ge.space.ui.components.feature_list.SPFeatureItem.Orientation.Vertical
import ge.space.ui.util.extension.*

class SPFeatureItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPFeatureItem
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {
    private val binding by lazy {
        SpFeatureItemLayoutBinding.inflate(LayoutInflater.from(context), this, true)
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

            binding.descTV.isVisible = value.isNotEmpty()
            binding.descTV.text = value
        }

    /**
     * Sets a title image
     */
    @DrawableRes
    var image: Int = 0
        set(value) {
            field = value
            binding.imageView.show()
            binding.imageView.setImageResource(image)
        }

    /**
     * Sets a component title.
     */
    var isZebraEffect: Boolean = false
        set(value) {
            field = value
          setBackgroundColor(getBackgroundColor())
        }

    private fun getBackgroundColor() =
        if (isZebraEffect) context.getColorFromAttribute(R.attr.background_secondary)
        else context.getColorFromAttribute(R.attr.background_primary)

    /**
     * Sets a arrow direction.
     */
    var orientation: Orientation = Vertical
        set(value) {
            field = value

            if (orientation == Horizontal)
                ConstraintSet().apply {
                    clone(context, R.layout.sp_feature_list_layout_horizontal)
                    binding.descTV.gravity = Gravity.END
                    applyTo(binding.root)
                }
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

        val orientationIndex = getInt(
            R.styleable.SPFeatureList_orientation,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )

        orientation = Orientation.values()[orientationIndex]

        text = getString(R.styleable.SPFeatureList_title).orEmpty()

        getResourceId(R.styleable.SPFeatureList_android_src, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
                image = it
            }

        getResourceId(
            R.styleable.SPFeatureList_titleTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            textAppearance = it
        }

        description = getString(R.styleable.SPFeatureList_descriptionText).orEmpty()

        getResourceId(
            R.styleable.SPFeatureList_descriptionTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            descTextAppearance = it
        }

        isZebraEffect = getBoolean(R.styleable.SPFeatureList_isZebraEffect, false)
    }

    /**
     * Sets title text appearance
     */
   private fun updateTextAppearance() {
        binding.titleTV.setTextStyle(textAppearance)
        binding.descTV.setTextStyle(descTextAppearance)
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
        Horizontal
    }
}
