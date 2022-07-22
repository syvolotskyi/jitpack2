package ge.space.ui.components.text_fields.input.search

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpSearchViewLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.text_fields.input.base.SPEndViewType
import ge.space.ui.components.text_fields.input.base.setupEndViewByType
import ge.space.ui.components.text_fields.input.utils.extension.doOnTextChanged
import ge.space.ui.util.extension.*

class SPSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPSearchViewDefault,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    /**
     * Sets a text appearance.
     */
    @StyleRes
    var titleTextAppearance: Int = 0
        set(value) {
            field = value

            updateTextAppearance(value)
        }

    /**
     * Sets a visibility for setting toggle button
     */
    var showSettingButton = false
        set(value) {
            field = value

            binding.toggleSettings.isVisible = field
        }

    /**
     * Sets a visibility for cancel button
     */
    var showCancelButton = false

    /**
     * Sets a visibility for clear button
     */
    var showClearButton = false
    private var settingListener: () -> Unit = {}

    private val binding =
        SpSearchViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPSearchView,
            defStyleAttr,
            defStyleRes
        ) {
            applyStyledAttributes()
        }
    }

    private fun TypedArray.applyStyledAttributes() {
        getString(
            R.styleable.SPSearchView_android_text
        ).orEmpty().apply { binding.searchInputView.text = this }

        getResourceId(
            R.styleable.SPSearchView_titleTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            titleTextAppearance = it
        }

        getBoolean(
            R.styleable.SPSearchView_showCancelButton,
            false
        ).apply {
            showCancelButton = this
        }
        getBoolean(
            R.styleable.SPSearchView_showClearButton,
            false
        ).apply {
            showClearButton = this
        }
        getBoolean(
            R.styleable.SPSearchView_showSettingButton,
            false
        ).apply {
            showSettingButton = this
        }

        setupCancelButton()
        setupClearButton()
    }

    fun setSettingButtonCLickListener(listener: () -> Unit) {
        settingListener = listener
    }

    private fun setupCancelButton() {
        binding.cancelButton.onClick {
            binding.searchInputView.removeAllText()
            binding.searchInputView.clearFocus()
            binding.searchInputView.hideKeyboard()
        }
        binding.searchInputView.onFocusChangeListener = { focused ->
            binding.cancelButton.isVisible = focused && showCancelButton
        }
    }

    private fun setupClearButton() {
        binding.searchInputView.doOnTextChanged { text, _, _, _ ->
            binding.searchInputView.setupEndViewByType(
                if (!text.isNullOrEmpty() && showClearButton) SPEndViewType.SPRemovableViewType
                else SPEndViewType.SPNoneViewType
            )
        }
    }

    private fun setupSettingButton() {
        binding.toggleSettings.onClick {
            settingListener()
        }
        binding.toggleSettings.isVisible = showSettingButton
    }

    /**
     * Sets a textAppearance and descriptionTextAppearance to view
     */
    fun updateTextAppearance(textAppearance: Int) {
        binding.searchInputView.textAppearance = textAppearance
    }

    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPRadioButton
        ) {
            applyStyledAttributes()
        }
    }
}