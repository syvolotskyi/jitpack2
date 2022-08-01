package ge.space.ui.components.text_fields.input.search

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ge.space.ui.util.animations.motion.awaitTransitionComplete
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpSearchViewMotionLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * SPSearchView view extended from [ConstraintLayout] generic that allows to change its configuration.
 * There are 4 realized styles which can be applied to the view:
 *
 * <p>
 *     1. SPSearchViewDefault
 *     2. SPSearchView.Giphy - without cancel button
 *     3. SPSearchView.Elevated - with box shadow
 *     4. SPSearchView.Filtered - without cancel button but with filter button
 * <p>
 *
 * @property cancelButtonClickListener [(() -> Unit)] listener calls after cancel button was clicked
 * @property onSearchClickListener [((String) -> Unit)] on text change listener
 * @property focusChangeListener [(() -> Unit)] on focus change listener
 * @property titleTextAppearance [Int] sets a text appearance.
 * @property shadowStyle [Int] sets a search box shadow style.
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
     * On focus change listener
     */
    var focusChangeListener: (() -> Unit) = {}

    /**
     * Listener calls after setting button was clicked
     */
    var settingClickListener: () -> Unit = {}

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
    var shadowStyle: Int = R.style.shadow_empty
        set(value) {
            field = value

            binding.templateSearchView.setBaseViewShadowStyle(shadowStyle)
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

    private val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
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

        getResourceId(R.styleable.SPSearchView_titleTextAppearance, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { titleTextAppearance = it }

        getResourceId(R.styleable.SPSearchView_shadowStyle, R.style.shadow_empty)
            .handleAttributeAction(R.style.shadow_empty) { shadowStyle = it }

        getBoolean(R.styleable.SPSearchView_showCancelButton, false)
            .apply { showCancelButton = this }

        getBoolean(R.styleable.SPSearchView_showClearButton, false)
            .apply { showClearButton = this }

        getBoolean(R.styleable.SPSearchView_showSettingButton, false)
            .apply { showSettingButton = this }
    }

    /**
     * remove focus
     */
    fun clearEditTextFocus() =
        with(binding) {
            if (transferCancel.isVisible) {
                cancelAnimation()
            }
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

    private fun setupCancelButton() {
        with(binding) {
            searchInputView.onClick {
                focusChangeListener.invoke()
            }
            searchInputView.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    handleViewTransaction()
                }
            }
            transferCancel.onClick {
                cancelAnimation()
            }
        }
    }

    private fun SpSearchViewMotionLayoutBinding.handleViewTransaction() {
        if (!transferCancel.isVisible && showCancelButton) {
            searchViewRoot.setTransition(R.id.startState, R.id.endStateWithCancel)
        } else if (!showCancelButton && searchViewRoot.currentState == R.id.endState) {
            searchViewRoot.setTransition(R.id.startState, R.id.endState)
        }

        searchViewRoot.transitionToEnd()
    }

    private fun cancelAnimation() =
        with(binding) {
            uiScope.launch {
                searchInputView.setText(EMPTY_TEXT)
                searchViewRoot.transitionToStart()
                searchViewRoot.awaitTransitionComplete(R.id.startState)
            }
            cancelButtonClickListener()
            clearFocus()
            hideKeyboard()
        }


    private fun setupClearButton() =
        with(binding) {
            searchInputView.onChange {
                clearImage.visibleIf(showClearButton && it.isNotEmpty())
                onSearchClickListener.invoke(it)
            }

            clearImageContainer.onClick {
                clearInput()
                focus()
            }
        }

    private fun clearInput() =
        with(binding) {
            searchInputView.text.clear()
            onSearchClickListener("")
        }

    private fun setupSettingButton() {
        binding.toggleSettings.onClick {
            settingClickListener()
        }
        binding.toggleSettings.isVisible = showSettingButton
    }
}