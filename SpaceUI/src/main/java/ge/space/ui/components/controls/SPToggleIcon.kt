package ge.space.ui.components.controls

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPSetViewStyleInterface

class SPToggleIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr), SPSetViewStyleInterface {

    var toggleState: ToggleState = ToggleState.DISABLED
    set(value) {
        field = value
        toggle()
    }


    override fun setViewStyle(@StyleRes newStyle: Int) {
        val styleAttrs = context.theme.obtainStyledAttributes(newStyle, R.styleable.SPToggleIcon)
        with(styleAttrs) {
            styledAttributes()
            recycle()
        }
    }

    private fun TypedArray.styledAttributes() {

    }

    private fun toggle() {
        toggleState = if (toggleState == ToggleState.DISABLED)
            ToggleState.ENABLED else ToggleState.DISABLED
    }

    enum class ToggleState {
        DISABLED,
        ENABLED
    }
}