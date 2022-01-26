package ge.space.ui.components.controls

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.google.android.material.switchmaterial.SwitchMaterial
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.handleAttributeAction

class SPToggleSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SwitchMaterial(context, attrs, defStyleAttr), SPViewStyling {

    init {
        getContext().withStyledAttributes(attrs, R.styleable.SPToggleSwitch, defStyleAttr) {
            withStyledAttributes()
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.theme.obtainStyledAttributes(newStyle, R.styleable.SPToggleSwitch).run {
            withStyledAttributes()
            recycle()
        }
    }

    private fun TypedArray.withStyledAttributes() {
        getResourceId(R.styleable.SPToggleSwitch_android_thumb, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                setThumbResource(it)
            }

        getResourceId(R.styleable.SPToggleSwitch_track, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                setTrackResource(it)
            }

        getResourceId(R.styleable.SPToggleSwitch_thumbTint, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                thumbTintList = ColorStateList.valueOf(ContextCompat.getColor(context, it))
            }

        getResourceId(R.styleable.SPToggleSwitch_trackTint, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                trackTintList = ColorStateList.valueOf(ContextCompat.getColor(context, it))
            }
    }
}