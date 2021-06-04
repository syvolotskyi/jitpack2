package ge.space.extensions

import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.SuperscriptSpan
import android.text.style.TextAppearanceSpan
import android.util.Patterns

fun String.removeSpanableTag(): String {
    return this.replace("[", "").replace("]", "")
}

fun String.checkSpanableTag(): Boolean {
    return this.contains("[") && this.contains("]")
}

fun String.findSpannableArray(): Pair<String, MutableList<Pair<Int, Int>>> {
    var findSpannableProcess = true
    var replaceString = this
    val indexes: MutableList<Pair<Int, Int>> = arrayListOf()

    while (findSpannableProcess) {
        val startIndex = replaceString.indexOfFirst { it.toString() == "[" }
        if (startIndex != -1) {
            replaceString = replaceString.replaceFirst("[", "")
            indexes.add(Pair(startIndex, (replaceString.indexOfFirst { it.toString() == "]" })))
            replaceString = replaceString.replaceFirst("]", "")
        } else
            findSpannableProcess = false
    }
    return Pair(replaceString, indexes)
}

fun String.isEmail(): Boolean {
    return if (Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        val domain = this.substring(this.indexOf('@'), this.length)
        val secondPartOfDomain = domain.substring(domain.indexOf('.'), domain.length)
        secondPartOfDomain.length != 2
    } else false
}

fun String.isAddress(): Boolean {
    var containsDigit = false
    var containsLowerCase = false

    for (c in this.toCharArray())
        if (Character.isDigit(c)) {
            containsDigit = Character.isDigit(c)
            break
        }
    for (c in this.toCharArray())
        if (Character.isLetter(c)) {
            containsLowerCase = Character.isLetter(c)
            break
        }
    return containsDigit && containsLowerCase
}

fun String.removeLastTwoDigits(): String? {
    val lastIndex = if (this.length > 2) this.length - 2 else 0
    val subNumber = this.substring(0, lastIndex)
    return "$subNumber**"
}

fun String.contactInitials(): String {
    var array = this.split(" ")
    var buffer = StringBuffer()
    for (s in array)
        if (s.isNotEmpty())
            buffer.append(s.first())
    return buffer.toString().toUpperCase()
}

fun String.getParameterValueFromUrl(pramName: String) : String? {
    return Uri.parse(this).getQueryParameter(pramName)
}

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

fun String.formatSelectedPhoneNumber(phonePrefix: String?): String {
    phonePrefix?.let { prefix ->
        if (!this.startsWith(prefix)) {
            return "$prefix$this"
        }
    }
    return this
}

fun String?.removeCommasWhenNullOrEmpty(): String {
    return if (!this.isNullOrEmpty()) "$this, " else ""
}

fun String.toSpannable(
    subStringsList: List<String>,
    stylesList: List<TextAppearanceSpan>
): SpannableString {
    val spannable = SpannableString(this)
    if (subStringsList.size == stylesList.size) {
        subStringsList.forEachIndexed { index, _ ->
            val startIndex = this.indexOf(subStringsList[index])
            val endIndex = startIndex + subStringsList[index].length
            spannable.setSpan(
                stylesList[index],
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
    return spannable
}