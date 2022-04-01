package ge.space.ui.util.extension

import android.os.Build
import android.util.TypedValue
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import ge.space.spaceui.R

fun TextView.setTextStyle(styleRes: Int) {
    if (Build.VERSION.SDK_INT < 23) {
        TextViewCompat.setTextAppearance(this, styleRes)
    } else {
        this.setTextAppearance(styleRes)
    }
    setLineSpacingExtra(styleRes)
}

/**
 * TextAppearance doesn't support lineSpacingExtra
 * So we have to manually set it from attribute
 */
private fun TextView.setLineSpacingExtra(styleRes: Int) {
    val styleAttrs =
        context.theme.obtainStyledAttributes(styleRes, R.styleable.sp_typography_view_style)

    styleAttrs.run {
        val lineSpacingExtra = getDimensionPixelSize(
            R.styleable.sp_typography_view_style_android_lineSpacingExtra, 0
        )
        setLineSpacing(lineSpacingExtra.toFloat(), 1.0f)
    }
}