package ge.space.ui.components.progress_navigator

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpProgressNavigotorItemLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.progress_navigator.SPProgressNavigatorItem.ProgressState.*
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setTextStyle

/**
 * [SPProgressNavigatorItem] view extended from [LinearLayout, uses for creating steps to do.
 *
 * @param defaultText [String] is a text when the state is [NORMAL_STATE]
 * @param src [Int] is an icon whe the state is [NORMAL_STATE]
 * @param successText [String]  is a text when the state is [SUCCESS_STATE]
 * @param state [ProgressState]  is a current state
 */
class SPProgressNavigatorItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPProgressNavigator
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy {
        SpProgressNavigotorItemLayoutBinding.inflate(
            LayoutInflater.from(
                context
            ), this, true
        )
    }

    /**
     * Sets a image resource
     */
    @DrawableRes
    var src = SPBaseView.DEFAULT_INT
        set(value) {
            field = value

            binding.imageView.setImageResource(src)
        }

    /**
     * Sets a component title.
     */
    var defaultText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.titleText.text = defaultText
        }

    /**
     * Sets a successText.
     */
    var successText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.titleText.text = successText
        }


    var state = NORMAL_STATE
        set(value) {
            field = value

            updateState()
        }


    /**
     * Sets a defaulttext appearance
     */
    @StyleRes
    private var textAppearance: Int = SPBaseView.DEFAULT_INT

    /**
     * Sets a success text appearance
     */
    @StyleRes
    private var successTextAppearance: Int = SPBaseView.DEFAULT_INT


    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPProgressNavigator,
            defStyleAttr,
            defStyleRes
        ) {
            applyEmptyStateStyledAttrs()
        }
    }

    private fun TypedArray.applyEmptyStateStyledAttrs() {
        getResourceId(R.styleable.SPProgressNavigator_defaultIcon, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { src = it }

        getResourceId(R.styleable.SPProgressNavigator_textAppearance, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { textAppearance = it }
        getResourceId(
            R.styleable.SPProgressNavigator_successTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { successTextAppearance = it }

        getString(R.styleable.SPProgressNavigator_defaultText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { defaultText = it }

        getString(R.styleable.SPProgressNavigator_successText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { successText = it }

        val direction = getInt(
            R.styleable.SPProgressNavigator_state,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )

        state = values()[direction]
    }

    /**
     * Sets a navigation data
     */
    fun setupNavigationView(data: SPProgressNavigatorData) = with(data) {
        this@SPProgressNavigatorItem.src = iconSrc
        this@SPProgressNavigatorItem.defaultText = defaultText
        this@SPProgressNavigatorItem.successText = successText
        this@SPProgressNavigatorItem.state = state
    }

    /**
     * [updateState] changes ui state by current [ProgressState]
     */
    private fun updateState() = binding.apply {
        when (state) {
            NORMAL_STATE -> {
                titleText.text = defaultText
                imageView.setImageResource(src)
                titleText.setTextStyle(textAppearance)
                imageView.setColorFilter(context.getColorFromAttribute(R.attr.label_secondary))
                navigationFrame.color = context.getColorFromAttribute(R.attr.background_secondary)
            }
            SUCCESS_STATE -> {
                titleText.text = successText
                titleText.setTextStyle(successTextAppearance)
                imageView.setImageResource(R.drawable.ic_checkmark_24_regular)
                imageView.setColorFilter(context.getColorFromAttribute(R.attr.white))
                navigationFrame.color = context.getColorFromAttribute(R.attr.status_primary_success)
            }
        }
    }

    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPProgressNavigator
        ) {
            applyEmptyStateStyledAttrs()
        }
    }

    /**
     * Enum class which is for arrow direction.
     *
     * @property NORMAL_STATE is a default state.
     * @property SUCCESS_STATE is a succeed state
     */
    enum class ProgressState {
        NORMAL_STATE,
        SUCCESS_STATE,
    }
}