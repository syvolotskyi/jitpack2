package ge.space.ui.components.controls

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpToggleIconLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling

class SPToggleIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr), SPViewStyling {

    val binding: SpToggleIconLayoutBinding

    init {
        binding = SpToggleIconLayoutBinding.inflate(LayoutInflater.from(context), this)
        isCircle = true
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

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        if (selected) {
            color = R.attr.brand_primary
            binding.toggleIcon.colorFilter = PorterDuffColorFilter(R.attr.static_white,
                PorterDuff.Mode.SRC_IN)
        } else {
            color = android.R.color.transparent
            binding.toggleIcon.colorFilter = PorterDuffColorFilter(R.attr.brand_primary,
                PorterDuff.Mode.SRC_IN)
        }
    }

}