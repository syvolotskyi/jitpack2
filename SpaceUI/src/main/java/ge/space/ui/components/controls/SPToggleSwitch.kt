package ge.space.ui.components.controls

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.util.extension.handleAttributeAction

class SPToggleSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SwitchCompat(context, attrs, defStyleAttr) {

    init {
        getContext().withStyledAttributes(attrs, R.styleable.SPToggleSwitch, defStyleAttr) {
            withStyledAttributes()
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
    }
}