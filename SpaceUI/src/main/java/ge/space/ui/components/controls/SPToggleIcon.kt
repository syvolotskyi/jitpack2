package ge.space.ui.components.controls

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.google.android.material.button.MaterialButton
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.handleAttributeAction

class SPToggleIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPToggleIcon_Standard
) : MaterialButton(context, attrs), SPViewStyling {

    init {
        context.withStyledAttributes(attrs, R.styleable.SPToggleIcon, defStyleAttr, defStyleRes) {
            withStyledAttributes()
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.withStyledAttributes(newStyle, R.styleable.SPToggleIcon) {
            withStyledAttributes()
        }
    }

    private fun TypedArray.withStyledAttributes() {
        getResourceId(R.styleable.SPToggleIcon_android_background, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                background = ContextCompat.getDrawable(context, it)
            }
    }

}