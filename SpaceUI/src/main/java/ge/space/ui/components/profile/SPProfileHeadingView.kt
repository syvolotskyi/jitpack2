package ge.space.ui.components.profile

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
import ge.space.spaceui.databinding.SpProfileHeadingLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_INT
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setTextStyle
import ge.space.ui.util.view_factory.SPViewData

class SPProfileHeadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPProfileHeading
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy {
        SpProfileHeadingLayoutBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    /**
     * Sets a image resource
     */
    @IdRes
    var src = 0
        set(value) {
            field = value

            binding.button.src = src
        }

    /**
     * Sets a image resource
     */
    @IdRes
    var defaultMarkImage = 0
        set(value) {
            field = value

            binding.mark.setViewData(SPViewData.SPImageResourcesData(defaultMarkImage))
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
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = DEFAULT_INT

    /**
     * Sets a description text appearance
     */
    @StyleRes
    private var descriptionTextAppearance: Int = DEFAULT_INT


    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPProfileHeadingView,
            defStyleAttr
        ) {
            applyAttrs()
        }
    }


    private fun TypedArray.applyAttrs() {
        getString(R.styleable.SPProfileHeadingView_titleText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { titleText = it }

        getString(R.styleable.SPProfileHeadingView_descriptionText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { descText = it }

        textAppearance = getResourceId(
            R.styleable.SPProfileHeadingView_titleTextAppearance,
            DEFAULT_OBTAIN_VAL
        )

        descriptionTextAppearance = getResourceId(
            R.styleable.SPProfileHeadingView_descriptionTextAppearance,
            DEFAULT_OBTAIN_VAL
        )
        src = getResourceId(
            R.styleable.SPProfileHeadingView_icon,
            R.drawable.ic_plus_16_regular
        )
        defaultMarkImage = getResourceId(
            R.styleable.SPProfileHeadingView_defaultProfileImage,
            0
        )

        updateTextAppearance(textAppearance, descriptionTextAppearance)
    }


    private fun handleTitleText(text: String) {
        binding.titleTV.text = text
        binding.titleTV.isVisible = text.isNotEmpty()
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


    fun setViewData(viewData: SPViewData) =
        binding.mark.setViewData(viewData)


    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(newStyle, R.styleable.SPProfileHeadingView) {
            applyAttrs()
        }
    }

}