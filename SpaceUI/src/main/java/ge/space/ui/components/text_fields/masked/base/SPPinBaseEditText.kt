package ge.space.ui.components.text_fields.masked.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.viewbinding.ViewBinding
import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.handleAttributeAction


/**
 * Field view extended from [SPBaseView] that allows to change its configuration.
 *
 * @property text [String] value which sets a text.
 * @property labelText [String] value which sets a label text.
 * @property descriptionText [String] value which sets a description text.
 * @property maxLength [Int] value which sets a count of symbols.
 */
abstract class SPPinBaseEditText<VB : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr), SPViewStyling {

    /**
     * Reference to [VB] instance which is related to ViewBinding
     */
    protected val binding: VB

    /**
     * Lazy property for initialize ViewBinding in constructor
     */
    private val _binding by lazy {
        getViewBinding()
    }

    /**
     * Sets a text
     */
    var text: String = EMPTY_TEXT
        set(value) {
            field = value

            updateText(value)
        }

    /**
     * Sets a labelText
     */
    var labelText: String = EMPTY_TEXT
        set(value) {
            field = value

            updateLabel(value)
        }

    /**
     * Sets a labelText
     */
    var descriptionText: String = EMPTY_TEXT
        set(value) {
            field = value

            updateDescription(value)
        }

    /**
     * Sets a error
     */
    var state: SPPinState = SPPinState.DEFAULT
        set(state) {
            field = state
            handleState()
        }

    /**
     * Sets a maxLength
     */
    var maxLength: Int = DEFAULT_LENGTH
        set(value) {
            field = value

            setMaxLength()
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    protected var labelTextAppearance: Int = R.style.h700_medium_caps_label_secondary

    /**
     * Sets a description text appearance
     */
    @StyleRes
    protected var descriptionTextAppearance: Int = R.style.h700_bold_caps_brand_primary

    /**
     * Sets a  counter text appearance
     */
    @StyleRes
    protected var counterTextAppearance: Int = R.style.h700_bold_magenta


    protected val errorAnimation: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.sp_shake_anim
        ).apply {
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    resetPin()
                }
            })
        }
    }

    init {
        binding = _binding
    }

    /**
     * Listener of description label click
     */
    abstract fun setOnDescriptionClickListener(listener: () -> Unit)

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB

    /**
     * Update a main text
     */
    protected abstract fun updateText(text: String)

    /**
     * Update a label text
     */
    protected abstract fun updateLabel(text: String)

    /**
     * Update a Description text
     */
    protected abstract fun updateDescription(text: String)

    /**
     * handle state - Default, Succesfull or Error
     */
    protected abstract fun handleState()

    /**
     * set max Length to main input
     */
    protected abstract fun setMaxLength()

    /**
     * Request focus on this PinEntryEditText
     */
    abstract fun focus()

    /**
     * Clean previously set password
     */
    abstract fun resetPin()

    /**
     * Update all text Appearances
     */
    abstract fun updateTextAppearances(
        @StyleRes labelAppearance: Int = labelTextAppearance,
        @StyleRes descAppearance: Int = descriptionTextAppearance
    )

    override fun setViewStyle(newStyle: Int) {
        with(newStyle) {
            setStyle(this)
            setPinStyle(this)
        }
    }

    private fun setPinStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPPinEditText)

        styleAttrs.run {
            withStyledResource()
        }
    }

    fun TypedArray.withStyledResource() {
        text = getString(R.styleable.SPPinEditText_android_text).orEmpty()
        getString(R.styleable.SPPinEditText_pinLabelText).handleAttributeAction(
            EMPTY_TEXT
        ) {
            it?.let { labelText = it }
        }
        maxLength = getInt(
            R.styleable.SPPinEditText_android_maxLength,
            DEFAULT_LENGTH
        )
        getString(R.styleable.SPPinEditText_pinDescriptionText).handleAttributeAction(
            EMPTY_TEXT
        ) {
            it?.let { descriptionText = it }
        }
        getResourceId(
            R.styleable.SPPinEditText_descriptionTextAppearance,
            DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(
            DEFAULT_OBTAIN_VAL
        ) {
            if (it != DEFAULT_OBTAIN_VAL) descriptionTextAppearance = it
        }
        getResourceId(
            R.styleable.SPPinEditText_labelTextAppearance,
            R.style.h700_medium_caps_label_secondary
        ).handleAttributeAction(
            R.style.h700_medium_caps_label_secondary
        ) {
            if (it != DEFAULT_OBTAIN_VAL) labelTextAppearance = it
        }
        updateTextAppearances(labelTextAppearance, descriptionTextAppearance)
        handleState()
    }

    companion object {
        const val DEFAULT_LENGTH = 4
    }
}