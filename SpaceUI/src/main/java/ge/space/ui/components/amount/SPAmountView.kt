package ge.space.ui.components.amount

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpAmountLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setTextStyle

class SPAmountView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPAmountViewBase
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy { SpAmountLayoutBinding.inflate(LayoutInflater.from(context), this, true) }

    /**
     * Sets a component title.
     */
    var titleText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.titleTV.text = titleText
        }

    /**
     * Sets a description title.
     */
    var descText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.descriptionText.text = value
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = SPBaseView.DEFAULT_INT

    /**
     * Sets a description text appearance
     */
    @StyleRes
    private var descriptionTextAppearance: Int = SPBaseView.DEFAULT_INT

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPAmountView,
            defStyleAttr,
            defStyleRes
        ) {
            applyAmountStyledAttrs()
        }
    }

    private fun TypedArray.applyAmountStyledAttrs() {

        getResourceId(R.styleable.SPAmountView_titleTextAppearance, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { textAppearance = it }

        getResourceId(R.styleable.SPAmountView_descriptionTextAppearance, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { descriptionTextAppearance = it }

        getString(R.styleable.SPAmountView_title).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { titleText = it }

        getString(R.styleable.SPAmountView_descriptionText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { descText = it }


        updateTextAppearance(textAppearance)
    }

    /**
     * Sets title and description text appearance
     */
    fun updateTextAppearance(
        textAppearance: Int,
        descAppearance: Int = descriptionTextAppearance
    ) {
        binding.titleTV.setTextStyle(textAppearance)
        binding.descriptionText.setTextStyle(descAppearance)
    }
    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPAmountView
        ) {
            applyAmountStyledAttrs()
        }
    }
}