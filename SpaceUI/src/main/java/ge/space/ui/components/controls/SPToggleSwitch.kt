package ge.space.ui.components.controls

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.handleAttributeAction

class SPToggleSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPToggleSwitch_Standard
) : SwitchCompat(context, attrs), SPViewStyling {

    init {
        getContext().withStyledAttributes(attrs, R.styleable.SwitchCompat, defStyleAttr, defStyleRes) {
            withStyledAttributes()
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.theme.obtainStyledAttributes(newStyle, R.styleable.SwitchCompat).run {
            withStyledAttributes()
            recycle()
        }
    }

    private fun TypedArray.withStyledAttributes() {
        getResourceId(R.styleable.SwitchCompat_android_thumb, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                setThumbResource(it)
            }

        getResourceId(R.styleable.SwitchCompat_track, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                setTrackResource(it)
            }
    }
}