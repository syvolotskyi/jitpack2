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

/**
    helper extensions/methods
 */
val SPShowCaseComponent.hasSubComponents
    get() = getSubComponents().isNotEmpty()

val SPShowCaseComponent.isNew: Boolean
    get() = (when (this) {
        is SingleSPShowCaseComponent -> componentSP
        else -> this
    }).javaClass in NEW_COMPONENTS || getSubComponents().any { it.isNew }

val SPShowCaseComponent.flattenSubComponentSPS: List<SPShowCaseComponent>
    get() {
        return if (getComponentClass() != null) {
            listOf(SingleSPShowCaseComponent(this)) + getSubComponents()
        } else {
            getSubComponents()
        }
    }

internal class SingleSPShowCaseComponent(val componentSP: SPShowCaseComponent) :
    SPShowCaseComponent by componentSP {
    override fun getSubComponents(): List<SPShowCaseComponent> = emptyList()
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