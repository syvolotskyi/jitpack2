package ge.space.ui.components.statusmessage

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.google.android.material.textview.MaterialTextView
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.base.SPSetViewStyleInterface
import ge.space.ui.util.extension.getColorFromTextAppearance

class SPStatusTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPStatusMessage_Info
) : MaterialTextView(context, attrs, defStyleAttr), SPSetViewStyleInterface {
    
    var status : SPMessageStatus = SPMessageStatus.INFO
        set(value) {
            field = value
            updateTextAppearance(value.textAppearance)
            setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, value.icon),
                null, null, null)
        }

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
        }
    }

    override fun setViewStyle(newStyle: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(newStyle, R.styleable.SPStatusTextView)

        styleAttrs.run { withStyledAttributes() }
    }

    private fun TypedArray.withStyledAttributes() {
        val statusId = getInt(R.styleable.SPStatusTextView_status, DEFAULT_OBTAIN_VAL)
        status = SPMessageStatus.values()[statusId]

        val textAppearance = getResourceId(R.styleable.SPStatusTextView_android_textAppearance, DEFAULT_OBTAIN_VAL)
        updateTextAppearance(textAppearance)
    }

    fun updateTextAppearance(textAppearance: Int) {
        setTextStyle(textAppearance)
        updateDrawableColor(context.getColorFromTextAppearance(textAppearance))
    }

    private fun updateDrawableColor(color: Int) {
        compoundDrawables.forEach {
            it?.colorFilter = PorterDuffColorFilter(
                color,
                PorterDuff.Mode.SRC_IN
            )
        }
    }
}