package ge.space.ui.util.extension

import android.content.Context
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView

fun Context.getColorFromTextAppearance(textAppearance: Int): Int {
    val styleAttrs2 = theme.obtainStyledAttributes(textAppearance, R.styleable.TextAppearance)
    return styleAttrs2.getColor(
        R.styleable.TextAppearance_android_textColor,
        SPBaseView.DEFAULT_OBTAIN_VAL
    )
}