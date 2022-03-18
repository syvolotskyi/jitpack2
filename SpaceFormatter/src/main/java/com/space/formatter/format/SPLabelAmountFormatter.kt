package com.space.formatter.format

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*
import kotlin.math.pow

class SPLabelAmountFormatter(var fractionStrategy: SPFractioningStrategy) :
    LongFormatter {

    var fractionPrecision = 2
    var separator: Char = ','
    var group: Boolean = true

    override fun format(input: Long): String {
        val amountDecimal: Double = input / 10.0.pow(fractionPrecision.toDouble())
        (DecimalFormat.getInstance(Locale.US) as DecimalFormat).run {
            fractionStrategy.apply(this, fractionPrecision, amountDecimal)
            isGroupingUsed = group
            decimalFormatSymbols = decimalFormatSymbols.apply { groupingSeparator = separator }
            return format(amountDecimal)
        }
    }

    override fun undoFormat(formattedText: String): Long {
        val decimal =  formattedText.replace(separator.toString(), "").toBigDecimal()
        val roundingMultiplier = BigDecimal(10).pow(fractionPrecision)
        return decimal.multiply(roundingMultiplier).toLong()
    }

}