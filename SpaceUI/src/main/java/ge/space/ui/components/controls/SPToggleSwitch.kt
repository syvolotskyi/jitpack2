package ge.space.ui.components.controls

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import com.google.android.material.switchmaterial.SwitchMaterial
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.base.SPViewStyling

class SPToggleSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SwitchMaterial(context, attrs, defStyleAttr), SPViewStyling {

    init {
        getContext().withStyledAttributes(attrs, R.styleable.SPToggleSwitch, defStyleAttr) {
            setViewStyle(getResourceId(R.styleable.SPToggleSwitch_style, DEFAULT_OBTAIN_VAL))
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

    }

}