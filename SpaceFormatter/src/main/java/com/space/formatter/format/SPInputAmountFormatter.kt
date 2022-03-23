package com.space.formatter.format

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

class SPInputAmountFormatter : StringFormatter {

    var maxFractionDigits = 2
    var maxIntegerDigits = 9
    var separator: Char = ','

    override fun format(input: String): String {
        val inputAmount = if (input == ".") "0." else input

        var amountDecimal = BigDecimal(inputAmount.replace(separator.toString(), ""))
            .setScale(maxFractionDigits, RoundingMode.DOWN)

        val integerLength = amountDecimal.precision() - amountDecimal.scale()
        if (integerLength > maxIntegerDigits) {
            val fraction = amountDecimal.remainder(BigDecimal.ONE)
            val integer = amountDecimal.toBigInteger()
            val newInteger = integer.toString().substring(0, maxIntegerDigits).toBigDecimal()
            amountDecimal = newInteger.plus(fraction)
        }

        (DecimalFormat.getInstance(Locale.US) as DecimalFormat).run {
            maximumFractionDigits = maxFractionDigits
            if (inputAmount.contains(".")) {
                val fractionOffset = inputAmount.length - inputAmount.indexOf(".") - 1
                minimumFractionDigits = if (fractionOffset < maxFractionDigits) fractionOffset else maxFractionDigits
            }
            isGroupingUsed = true
            decimalFormatSymbols = decimalFormatSymbols.apply { groupingSeparator = separator }
            isDecimalSeparatorAlwaysShown = inputAmount.endsWith(".")
            return format(amountDecimal)
        }
    }

    override fun undoFormat(formattedText: String): String {
        return formattedText.replace(separator.toString(), " ")
    }

}