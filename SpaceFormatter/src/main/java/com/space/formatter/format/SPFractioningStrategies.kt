package com.space.formatter.format

import java.text.DecimalFormat

sealed class SPFractioningStrategies :
    SPFractioningStrategy {

    /**
     * If fractionDigits is 2 Produces results like "200" "200.23" "200.20" "200.02"
     */
    object DontTrimZerosUnlessWhole : SPFractioningStrategies() {
        override fun apply(decimalFormat: DecimalFormat, fractionDigits: Int, number: Double) {
            decimalFormat.apply {
                maximumFractionDigits = fractionDigits
                minimumFractionDigits = if ((number % 1) == 0.0) 0 else fractionDigits
            }
        }
    }

    /**
     * If fractionDigits is 2, Produces results like "200" "200.23" "200.2" "200.02"
     */
    object TrimTrailingZeros : SPFractioningStrategies() {
        override fun apply(decimalFormat: DecimalFormat, fractionDigits: Int, number: Double)  {
            decimalFormat.apply {
                maximumFractionDigits = fractionDigits
            }
        }
    }
}
