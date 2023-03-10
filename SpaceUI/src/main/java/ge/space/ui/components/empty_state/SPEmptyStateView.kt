package ge.space.ui.components.empty_state

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpEmptyStateLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.*

class SPEmptyStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPEmptyStateBase
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy { SpEmptyStateLayoutBinding.inflate(LayoutInflater.from(context), this, true) }

    /**
     * Sets a image resource
     */
    @IdRes
    var src = SPBaseView.DEFAULT_INT
        set(value) {
            field = value

            binding.imageView.setImageResource(src)
        }

    /**
     * Sets a component title.
     */
    var titleText: String = EMPTY_TEXT
        set(value) {
            field = value

            handleTitleText(value)
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
     * Sets a button title.
     */
    var buttonText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.buttonView.text = value
        }

    /**
     * Set true if button should have been shown.
     */
    var buttonVisibility: Boolean = false
        set(value) {
            field = value

            binding.buttonView.isVisible = value
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
            R.styleable.SPEmptyStyleView,
            defStyleAttr,
            defStyleRes
        ) {
            applyEmptyStateStyledAttrs()
        }
    }

    private fun TypedArray.applyEmptyStateStyledAttrs() {
        getResourceId(R.styleable.SPEmptyStyleView_android_src, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { src = it }

        getResourceId(R.styleable.SPEmptyStyleView_titleTextAppearance, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { textAppearance = it }

        getResourceId(R.styleable.SPEmptyStyleView_descriptionTextAppearance, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { descriptionTextAppearance = it }

        getString(R.styleable.SPEmptyStyleView_title).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { titleText = it }

        getString(R.styleable.SPEmptyStyleView_descriptionText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { descText = it }

        getString(R.styleable.SPEmptyStyleView_buttonText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { buttonText = it }

        getBoolean(R.styleable.SPEmptyStyleView_buttonVisibility, false)
            .handleAttributeAction(false) { buttonVisibility = it }

        updateTextAppearance(textAppearance)
    }

    /**
     * Sets a listener to button
     */
    fun setOnButtonClickListener(listener: () -> Unit) {
        binding.buttonView.onClick { listener() }
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

    private fun handleTitleText(text: String) {
        binding.titleTV.text = text
        binding.titleTV.isVisible = text.isNotEmpty()
    }

    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPEmptyStyleView
        ) {
            applyEmptyStateStyledAttrs()
        }
    }
}