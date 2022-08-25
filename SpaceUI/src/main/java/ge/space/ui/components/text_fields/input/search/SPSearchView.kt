package ge.space.ui.components.text_fields.input.search

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpSearchViewMotionLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.controls.SPToggleIcon
import ge.space.ui.util.extension.*

/**
 * SPSearchView view extended from [ConstraintLayout] generic that allows to change its configuration.
 * There are 4 realized styles which can be applied to the view:
 *
 * <p>
 *     1. SPSearchViewDefault
 *     3. SPSearchView.Elevated - with box shadow
 *     4. SPSearchView.Filtered - without cancel button but with filter button
 * <p>
 *
 * @property cancelButtonClickListener [(() -> Unit)] listener calls after cancel button was clicked
 * @property onSearchClickListener [((String) -> Unit)] on text change listener
 * @property focusChangeListener [(() -> Unit)] on focus change listener
 * @property titleTextAppearance [Int] sets a text appearance.
 * @property searchBoxStyle [Int] sets a search box shadow style.
 * @property showSettingButton [Boolean] sets a visibility for setting toggle button
 * @property showCancelButton [Boolean] sets a visibility for cancel button
 */

class SPSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPSearchViewDefault,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    /**
     * Listener calls after cancel button was clicked
     */
    var cancelButtonClickListener: (() -> Unit) = {}

    /**
     * On text change listener
     */
    var onSearchClickListener: ((String) -> Unit) = {}

    /**
     * Calls when view is focused
     */
    var focusChangeListener: (() -> Unit) = {}

    /**
     * Listener calls after setting button was clicked
     */
    var settingClickListener: (Boolean) -> Unit = {}

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
     * Sets a search box shadow style.
     */
    @StyleRes
    var searchBoxStyle: Int = R.style.SPSearchBoxDefault
        set(value) {
            field = value

            binding.searchView.setBaseViewStyle(searchBoxStyle)
        }

    /**
     * Sets a visibility for setting toggle button
     */
    var showSettingButton = false
        set(value) {
            field = value

            setupSettingButton()
        }

    /**
     * Sets a visibility for cancel button
     */
    var showCancelButton = false
        set(value) {
            field = value

            setupCancelButton()
        }

    /**
     * Sets a visibility for clear button
     */
    var showClearButton = false
        set(value) {
            field = value

            setupClearButton()
        }

    private val binding =
        SpSearchViewMotionLayoutBinding.inflate(LayoutInflater.from(context), this, true)

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
        getString(R.styleable.SPSearchView_android_text).orEmpty()
            .apply { binding.searchInputView.setText(this) }

        getResourceId(R.styleable.SPSearchView_textAppearance, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { titleTextAppearance = it }

        getResourceId(R.styleable.SPSearchView_searchBoxStyle, R.style.shadow_empty)
            .handleAttributeAction(R.style.shadow_empty) { searchBoxStyle = it }

        getBoolean(R.styleable.SPSearchView_showCancelButton, false)
            .apply { showCancelButton = this }

        getBoolean(R.styleable.SPSearchView_showClearButton, false)
            .apply { showClearButton = this }

        getBoolean(R.styleable.SPSearchView_showSettingButton, false)
            .apply { showSettingButton = this }
    }

    /**
     * request focus
     */
    fun focus() {
        binding.searchInputView.requestFocus()
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(binding.searchInputView, InputMethodManager.SHOW_IMPLICIT)
    }

    /**
     * Sets a textAppearance and descriptionTextAppearance to view
     */
    fun updateTextAppearance(textAppearance: Int) =
        binding.searchInputView.setTextStyle(textAppearance)

    /**
     * Allows to update search view style and BaseViewStyle programmatically
     */
    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPSearchView
        ) {
            applyStyledAttributes()
        }
    }

    /**
     * remove focus
     */
    fun clearEditTextFocus() {
        if (binding.cancelBtn.isVisible) {
            cancelAnimation()
        }
    }

    /**
     * Setup cancel button click and focus change
     */
    private fun setupCancelButton() {
        with(binding) {
            searchInputView.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    handleViewTransaction()
                    focusChangeListener()
                }
            }
            cancelBtn.onClick {
                cancelAnimation()
            }
        }
    }

    private fun SpSearchViewMotionLayoutBinding.handleViewTransaction() {
        if (!cancelBtn.isVisible && showCancelButton) {
            searchViewRoot.setTransition(R.id.startState, R.id.endStateWithCancel)
        } else if (!showCancelButton && searchViewRoot.currentState == R.id.endState) {
            searchViewRoot.setTransition(R.id.startState, R.id.endState)
        }

        searchViewRoot.transitionToEnd()
    }

    /**
     * Cancel animation and return to start position, remove focus from input field and hide keyboard
     */
    private fun cancelAnimation() =
        with(binding) {
            searchInputView.setText(EMPTY_TEXT)
            searchViewRoot.transitionToStart()
            cancelButtonClickListener()
            clearFocus()
            hideKeyboard()
        }

    /**
     * Show clear button if showClearButton is true and input isn't empty
     */
    private fun setupClearButton() =
        with(binding) {
            searchInputView.onChange {
                clearImage.visibleIf(showClearButton && it.isNotEmpty())
                onSearchClickListener(it)
            }

            clearImageContainer.onClick {
                clearInput()
                focus()
            }
        }

    /**
     * Clear entered text and call onSearchClickListener
     */
    private fun clearInput() =
        with(binding) {
            searchInputView.text.clear()
            onSearchClickListener(EMPTY_TEXT)
        }

    /**
     * Show setting button if showSettingButton is true and init click listener on it
     */
    private fun setupSettingButton() {
        if (showSettingButton) {
            binding.toggleSettingsStub.inflate().apply {
                (this as SPToggleIcon).addOnCheckedChangeListener { _, isChecked ->
                    settingClickListener(isChecked)
                }
            }
        }
    }
}