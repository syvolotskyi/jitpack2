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
import ge.space.ui.util.extension.handleAttributeAction

class SPToggleIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr), SPViewStyling {

    val binding = SpToggleIconLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        isCircle = true
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        val styleAttrs = context.theme.obtainStyledAttributes(newStyle, R.styleable.SPToggleIcon)
        with(styleAttrs) {
            withStyledAttributes()
            recycle()
        }
    }

    private fun TypedArray.withStyledAttributes() {
        getResourceId(R.styleable.SPToggleIcon_android_src, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                binding.toggleIcon.setImageResource(it)
            }

        getResourceId(R.styleable.SPToggleIcon_android_state_selected, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                binding.toggleIcon.isSelected = getBoolean(it, false)
            }

        getResourceId(R.styleable.SPToggleIcon_backgroundColor, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                binding.toggleIcon.setBackgroundColor(getColor(it, DEFAULT_OBTAIN_VAL))
            }

        getResourceId(R.styleable.SPToggleIcon_selectedBackgroundColor, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {

            }

        getResourceId(R.styleable.SPToggleIcon_iconTint, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {

            }

        getResourceId(R.styleable.SPToggleIcon_selectedIconTint, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {

            }
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