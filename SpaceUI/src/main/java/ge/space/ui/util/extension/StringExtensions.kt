package ge.space.ui.util.extension

import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.SuperscriptSpan
import android.text.style.TextAppearanceSpan
import android.util.Patterns

/*
* Return an empty string
*/
const val EMPTY_TEXT = ""

/*
* Return an space string
*/
const val SPACE = " "


fun String.appendAsterisk(): Spannable =
    SpannableStringBuilder("$this *").apply {
        val spannedIndexStart = this@appendAsterisk.length + 1
        val spannedIndexEnd = this@appendAsterisk.length + 2
        setSpan(
            SuperscriptSpan(),
            spannedIndexStart,
            spannedIndexEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(Color.parseColor("#EC008C")),
            spannedIndexStart,
            spannedIndexEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
