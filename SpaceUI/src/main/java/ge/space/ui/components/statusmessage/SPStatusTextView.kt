package ge.space.ui.components.statusmessage

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import com.google.android.material.textview.MaterialTextView
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.base.SPSetViewStyleInterface
import ge.space.ui.util.extension.getColorFromTextAppearance
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setCompoundDrawablesTint

class SPStatusTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPStatusTextView_Info
) : MaterialTextView(context, attrs, defStyleAttr, defStyleRes), SPSetViewStyleInterface {

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPStatusTextView,
            defStyleAttr
        ) {
            setViewStyle(
                getResourceId(
                    R.styleable.SPStatusTextView_style,
                    defStyleRes
                )
            )

            withStyledAttributes()
        }
    }

    override fun setViewStyle(newStyle: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(newStyle, R.styleable.SPStatusTextView)

        styleAttrs.run {
            withStyledAttributes()
            recycle()
        }
    }

    private fun TypedArray.withStyledAttributes() {
        getResourceId(R.styleable.SPStatusTextView_android_src, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                text = resources.getResourceEntryName(it)
                setCompoundDrawablesWithIntrinsicBounds(
                    it,
                    DEFAULT_OBTAIN_VAL,
                    DEFAULT_OBTAIN_VAL,
                    DEFAULT_OBTAIN_VAL
                )
            }

        val textAppearance =
            getResourceId(R.styleable.SPStatusTextView_android_textAppearance, DEFAULT_OBTAIN_VAL)
        updateTextAppearance(textAppearance)
    }

    fun updateTextAppearance(textAppearance: Int) {
        setTextStyle(textAppearance)
        setCompoundDrawablesTint(context.getColorFromTextAppearance(textAppearance))
    }
}