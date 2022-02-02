package com.space.formatter.extensions

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.SuperscriptSpan
import android.text.style.TextAppearanceSpan
import android.util.Patterns
import androidx.core.content.ContextCompat
import com.space.formatter.validators.SPRegexValidators

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

fun String.toShortMobileNumber(): String? {
    var shortNumber: String = this.replace(" ", "")
        .replace("-", "")
        .replace("\\s+", "")
        .replace("(", "")
        .replace(")", "")
        .replace("^.*[\\s\\(\\)-].*", "")
        .replace("\u00a0", "")
        .replace("&nbsp;", "")

    if (SPRegexValidators.MOBILE_PHONE_LONG.matches(shortNumber))
        shortNumber = shortNumber.removeRange(0, if (this.startsWith("+")) 4 else 3)

    return if (SPRegexValidators.MOBILE_PHONE_SHORT.matches(shortNumber)) shortNumber else null
}
fun String.toShortLandLineNumber(): String? {
    var shortNumber: String = this.replace(" ", "")
        .replace("-", "")
        .replace("\\s+", "")
        .replace("(", "")
        .replace(")", "")
        .replace("^.*[\\s\\(\\)-].*", "")
        .replace("\u00a0", "")
        .replace("&nbsp;", "")

    if (!SPRegexValidators.LAND_LINE_SHORT.matches(shortNumber))
        shortNumber = shortNumber.removeRange(0, if (this.startsWith("+")) 4 else 3)

    return if (SPRegexValidators.LAND_LINE_LONG.matches(shortNumber)) shortNumber else null
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

fun String.isEmptyOrContainsDot() = this.isEmpty() || this == "."

fun String.removeWhiteSpaces() = this.replace(" ", "")

fun Context.getTextColorSpannableText(
    color: Int,
    fullText: String,
    vararg linkText: String
): SpannableString {
    val result = SpannableString(fullText)
    linkText.forEach {
        val startIndex = fullText.indexOf(it)
        val endIndex = startIndex + it.length
        result.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, color)),
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return result
}

fun String?.withPlusPrefix() = "+$this"

fun String?.withMinusPrefix() = "-$this"

fun String.separate(size: Int, separator: String): String {
    return this.chunked(size).joinToString(separator = separator)
}

fun Int.formatTwoDigits(): String {
    return String.format("%02d", this)
}

fun String.digitBeautifier(size: Int = 3, separator: String = " "): String {
    if(this.isEmpty())
        return ""
    var result = ""
    var counter = 0
    val tillIndex = (this.length % size) - 1
    this.replace(" ", "").forEachIndexed { index, c ->
        result+=c
        counter++
        if(index == tillIndex || counter == size && index != this.length - 1){
            counter = 0
            result += separator
        }
    }
    return result
}