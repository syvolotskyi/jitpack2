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

class SPTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextView_Info
) : MaterialTextView(context, attrs, defStyleAttr), SPSetViewStyleInterface {

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextView,
            defStyleAttr
        ) {
            setViewStyle(
                getResourceId(
                    R.styleable.SPTextView_style,
                    defStyleRes
                )
            )
            withStyledAttributes()
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(newStyle, R.styleable.SPTextView)

        styleAttrs.run {
            withStyledAttributes()
            recycle()
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

        // TextView paddings
        /*val start = resources.getDimensionPixelSize(
            getResourceId(R.styleable.SPTextView_android_paddingStart, DEFAULT_OBTAIN_VAL)
        )
        val top = resources.getDimensionPixelSize(
            getResourceId(R.styleable.SPTextView_android_paddingTop, DEFAULT_OBTAIN_VAL)
        )
        val end = resources.getDimensionPixelSize(
            getResourceId(R.styleable.SPTextView_android_paddingEnd, DEFAULT_OBTAIN_VAL)
        )

        val bottom = resources.getDimensionPixelSize(
            getResourceId(R.styleable.SPTextView_android_paddingBottom, DEFAULT_OBTAIN_VAL)
        )

        setPaddingRelative(start, top, end, bottom)*/

        var start = 0
        var top = 0
        var end = 0
        var bottom = 0

        getResourceId(R.styleable.SPTextView_android_paddingStart, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                start = resources.getDimensionPixelSize(it)
            }
        getResourceId(R.styleable.SPTextView_android_paddingTop, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                top = resources.getDimensionPixelSize(it)
            }
        getResourceId(R.styleable.SPTextView_android_paddingEnd, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                end = resources.getDimensionPixelSize(it)
            }
        getResourceId(R.styleable.SPTextView_android_paddingBottom, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                bottom = resources.getDimensionPixelSize(it)
            }

        setPaddingRelative(start, top, end, bottom)


        getResourceId(R.styleable.SPTextView_textAppearance, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                updateTextAppearance(it)
            }
    }

    fun updateTextAppearance(textAppearance: Int) {
        setTextStyle(textAppearance)
        setCompoundDrawablesTint(context.getColorFromTextAppearance(textAppearance))
    }



}