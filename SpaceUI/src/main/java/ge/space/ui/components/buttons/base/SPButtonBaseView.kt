package ge.space.ui.components.buttons.base

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.viewbinding.ViewBinding
import ge.space.ui.base.OnDistractiveInterface
import ge.space.ui.base.SPBaseView
import ge.space.ui.util.extension.SPSetViewStyleInterface

/**
 * Abstract base Button view extended from [SPBaseView] that allows to change its configuration.
 * It has to be extended to apply styled properties.
 *
 * @property text [String] value which applies a button label text
 */
abstract class SPButtonBaseView<VB : ViewBinding> @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr), SPSetViewStyleInterface, OnDistractiveInterface {

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
     * Sets a button title.
     */
    var text: String = EMPTY_TEXT
        set(value) {
            field = value

            updateText(value)
        }

    override var isDistractive: Boolean = false
        set(value) {
            field = value

            handleDistractiveState()
        }

    init {
        binding = _binding
    }

    override fun setViewStyle(newStyle: Int) {
        with(newStyle) {
            setStyle(this)
            setButtonStyle(this)
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        alpha = if (enabled) {
            DEFAULT_ALPHA
        } else {
            DISABLED_ALPHA
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled)
            return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                alpha = CLICKED_ALPHA
                return true
            }
            MotionEvent.ACTION_UP -> {
                alpha = DEFAULT_ALPHA
                callOnClick()
                return false
            }
            MotionEvent.ACTION_CANCEL -> alpha = DEFAULT_ALPHA
        }
        return false
    }

    protected abstract fun updateText(text: String)

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB

    /**
     * Update view depends on isDistractive attr
     */
    abstract fun handleDistractiveState()

    /**
     * Allows to update button style using ViewBinding
     */
    abstract fun setButtonStyle(@StyleRes defStyleRes: Int)

    companion object {
        private const val DEFAULT_ALPHA = 1f
        private const val CLICKED_ALPHA = 0.8f
        private const val DISABLED_ALPHA = 0.25f
    }
}