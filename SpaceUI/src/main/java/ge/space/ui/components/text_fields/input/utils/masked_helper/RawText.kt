package ge.space.ui.components.text_fields.input.utils.masked_helper


class RawText (var text: String = "")

/**
 *
 * @param newStr New String to be added
 * @param start Position to insert newString
 * @param maxLength Maximum raw text length
 * @return Number of added characters
 */
fun RawText.addToString(newStr: String, start: Int, maxLength: Int): Int {
    var firstPart = ""
    var lastPart = ""
    var newString = newStr
    if (newString == "") {
        return 0
    } else require(start >= 0) {
        "Start position must be non-negative"
    }

    require(start <= text.length) {
        "Start position must be less than the actual text length"
    }

    var count = newString.length
    if (start > 0) {
        firstPart = text.substring(0, start)
    }
    if (start >= 0 && start < text.length) {
        lastPart = text.substring(start, text.length)
    }
    if (text.length + newString.length > maxLength) {
        count = maxLength - text.length
        newString = newString.substring(0, count)
    }
    text = firstPart + newString + lastPart
    return count
}
/**
 * text = 012345678, range = 123 =&gt; text = 0456789
 * @param range given range
 */
fun RawText.subtractFromString(range: Range) {
    var firstPart = ""
    var lastPart = ""
    if (range.start > 0 && range.start <= text.length) {
        firstPart = text.substring(0, range.start)
    }
    if (range.end >= 0 && range.end < text.length) {
        lastPart = text.substring(range.end, text.length)
    }
    text = firstPart + lastPart
}

fun RawText.length(): Int = text.length

fun RawText.charAt(position: Int): Char = text[position]