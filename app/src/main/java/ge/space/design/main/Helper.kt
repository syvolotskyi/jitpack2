package ge.space.design.main

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import ge.space.design.NEW_COMPONENTS

/*
some helper extensions/methods
 */
val ShowCaseComponent.hasSubComponents
    get() = getSubComponents().isNotEmpty()

val ShowCaseComponent.isNew: Boolean
    get() = (when (this) {
        is SingleShowCaseComponent -> component
        else -> this
    }).javaClass in NEW_COMPONENTS || getSubComponents().any { it.isNew }

val ShowCaseComponent.flattenSubComponents: List<ShowCaseComponent>
    get() {
        return if (getComponentClass() != null) {
            listOf(SingleShowCaseComponent(this)) + getSubComponents()
        } else {
            getSubComponents()
        }
    }

internal class SingleShowCaseComponent(val component: ShowCaseComponent) :
    ShowCaseComponent by component {
    override fun getSubComponents(): List<ShowCaseComponent> = emptyList()
}

fun getNewBadge(): Spanned {
    return SpannableString("New").apply {
        setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        setSpan(
            RelativeSizeSpan(0.6f),
            0,
            length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}