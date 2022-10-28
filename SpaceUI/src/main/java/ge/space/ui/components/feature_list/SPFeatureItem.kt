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
import ge.space.ui.components.buttons.SPButton.IconDirection
import ge.space.ui.components.feature_list.SPFeatureItem.Orientation.Horizontal
import ge.space.ui.components.feature_list.SPFeatureItem.Orientation.Vertical
import ge.space.ui.util.extension.*

/**
 * SPFeatureItem view extended from ConstraintLayout generic that allows to change its configuration.
 * There are 3 realized styles which can be applied to the view:
 *
 * <p>
 *     1. SPFeatureItem
 *     2. SPFeatureItem.Title
 *     3. SPFeatureItem.Success
 * <p>
 *
 *
 * @property titleImage [Int] value which applies a button image using a resource ID.
 * @property text [String] sets a component title.
 * @property description [String] sets a component description.
 * @property isZebraEffect [Boolean] sets light or dark background.
 * @property orientation [Orientation] sets a description text position.
 *  This property can have a value from [Orientation.Horizontal] or  [Orientation.Vertical],
 */
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
     * Sets a component description.
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
    var titleImage: Int = 0
        set(value) {
            field = value
            binding.imageView.show()
            binding.imageView.setImageResource(titleImage)
        }

    /**
     * Sets light or dark background.
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
     * Sets a description text position.
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
     * Sets a description appearance
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
                titleImage = it
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
     * Enum class which is for description text position
     *
     * @property Vertical applies a description text under title view.
     * @property Horizontal applies a description text on the right of title view.
     */
    enum class Orientation {
        Vertical,
        Horizontal
    }
}
