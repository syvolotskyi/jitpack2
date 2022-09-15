package ge.space.ui.components.text_view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import com.google.android.material.textview.MaterialTextView
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.getColorFromTextAppearance
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setCompoundDrawablesTint
import ge.space.ui.util.extension.setTextStyle


/**
 * Custom MaterialTextView currently used for displaying status messages and tags,
 * 4 predefined styles for status messages: Success, Error, Pending and Info
 * Predefined styles for tags: SPTextView.Tag, Error, Pending and Info
 */
class SPTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextView_Info
) : MaterialTextView(context, attrs, defStyleAttr), SPViewStyling {

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextView,
            defStyleAttr,
            defStyleRes
        ) {
            withStyledAttributes()
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.withStyledAttributes(newStyle, R.styleable.SPTextView) {
            withStyledAttributes()
            setViewPaddings()
        }
    }

    private fun TypedArray.withStyledAttributes() {
        getResourceId(R.styleable.SPTextView_android_src, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                setCompoundDrawablesWithIntrinsicBounds(
                    it,
                    DEFAULT_OBTAIN_VAL,
                    DEFAULT_OBTAIN_VAL,
                    DEFAULT_OBTAIN_VAL
                )
            }

        getResourceId(R.styleable.SPTextView_android_drawablePadding, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                compoundDrawablePadding = resources.getDimensionPixelSize(it)
            }

        getResourceId(R.styleable.SPTextView_android_textAppearance, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                updateTextAppearance(it)
            }
    }

    /**
     * Adds paddings to view when setting the style
     */
    private fun TypedArray.setViewPaddings() {
        var vertical = DEFAULT_OBTAIN_VAL
        var horizontal = DEFAULT_OBTAIN_VAL

        getResourceId(R.styleable.SPTextView_paddingVertical, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                vertical = resources.getDimensionPixelSize(it)
            }
        getResourceId(R.styleable.SPTextView_paddingHorizontal, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                horizontal = resources.getDimensionPixelSize(it)
            }

        setPaddingRelative(horizontal, vertical, horizontal, vertical)
    }

    fun updateTextAppearance(textAppearance: Int) {
        setTextStyle(textAppearance)
        setCompoundDrawablesTint(context.getColorFromTextAppearance(textAppearance))
    }
}